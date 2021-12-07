package com.zz.service;

import com.zz.entity.AdminRole;
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
public interface AdminRoleService extends IService<AdminRole> {

    /**
     * 列出所有角色及其权限
     * @return list
     */
    List<AdminRole> listWithPerms();

    /**
     * 通过用户名查找所有的角色
     * @param username：用户名
     * @return list
     */
    List<AdminRole> listRolesByUsername(String username);

    /**
     * 更改角色状态
     * @param role：角色信息
     * @return role
     */
    AdminRole updateRoleStatus(AdminRole role);

    /**
     * 编辑角色
     * @param role：角色信息
     */
    void editRole(AdminRole role);
}
