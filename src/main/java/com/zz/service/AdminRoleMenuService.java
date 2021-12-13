package com.zz.service;

import com.zz.entity.AdminRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zZeng
 * @since 2021-12-10
 */
public interface AdminRoleMenuService extends IService<AdminRoleMenu> {

    /**
     * 通过角色id查找该角色拥有的所有菜单
     * @param rid 角色id
     * @return list
     */
    List<AdminRoleMenu> findAllByRid(int rid);

    /**
     * 通过角色id列表查找他们拥有的所有角色
     * @param rids 角色id列表
     * @return list
     */
    List<AdminRoleMenu> findAllByRids(List<Integer> rids);

    /**
     * 修改角色拥有的菜单
     * @param rid 角色id
     * @param menusIds 要更新的菜单
     */
    void updateRoleMenu(int rid, Map<String, List<Integer>> menusIds);

}
