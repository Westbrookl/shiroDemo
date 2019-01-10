package com.jhc.demo;

import com.jhc.demo.model.Permission;
import com.jhc.demo.model.Role;
import com.jhc.demo.model.User;
import com.jhc.demo.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author jhc on 2019/1/9
 */
public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 授权部分的内容是得到一个用户从数据库中得到他所有的角色以及所有的权限然后构造成一个新的
     * AuthorizationInfo这个Info成为Shiro的一部分然后securityManager可以通过这个部分来获取这个内容
     *
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissionList = new ArrayList<>();
        List<String> roleNameList = new ArrayList<>();
        Set<Role> roleSet = user.getRoles();
        if(roleSet != null && roleSet.size() > 0){
            for(Role role : roleSet){
                roleNameList.add(role.getRname());
                Set<Permission> permissionSet = role.getPermissions();
                if(permissionSet != null && permissionSet.size() > 0 ){
                    for(Permission permission:permissionSet){
                        permissionList.add(permission.getPname());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        info.addRoles(roleNameList);
        return info;

    }

    /**
     * 认证登录
     * 这个是根据用户名来确认登录 然后根据把这个用户放入到Realm当中去可以用来做授权处理
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        User user = userService.findByUserName(username);
        System.out.println(user.getUsername());
        return new SimpleAuthenticationInfo(user,user.getPassword(),this.getClass().getName());
    }
}
