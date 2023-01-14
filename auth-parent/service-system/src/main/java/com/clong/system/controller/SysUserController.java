package com.clong.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clong.common.result.Result;
import com.clong.common.utils.MD5;
import com.clong.model.system.SysRole;
import com.clong.model.system.SysUser;
import com.clong.model.vo.SysRoleQueryVo;
import com.clong.model.vo.SysUserQueryVo;
import com.clong.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author clong
 * @since 2022-12-24
 */
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {
    @Autowired
    SysUserService sysUserService;

    @ApiOperation("更改用户状态")
    @GetMapping("updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable String id, @PathVariable Integer status){
        sysUserService.updateStatus(id,status);
        return Result.ok();
    }

    @ApiOperation("用户列表")
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable Long page, @PathVariable Long limit, SysUserQueryVo sysUserQueryVo) {
        Page<SysUser> pageParam = new Page<>(page, limit);
        IPage<SysUser> pageModel = sysUserService.selectPage(pageParam, sysUserQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation("添加用户")
    @PostMapping("save")
    public Result save(@RequestBody SysUser user) {
        String encrypt = MD5.encrypt(user.getPassword());
        user.setPassword(encrypt);
        boolean isSuccess = sysUserService.save(user);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation("根据id查询")
    @GetMapping("getUser/{id}")
    public Result getUser(@PathVariable String id) {
        SysUser user = sysUserService.getById(id);
        return Result.ok(user);
    }

    @ApiOperation("修改用户")
    @PostMapping("update")
    public Result update(@RequestBody SysUser user) {
        boolean isSuccess = sysUserService.updateById(user);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation("删除用户")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id) {
        boolean isSuccess = sysUserService.removeById(id);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }
}

