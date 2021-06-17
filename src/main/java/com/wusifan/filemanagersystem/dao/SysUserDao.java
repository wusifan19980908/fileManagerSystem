package com.wusifan.filemanagersystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wusifan.filemanagersystem.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author jilin
 * @date 2021/06/16 9:46
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUser> {
    SysUser getUserByUsername(@Param("username")String username);
}
