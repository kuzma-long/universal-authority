package com.clong.system.controller;

import com.clong.common.result.Result;
import com.clong.common.utils.JwtHelper;
import com.clong.common.utils.MD5;
import com.clong.model.system.SysUser;
import com.clong.model.vo.LoginVo;
import com.clong.system.exception.MyException;
import com.clong.system.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "后台登陆管理")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {
    @Autowired
    private SysUserService sysUserService;

    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo) {
        SysUser sysUser = sysUserService.getUserInfoByUserName(loginVo.getUsername());
        if (sysUser == null) {
            throw new MyException(20001, "用户不存在");
        }
        String password = loginVo.getPassword();
        String encrypt = MD5.encrypt(password);
        if (!sysUser.getPassword().equals(encrypt)) {
            throw new MyException(20002, "密码不正确");
        }
        if (sysUser.getStatus() == 0) {
            throw new MyException(20003, "用户已经被禁用");
        }
        String token = JwtHelper.createToken(sysUser.getId(), sysUser.getUsername());
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        return Result.ok(map);
    }

    @GetMapping("info")
    public Result info(HttpServletRequest request) {
        String token = request.getHeader("token");
        String username = JwtHelper.getUsername(token);
        Map<String, Object> map = sysUserService.getUserInfo(username);
        return Result.ok(map);
    }
}
