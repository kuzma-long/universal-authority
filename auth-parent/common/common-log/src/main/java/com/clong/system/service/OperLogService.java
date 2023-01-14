package com.clong.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.clong.model.system.SysOperLog;
import com.clong.model.vo.SysOperLogQueryVo;

public interface OperLogService {
    public void saveSysLog(SysOperLog sysOperLog);

    IPage<SysOperLog> selectPage(Long page, Long limit, SysOperLogQueryVo sysOperLogQueryVo);
}
