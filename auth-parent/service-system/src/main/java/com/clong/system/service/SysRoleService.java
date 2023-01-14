package com.clong.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.clong.model.system.SysRole;
import com.clong.model.vo.AssginRoleVo;
import com.clong.model.vo.SysRoleQueryVo;

import java.util.Map;

public interface SysRoleService extends IService<SysRole> {
    IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo);

    Map<String, Object> getRoleByUserId(String userId);

    void doAssign(AssginRoleVo assginRoleVo);
}
