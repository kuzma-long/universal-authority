package com.clong.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clong.model.system.SysOperLog;
import com.clong.model.vo.SysOperLogQueryVo;
import com.clong.system.mapper.OperLogMapper;
import com.clong.system.service.OperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class OperLogServiceImpl implements OperLogService {
    @Autowired
    private OperLogMapper operLogMapper;

    @Override
    public void saveSysLog(SysOperLog sysOperLog) {
        operLogMapper.insert(sysOperLog);
    }

    @Override
    public IPage<SysOperLog> selectPage(Long page, Long limit, SysOperLogQueryVo sysOperLogQueryVo) {
        Page<SysOperLog> pageParam = new Page<>(page, limit);
        String operName = sysOperLogQueryVo.getOperName();
        String title = sysOperLogQueryVo.getTitle();
        String createTimeBegin = sysOperLogQueryVo.getCreateTimeBegin();
        String createTimeEnd = sysOperLogQueryVo.getCreateTimeEnd();
        QueryWrapper<SysOperLog> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(title)) {
            wrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(operName)) {
            wrapper.like("oper_name", operName);
        }
        if (!StringUtils.isEmpty(createTimeBegin)) {
            wrapper.like("create_time", createTimeBegin);
        }
        if (!StringUtils.isEmpty(createTimeEnd)) {
            wrapper.like("create_time", createTimeEnd);
        }
        return operLogMapper.selectPage(pageParam, wrapper);
    }
}
