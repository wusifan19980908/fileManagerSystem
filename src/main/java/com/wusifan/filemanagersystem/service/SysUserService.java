package com.wusifan.filemanagersystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wusifan.filemanagersystem.common.R;
import com.wusifan.filemanagersystem.model.SysUser;

/**
 * @author jilin
 * @date 2021/06/16 9:41
 */
public interface SysUserService extends IService<SysUser> {
    R createToken(Integer userId) throws Exception;
    //常量
    final static int NORMAL_STATUS = 1;//正常
    final static int STOP_STATUS = 2;//封禁
}
