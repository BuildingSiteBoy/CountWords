package com.zz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zz.common.myPage.UserPage;
import com.zz.entity.AdminRole;
import com.zz.entity.AdminUser;
import com.zz.mapper.AdminUserMapper;
import com.zz.service.AdminRoleService;
import com.zz.service.AdminUserRoleService;
import com.zz.service.AdminUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zZeng
 * @since 2021-11-25
 */
@Slf4j
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {
    @Autowired
    AdminRoleService roleService;
    @Autowired
    AdminUserMapper adminUserMapper;
    @Autowired
    AdminUserRoleService userRoleService;

    @Override
    public List<AdminUser> listWithRoles() {
        List<AdminUser> users = list();
        users.forEach(user -> {
            List<AdminRole> roles = roleService.listRolesByUsername(user.getUsername());
            user.setRoles(roles);
        });
        return users;
    }

    @Override
    public UserPage listPageWithRoles(int size, int page) {
        //IPage<AdminUser> userPage = new Page<>(page, size);
        //IPage<AdminUser> iPage = page(userPage);
        IPage<AdminUser> iPage = page(new Page<>(page, size));
        List<AdminUser> users = iPage.getRecords();

        users.forEach(user -> {
            List<AdminRole> roles = roleService.listRolesByUsername(user.getUsername());
            user.setRoles(roles);
        });

        UserPage userPage = new UserPage();
        userPage.setPages((int)iPage.getPages());
        userPage.setTotal((int)iPage.getTotal());
        userPage.setUsers(users);

        return userPage;
    }

    @Override
    public String getPassword(String username) {
        AdminUser user = getByName(username);

        return null == user ? null : user.getPassword();
    }

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
            log.info("错误：该用户已存在");
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
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times  = 2;
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();

        user.setSalt(salt);
        user.setPassword(encodedPassword);

        adminUserMapper.insert(user);
        log.info("注册成功");
        return 1;
    }

    @Override
    public void resetPassword(AdminUser user) {
        AdminUser userIn = getByName(user.getUsername());

        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String encodePassword = new SimpleHash("md5", user.getPassword(), salt, times).toString();

        userIn.setSalt(salt);
        userIn.setPassword(encodePassword);

        adminUserMapper.updateById(userIn);
    }

    @Override
    public void editUser(AdminUser user) {
        AdminUser userIn = getByName(user.getUsername());
        userIn.setName(user.getName());
        userIn.setEmail(user.getEmail());
        userIn.setBirth(user.getBirth());
        adminUserMapper.updateById(userIn);

        if (user.getRoles() != null) {
            userRoleService.saveRoleChanges(userIn.getId(), user.getRoles());
        }
    }
}
