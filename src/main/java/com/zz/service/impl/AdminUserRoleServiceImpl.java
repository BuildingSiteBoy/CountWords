package com.zz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zz.entity.AdminRole;
import com.zz.entity.AdminUserRole;
import com.zz.mapper.AdminUserRoleMapper;
import com.zz.service.AdminUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zZeng
 * @since 2021-11-25
 */
@Service
public class AdminUserRoleServiceImpl extends ServiceImpl<AdminUserRoleMapper, AdminUserRole> implements AdminUserRoleService {

    @Autowired
    AdminUserRoleMapper userRoleMapper;

    @Override
    public List<AdminUserRole> listAllByUid(int uid) {
        return userRoleMapper.selectList(
                new QueryWrapper<AdminUserRole>().eq("uid",uid)
        );
    }

    @Override
    public void saveRoleChanges(int uid, List<AdminRole> roles) {
        //删除当前用户的所有角色
        userRoleMapper.delete(
                new QueryWrapper<AdminUserRole>().eq("uid", uid)
        );

        //插入角色信息
        roles.forEach(r -> {
            AdminUserRole ur = new AdminUserRole();
            ur.setUid(uid);
            ur.setRid(r.getId());
            userRoleMapper.insert(ur);
        });
    }
}
