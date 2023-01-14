package com.clong.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.clong.model.system.SysLoginLog;
import com.clong.model.vo.SysLoginLogQueryVo;

public interface LoginLogService {
    public void recordLoginLog(String username, Integer status, String ipaddr, String message);

    IPage<SysLoginLog> selectPage(long page, long limit, SysLoginLogQueryVo sysLoginLogQueryVo);
}
