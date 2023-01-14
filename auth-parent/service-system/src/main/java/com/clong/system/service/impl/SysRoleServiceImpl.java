package com.clong.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clong.model.system.SysRole;
import com.clong.model.system.SysUserRole;
import com.clong.model.vo.AssginRoleVo;
import com.clong.model.vo.SysRoleQueryVo;
import com.clong.system.mapper.SysRoleMapper;
import com.clong.system.mapper.SysUserRoleMapper;
import com.clong.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo) {
        IPage<SysRole> pageModel = baseMapper.selectPage(pageParam,sysRoleQueryVo);
        return pageModel;
    }

    @Override
    public Map<String, Object> getRoleByUserId(String userId) {
        List<SysRole> sysRoles = baseMapper.selectList(null);
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectList(wrapper);
        List<String> userRoleIds = new ArrayList<>();
        for (SysUserRole sysUserRole : sysUserRoles) {
            String roleId = sysUserRole.getRoleId();
            userRoleIds.add(roleId);
        }
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("allRoles",sysRoles);
        returnMap.put("userRoleIds",userRoleIds);
        return returnMap;
    }

    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",assginRoleVo.getUserId());
        sysUserRoleMapper.delete(wrapper);
        List<String> roleIdList = assginRoleVo.getRoleIdList();
        for (String roleId : roleIdList) {
            SysUserRole userRole = new SysUserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(assginRoleVo.getUserId());
            sysUserRoleMapper.insert(userRole);
        }
    }
}
