package com.wusifan.filemanagersystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wusifan.filemanagersystem.common.R;
import com.wusifan.filemanagersystem.dao.SysUserDao;
import com.wusifan.filemanagersystem.model.SysUser;
import com.wusifan.filemanagersystem.service.SysUserService;
import com.wusifan.filemanagersystem.shiro.TokenGenerator;
import org.springframework.stereotype.Service;

/**
 * @author jilin
 * @date 2021/06/16 9:43
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao,SysUser> implements SysUserService {
    @Override
    public R createToken(Integer userId) throws Exception {
        String token  = TokenGenerator.generateValue();
        R r = R.ok().put("token", token);
        return r;
    }
}
