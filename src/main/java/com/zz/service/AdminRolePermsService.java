package com.zz.service;

import com.zz.entity.AdminPerms;
import com.zz.entity.AdminRolePerms;
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
public interface AdminRolePermsService extends IService<AdminRolePerms> {
    /**
     * 通过角色id查找角色的所有权限
     * @param rid: 角色id
     * @return list
     */
    List<AdminRolePerms> listAllByRid(int rid);

    /**
     * 通过角色id集合查找所有权限
     * @param rids：角色id集合
     * @return list
     */
    List<AdminRolePerms> listAllByRisList(List<Integer> rids);

    /**
     * 更改角色的权限
     * @param rid: 角色id
     * @param perms: 权限
     */
    void savePermsChanges(int rid, List<AdminPerms> perms);
}
