package com.zz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zz.entity.AdminUser;
import com.zz.result.Result;
import com.zz.result.ResultFactory;
import com.zz.service.AdminUserRoleService;
import com.zz.service.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.List;

/**
 * <p>
 *  用户管理前端控制器
 * </p>
 *
 * @author zZeng
 * @since 2021-11-25
 */
@Api(value = "UserController", tags = "用户管理控制类")
@RestController
@RequestMapping("/api")
public class AdminUserController {
    @Autowired
    AdminUserService userService;
    @Autowired
    AdminUserRoleService userRoleService;

    @ApiOperation("列出用户接口")
    @GetMapping("/admin/users")
    public Result listUsers() {
        return ResultFactory.buildSuccessResult(userService.listWithRoles());
    }

    @ApiOperation("用户分页查询接口")
    @GetMapping("/admin/user")
    public Result listPageUsers(@ApiParam("页面大小")int size,
                                @ApiParam("当前页")int page) {
        return ResultFactory.buildSuccessResult(userService.listPageWithRoles(size, page));
    }

    @ApiOperation("更新密码接口")
    @PutMapping("/admin/user/password")
    public Result resetPwd(@ApiParam("用户信息") @RequestBody @Validated AdminUser requestUser) {
        userService.resetPassword(requestUser);
        return ResultFactory.buildSuccessResult("用户密码更新成功");
    }

    @ApiOperation("编辑用户接口")
    @PutMapping("/admin/user")
    public Result editUser(@ApiParam("用户信息") @RequestBody @Validated AdminUser requestUser) {
        userService.editUser(requestUser);
        return ResultFactory.buildSuccessResult("用户信息修改成功");
    }

    @ApiOperation("删除用户接口")
    @DeleteMapping("/admin/user/delete")
    public Result deleteUser(@ApiParam("用户信息") @RequestBody @Validated AdminUser user) {
        userService.removeById(user.getId());
        return ResultFactory.buildSuccessResult("用户删除成功");
    }

    @ApiOperation("测试接口：通过Id获取用户")
    @GetMapping("/getUserTest")
    public Object index() {
        return userService.getById(1L);
    }

    @ApiOperation("测试接口：测试mp条件查询")
    @GetMapping("/testWrapper")
    public Object testWrapper() {
        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("id", 0);
        List<AdminUser> users = userService.list(queryWrapper);
        System.out.println(users);
        return ResultFactory.buildSuccessResult(users);

    }
}
