package com.zz.service.impl;

import com.zz.entity.AdminPerms;
import com.zz.entity.AdminRole;
import com.zz.entity.AdminRolePerms;
import com.zz.mapper.AdminPermsMapper;
import com.zz.mapper.AdminRoleMapper;
import com.zz.mapper.AdminRolePermsMapper;
import com.zz.service.AdminPermsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zz.service.AdminRolePermsService;
import com.zz.service.AdminRoleService;
import com.zz.service.AdminUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zZeng
 * @since 2021-12-06
 */
@Service
public class AdminPermsServiceImpl extends ServiceImpl<AdminPermsMapper, AdminPerms> implements AdminPermsService {

    @Autowired
    AdminRoleService roleService;
    @Autowired
    AdminPermsMapper permsMapper;
    @Autowired
    AdminUserRoleService userRoleService;
    @Autowired
    AdminRolePermsService rolePermsService;

    @Override
    public boolean needInterceptor(String requestUri) {
        List<AdminPerms> ps = list();

        for (AdminPerms p : ps) {
            // 匹配前缀
            if (requestUri.startsWith(p.getPath())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public List<AdminPerms> listPermsByRid(int rid) {
        List<Integer> pIds = rolePermsService.listAllByRid(rid)
                .stream().map(AdminRolePerms::getPid).collect(Collectors.toList());
        return permsMapper.selectBatchIds(pIds);
    }

    @Override
    public Set<String> listPermsUrlsByUser(String username) {
        List<Integer> rIds = roleService.listRolesByUsername(username)
                .stream().map(AdminRole::getId).collect(Collectors.toList());

        List<Integer> pIds = rolePermsService.listAllByRisList(rIds)
                .stream().map(AdminRolePerms::getPid).collect(Collectors.toList());

        Set<String> urls = permsMapper.selectBatchIds(pIds)
                .stream().map(AdminPerms::getPath).collect(Collectors.toSet());

        return urls;
    }
}
