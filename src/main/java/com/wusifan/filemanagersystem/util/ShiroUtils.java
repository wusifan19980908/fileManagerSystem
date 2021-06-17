package com.wusifan.filemanagersystem.util;

import com.wusifan.filemanagersystem.model.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @author jilin
 * @date 2021/06/16 14:07
 */
public class ShiroUtils {
    /**  加密算法 */
    public final static String hashAlgorithmName = "SHA-256";
    /**  循环次数 */
    public final static int hashIterations = 16;
    public static String sha256(String password, String salt) {
        return new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toString();
    }
    // 获取一个测试账号 admin
    public static void main(String[] args) {
        // 3743a4c09a17e6f2829febd09ca54e627810001cf255ddcae9dabd288a949c4a
        System.out.println(sha256("admin","123")) ;
    }
    /**
     * 获取会话
     */
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }
    /**
     * Subject：主体，代表了当前“用户”
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }
    public static SysUser getUserEntity() {
        return (SysUser)SecurityUtils.getSubject().getPrincipal();
    }
    public static Integer getUserId() {
        return getUserEntity().getId();
    }
    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }
    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }
    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }
    public static void logout() {
        SecurityUtils.getSubject().logout();
    }
}
