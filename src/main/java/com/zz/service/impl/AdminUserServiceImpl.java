package com.zz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zz.entity.AdminUser;
import com.zz.mapper.AdminUserMapper;
import com.zz.service.AdminUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zZeng
 * @since 2021-11-25
 */
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {
    @Autowired
    AdminUserMapper adminUserMapper;

    @Override
    public AdminUser getByName(String username) {
        return null;
    }

    @Override
    public AdminUser getByNamePwd(String username, String password) {
        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("username", username)
                .eq("password", password);
        return adminUserMapper.selectOne(queryWrapper);
}
}
