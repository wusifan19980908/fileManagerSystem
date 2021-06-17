package com.wusifan.filemanagersystem.controller;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wusifan.filemanagersystem.common.R;
import com.wusifan.filemanagersystem.model.LoginForm;
import com.wusifan.filemanagersystem.model.SysUser;
import com.wusifan.filemanagersystem.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jilin
 * @date 2021/06/16 15:49
 */
@RequestMapping("/user")
@Api(tags = "用户相关接口")
@RestController
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @ApiOperation("登陆接口")
    @PostMapping("/login")
    public R login(@RequestBody LoginForm loginForm) throws Exception {
        SysUser user = sysUserService.getOne(Wrappers.lambdaQuery(SysUser.class)
                        .eq(SysUser::getUsername,loginForm.getUsername()));
        if (user==null){
            return R.error("用户不存在");
        }
        if (!user.getPassword().equals(new Sha256Hash(loginForm.getPassword(),user.getSalt()).toHex())){
            return R.error("用户名或密码不正确");
        }
        R r = sysUserService.createToken(user.getId());
        return r;
    }
    @ApiOperation("注册接口")
    @PostMapping("/register")
    public R register(@RequestBody LoginForm loginForm){
        SysUser user = sysUserService.getOne(Wrappers.lambdaQuery(SysUser.class)
                        .eq(SysUser::getUsername,loginForm.getUsername()));
        if (user!=null){
            return R.error("用户已存在");
        }
        user = new SysUser();
        user.setUsername(loginForm.getUsername());
        String salt = RandomUtil.randomString(20);
        user.setSalt(salt);
        user.setPassword(new Sha256Hash(loginForm.getPassword(), salt).toHex());
        sysUserService.save(user);
        return R.ok();
    }
}
