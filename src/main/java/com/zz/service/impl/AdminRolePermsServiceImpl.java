package com.zz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zz.entity.AdminPerms;
import com.zz.entity.AdminRolePerms;
import com.zz.mapper.AdminRolePermsMapper;
import com.zz.service.AdminRolePermsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类：角色权限表
 * </p>
 *
 * @author zZeng
 * @since 2021-11-25
 */
@Service
public class AdminRolePermsServiceImpl extends ServiceImpl<AdminRolePermsMapper, AdminRolePerms> implements AdminRolePermsService {

    @Autowired
    AdminRolePermsMapper rolePermsMapper;

    @Override
    public List<AdminRolePerms> listAllByRid(int rid) {
        return rolePermsMapper.selectList(
                new QueryWrapper<AdminRolePerms>().eq("rid", rid)
        );
    }

    @Override
    public List<AdminRolePerms> listAllByRisList(List<Integer> rids) {
        return rolePermsMapper.selectList(
                new QueryWrapper<AdminRolePerms>().in("rid", rids)
        );
    }

    @Override
    public void savePermsChanges(int rid, List<AdminPerms> perms) {
        // 删除当前角色的所有权限
        rolePermsMapper.delete(
                new QueryWrapper<AdminRolePerms>().eq("rid", rid)
        );

        // 插入角色的权限信息
        perms.forEach(perm -> {
            AdminRolePerms rp = new AdminRolePerms();
            rp.setRid(rid);
            rp.setPid(perm.getId());
            rolePermsMapper.insert(rp);
        });
    }
}
