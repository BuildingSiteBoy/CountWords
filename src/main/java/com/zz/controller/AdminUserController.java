package com.zz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zz.entity.AdminUser;
import com.zz.result.ResultFactory;
import com.zz.service.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zZeng
 * @since 2021-11-25
 */
@Api(value = "UserController", tags = "用户操作接口")
@RestController
@RequestMapping("/api")
public class AdminUserController {
    @Autowired
    AdminUserService userService;

    @ApiOperation("测试方法：通过Id获取用户")
    @GetMapping("/getUser")
    public Object index() {
        return userService.getById(1L);
    }

    @ApiOperation("测试方法：测试mp条件查询")
    @GetMapping("/testWrapper")
    public Object testWrapper() {
        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("id", 0);
        List<AdminUser> users = userService.list(queryWrapper);
        System.out.println(users);
        return ResultFactory.buildSuccessResult(users);

    }
}
