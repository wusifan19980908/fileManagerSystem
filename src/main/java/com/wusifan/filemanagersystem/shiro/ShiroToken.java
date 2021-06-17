package com.wusifan.filemanagersystem.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author jilin
 * @date 2021/06/16 15:16
 */
public class ShiroToken implements AuthenticationToken {
    private String token;
    public ShiroToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
