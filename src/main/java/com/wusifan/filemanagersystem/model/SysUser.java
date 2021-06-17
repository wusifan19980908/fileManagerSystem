package com.wusifan.filemanagersystem.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author jilin
 * @date 2021/06/16 9:32
 */
@Data
@TableName("sys_user")
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private Integer id;
    private String username;
    private String password;
    private String salt;
    private Integer roleId;
    /**
     * 状态 1:正常 2:封禁
     */
    private Integer status;
}
