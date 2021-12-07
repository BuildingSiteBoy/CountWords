package com.zz.service;

import com.zz.entity.AdminRole;
import com.zz.entity.AdminUserRole;
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
public interface AdminUserRoleService extends IService<AdminUserRole> {

    /**
     * 通过用户id查找用户所有的角色
     * @param uid:用户id
     * @return list
     */
    List<AdminUserRole> listAllByUid(int uid);

    /**
     * 更改用户的角色信息
     * @param uid：用户id
     * @param roles：角色信息
     */
    void saveRoleChanges(int uid, List<AdminRole> roles);
}
