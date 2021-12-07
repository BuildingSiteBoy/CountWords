package com.zz.service;

import com.zz.entity.AdminUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zZeng
 * @since 2021-11-25
 */
public interface AdminUserService extends IService<AdminUser> {

    /**
     * 列出所有用户及其角色信息
     * @return list
     */
    List<AdminUser> listWithRoles();

    /**
     * 通过用户名获取密码
     * @param username：用户名
     * @return password
     */
    String getPassword(String username);

    /**
     * 通过用户名查询用户
     * @param username：用户名
     * @return user
     */
    AdminUser getByName(String username);

    /**
     * 通过用户名与密码查询用户
     * @param username: 账户
     * @param password: 密码
     * @return user
     */
    AdminUser getByNamePwd(String username, String password);

    /**
     * 注册用户
     * @param user：注册用户
     * @return 0:用户名或密码为空；1：注册成功；2：用户已存在
     */
    int register(AdminUser user);

    /**
     * 更新密码
     * @param user：用户信息
     * @return user
     */
    AdminUser resetPassword(AdminUser user);

    /**
     * 编辑用户
     * @param user：用户信息
     */
    void editUser(AdminUser user);
}
