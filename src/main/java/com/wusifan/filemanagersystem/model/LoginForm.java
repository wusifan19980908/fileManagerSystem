package com.wusifan.filemanagersystem.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author jilin
 * @date 2021/06/16 15:51
 */
@Data
@ApiModel(value = "登陆表单",description = "登陆表单")
public class LoginForm{
    @ApiModelProperty(value = "用户名",name = "username",example = "wusifan")
    private String username;
    @ApiModelProperty(value = "密码",name = "password",example = "170929")
    private String password;
}
