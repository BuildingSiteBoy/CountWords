package com.zz.service;

import com.zz.entity.AdminUser;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * 通过用户名查询用户
     * @param username：用户名
     * @return user
     */
    public AdminUser getByName(String username);

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
}
