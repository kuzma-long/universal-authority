package com.clong.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.clong.common.result.Result;
import com.clong.model.system.SysLoginLog;
import com.clong.model.vo.SysLoginLogQueryVo;
import com.clong.system.service.LoginLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "SysLoginLog管理", tags = "SysLoginLog管理")
@RestController
@RequestMapping(value="/admin/system/sysLoginLog")
public class SysLoginLogController {
    @Autowired
    private LoginLogService loginLogService;

    @ApiOperation(value = "获取分页列表")
	@GetMapping("{page}/{limit}")
    public Result index(@PathVariable long page, @PathVariable long limit, SysLoginLogQueryVo sysLoginLogQueryVo){
        IPage<SysLoginLog> pageModel = loginLogService.selectPage(page,limit,sysLoginLogQueryVo);
        return Result.ok(pageModel);
    }
}
