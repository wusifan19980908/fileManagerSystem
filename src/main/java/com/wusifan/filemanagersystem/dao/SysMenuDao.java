package com.wusifan.filemanagersystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wusifan.filemanagersystem.model.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @author jilin
 * @date 2021/06/16 10:24
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenu> {
    Set<String> permitList(@Param("userId") Integer userId);
}
