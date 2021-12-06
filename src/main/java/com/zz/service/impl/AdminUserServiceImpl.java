package com.zz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zz.entity.AdminUser;
import com.zz.mapper.AdminUserMapper;
import com.zz.service.AdminUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

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
        return adminUserMapper.selectOne(
                new QueryWrapper<AdminUser>().eq("username", username)
        );
    }

    @Override
    public AdminUser getByNamePwd(String username, String password) {
        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("username", username)
                .eq("password", password);
        return adminUserMapper.selectOne(queryWrapper);
}

    @Override
    public int register(AdminUser user) {
        if (null != getByName(user.getUsername())) {
            return 2;
        }

        String username = user.getUsername();
        String name = user.getName();
        String password = user.getPassword();
        String birth = user.getBirth();
        String email = user.getEmail();

        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        birth = HtmlUtils.htmlEscape(birth);
        user.setBirth(birth);
        email = HtmlUtils.htmlEscape(email);
        user.setEmail(email);

        if ("".equals(username) || "".equals(password)) {
            return 0;
        }

        //用户名不为空、且存在，默认生产16位盐
        //String salt = new SecureRandomNumberG

        return 1;
    }
}
