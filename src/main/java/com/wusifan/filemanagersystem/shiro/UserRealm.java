package com.wusifan.filemanagersystem.shiro;

import com.wusifan.filemanagersystem.dao.SysMenuDao;
import com.wusifan.filemanagersystem.dao.SysUserDao;
import com.wusifan.filemanagersystem.model.SysUser;
import com.wusifan.filemanagersystem.service.SysUserService;
import com.wusifan.filemanagersystem.util.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

@Component
public class UserRealm extends AuthorizingRealm {
    @Resource
    private SysUserDao sysUserDao;
    @Resource
    private SysMenuDao sysMenuDao;
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof ShiroToken;
    }

    /**
     * 授权(验证权限时调用)
     * 获取用户权限集合
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser user = (SysUser) principalCollection.getPrimaryPrincipal();
        if (user==null){
          throw new UnknownAccountException("帐号不存在");
        }
        //获取用户权限
        Set<String> permsSet = sysMenuDao.permitList(user.getId());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证、登陆时调用
     * 验证用户登陆
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        SysUser sysUser = sysUserDao.getUserByUsername(token.getUsername());
        if (sysUser==null){
            throw new UnknownAccountException("帐号或密码不正确");
        }
        //帐号封禁
        if (sysUser.getStatus()==SysUserService.STOP_STATUS){
            throw new LockedAccountException("帐号已被锁定,请联系管理员解决");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser,sysUser.getPassword(), ByteSource.Util.bytes(sysUser.getSalt()),getName());
        return info;
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName(ShiroUtils.hashAlgorithmName);
        matcher.setHashIterations(ShiroUtils.hashIterations);
        super.setCredentialsMatcher(credentialsMatcher);
    }
}
