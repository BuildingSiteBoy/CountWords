package com.zz.service.impl;

import com.zz.entity.AdminRoleMenu;
import com.zz.mapper.AdminRoleMenuMapper;
import com.zz.service.AdminRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zZeng
 * @since 2021-12-10
 */
@Service
public class AdminRoleMenuServiceImpl extends ServiceImpl<AdminRoleMenuMapper, AdminRoleMenu> implements AdminRoleMenuService {
    @Autowired
    AdminRoleMenuService roleMenuService;


    @Override
    public List<AdminRoleMenu> findAllByRid(int rid) {
        return null;
    }

    @Override
    public List<AdminRoleMenu> findAllByRids(List<Integer> rids) {
        return null;
    }

    @Override
    public void updateRoleMenu(int rid, Map<String, List<Integer>> menusIds) {

    }
}
