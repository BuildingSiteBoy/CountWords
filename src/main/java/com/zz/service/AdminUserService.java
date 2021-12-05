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
     * @param username
     * @return
     */
    public AdminUser getByName(String username);

    /**
     * 通过用户名与密码查询用户
     * @param username
     * @param password
     * @return
     */
    AdminUser getByNamePwd(String username, String password);
}
