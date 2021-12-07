package com.zz.common.realm;

import com.zz.entity.AdminUser;
import com.zz.service.AdminPermsService;
import com.zz.service.AdminUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.Set;

/**
 * @author zz
 * realm
 */
public class DatabaseRealm extends AuthorizingRealm {

    @Autowired
    private AdminUserService userService;
    @Autowired
    AdminPermsService adminPermsService;

    /**
     * 授权信息
     * @param principalCollection: 用户身份信息
     * @return 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前用户的所有权限
        String username = principalCollection.getPrimaryPrincipal().toString();
        Set<String> perms = adminPermsService.listPermsUrlsByUser(username);

        //将全线放入授权信息中
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(perms);
        return authorizationInfo;
    }

    /**
     * 获取认证信息，即根据 token中的用户名从数据库中获取密码、盐等并返回
     * @param authenticationToken: token
     * @return 认证信息
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户信息
        String username = token.getPrincipal().toString();
        AdminUser user = userService.getByName(username);
        if (ObjectUtils.isEmpty(user)) {
            throw new UnknownAccountException();
        }
        String password = user.getPassword();
        String salt = user.getSalt();

        //认证信息内存放账号、密码、盐值以及getName()，getName()通常返回当前类名
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                username, password, ByteSource.Util.bytes(salt), getName());
        return authenticationInfo;
    }
}
