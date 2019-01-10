package com.jhc.demo;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @author jhc on 2019/1/10
 */
public class CredentialMatcher extends SimpleCredentialsMatcher {
    @Override
    /**
     * 对这个方法进行验证，自己制定比较的规则在这里只需要比较时候相等就可以了
     * 这个token是从用户名里的到的密码
     * 而info是从relam中得到的内容进行验证的。
     */
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
        String password = new String(usernamePasswordToken.getPassword());
        String auPassword = (String)info.getCredentials();
        return this.equals(password,auPassword);
    }
}
