package com.zz.controller;

import com.zz.common.dto.LoginDto;
import com.zz.entity.AdminUser;
import com.zz.result.Result;
import com.zz.result.ResultFactory;
import com.zz.service.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zZeng
 * @since 2021-11-25
 */
@Slf4j
@Api(value = "LoginController", tags = "登录登出注册控制类")
@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    AdminUserService userService;

    @ApiOperation("用户登录接口")
    @PostMapping("/login")
    public Result login(@ApiParam("用户账号密码") @Validated @RequestBody LoginDto requestUser) {
        // 对html标签进行转义，防止xss攻击
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        // 封装用户的登录数据进token
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, requestUser.getPassword());
        usernamePasswordToken.setRememberMe(true);
        try {
            subject.login(usernamePasswordToken);
            AdminUser user = userService.getByName(username);
            log.info("登录成功！！！");
            return ResultFactory.buildSuccessResult(user.getUsername());
        } catch (IncorrectCredentialsException e) {
            String message = "账号或者密码错误";
            log.info(message);
            return ResultFactory.buildFailResult(message);
        } catch (UnknownAccountException e) {
            String message = "账号不存在";
            log.warn(message);
            return ResultFactory.buildFailResult(message);
        }
    }

    @ApiOperation("用户注册接口")
    @PostMapping("/register")
    public Result register(@ApiParam("用户注册信息") @Validated @RequestBody AdminUser user) {
        int status = userService.register(user);

        switch (status) {
            case 0:
                return ResultFactory.buildFailResult("错误：用户名或密码为空！");
            case 1:
                return ResultFactory.buildSuccessResult("注册成功");
            case 2:
                return ResultFactory.buildFailResult("错误：该用户已存在");
            default:
                return ResultFactory.buildFailResult("错误：未知错误");
        }
    }

    @ApiOperation("用户登出接口")
    @GetMapping("/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResultFactory.buildSuccessResult("登出成功，期待您的下次访问");
    }

    @ApiOperation("身份认证测试方法接口")
    @GetMapping("/authentication")
    public String authentication() {
        return "身份认证成功";
    }
}
