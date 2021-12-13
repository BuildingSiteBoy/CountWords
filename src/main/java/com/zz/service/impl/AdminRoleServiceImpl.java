package com.zz.service.impl;

import com.zz.entity.AdminPerms;
import com.zz.entity.AdminRole;
import com.zz.entity.AdminUserRole;
import com.zz.mapper.AdminRoleMapper;
import com.zz.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zZeng
 * @since 2021-11-25
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements AdminRoleService {
    @Autowired
    AdminRoleMapper roleMapper;
    @Autowired
    AdminUserService userService;
    @Autowired
    AdminPermsService permsService;
    @Autowired
    AdminUserRoleService userRoleService;
    @Autowired
    AdminRolePermsService rolePermsService;

    @Override
    public List<AdminRole> listWithPerms() {
        List<AdminRole> roles = list();
        roles.forEach(role -> {
            List<AdminPerms> perms = permsService.listPermsByRid(role.getId());
            role.setPerms(perms);
        });
        return roles;
    }

    @Override
    public List<AdminRole> listRolesByUsername(String username) {
        int uid = userService.getByName(username).getId();
        List<Integer> rids = new ArrayList<>();
        List<AdminUserRole> userRoles = userRoleService.listAllByUid(uid);
        for (AdminUserRole userRole : userRoles) {
            if (userRole.getRid() == null) {
                continue;
            }
                rids.add(userRole.getRid());
        }

        if (rids.size() == 0) {
            return null;
        }

        return roleMapper.selectBatchIds(rids);
    }

    @Override
    public AdminRole updateRoleStatus(AdminRole role) {
        AdminRole roleInDb = roleMapper.selectById(role.getId());
        roleInDb.setEnable(role.getEnable());
        roleMapper.updateById(roleInDb);
        return roleInDb;
    }

    @Override
    public void editRole(AdminRole role) {
        roleMapper.updateById(role);
        rolePermsService.savePermsChanges(role.getId(), role.getPerms());
    }
}
