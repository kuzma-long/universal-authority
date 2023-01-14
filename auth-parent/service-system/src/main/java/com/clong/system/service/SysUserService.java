package com.clong.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.clong.model.system.SysUser;
import com.clong.model.vo.SysRoleQueryVo;
import com.clong.model.vo.SysUserQueryVo;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author clong
 * @since 2022-12-24
 */
public interface SysUserService extends IService<SysUser> {

    IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo sysUserQueryVo);

    void updateStatus(String id, Integer status);

    SysUser getUserInfoByUserName(String username);

    Map<String, Object> getUserInfo(String username);
}
