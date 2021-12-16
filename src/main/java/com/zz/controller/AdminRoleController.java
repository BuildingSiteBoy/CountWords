package com.zz.controller;


import com.zz.entity.AdminRole;
import com.zz.result.Result;
import com.zz.result.ResultFactory;
import com.zz.service.AdminPermsService;
import com.zz.service.AdminRolePermsService;
import com.zz.service.AdminRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  role前端控制器
 * </p>
 *
 * @author zZeng
 * @since 2021-11-25
 */
@Api(value = "RoleController", tags = "角色管理控制类")
@RestController
@RequestMapping("/api")
public class AdminRoleController {

    @Autowired
    AdminRoleService roleService;
    @Autowired
    AdminPermsService permsService;
    @Autowired
    AdminRolePermsService rolePermsService;

    @ApiOperation("列出所有角色接口")
    @GetMapping("/admin/role")
    public Result listRoles() {
        return ResultFactory.buildSuccessResult(roleService.listWithPerms());
    }

    @ApiOperation("更新角色状态接口")
    @PutMapping("/admin/role/status")
    public Result updateRoleStatus(@ApiParam("角色信息") @RequestBody AdminRole requestRole) {
        AdminRole role = roleService.updateRoleStatus(requestRole);
        String message = "用户：" + role.getNameZh() + "状态更新成功";
        return ResultFactory.buildSuccessResult(message);
    }

    @ApiOperation("修改角色信息接口")
    @PutMapping("/admin/role")
    public Result editRole(@ApiParam("角色信息") @RequestBody AdminRole role) {
        roleService.editRole(role);
        rolePermsService.savePermsChanges(role.getId(), role.getPerms());
        return ResultFactory.buildSuccessResult("角色信息修改成功");
    }

    @ApiOperation("添加角色接口")
    @PostMapping("/admin/role")
    public Result addRole(@ApiParam("角色信息") @RequestBody AdminRole role) {
        roleService.save(role);
        return ResultFactory.buildSuccessResult("角色添加成功");
    }

    @ApiOperation("列出权限接口")
    @GetMapping("/admin/role/perm")
    public Result listPerms() {
        return ResultFactory.buildSuccessResult(permsService.list());
    }

    @ApiOperation("删除角色信息接口")
    @DeleteMapping("/admin/role/delete")
    public Result deleteRole(@ApiParam("角色信息") @RequestBody AdminRole role) {
        roleService.removeById(role.getId());
        return ResultFactory.buildSuccessResult("角色信息删除成功");
    }
}
