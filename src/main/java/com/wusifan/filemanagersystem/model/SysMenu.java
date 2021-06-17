package com.wusifan.filemanagersystem.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author jilin
 * @date 2021/06/16 10:15
 */
@Data
@TableName("sys_menu")
public class SysMenu implements Serializable {
    @TableId
    private Integer id;
    /**
     * 父级菜单id
     */
    private Integer parentId;
    /**
     * 菜单名
     */
    private String menuName;
    /**
     * 权限字段
     */
    private String sysRole;
}
