package com.clong.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clong.model.system.SysUser;
import com.clong.model.vo.RouterVo;
import com.clong.model.vo.SysUserQueryVo;
import com.clong.system.mapper.SysUserMapper;
import com.clong.system.service.SysMenuService;
import com.clong.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author clong
 * @since 2022-12-24
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo sysUserQueryVo) {
        return baseMapper.selectPage(pageParam, sysUserQueryVo);
    }

    @Override
    public void updateStatus(String id, Integer status) {
        SysUser user = baseMapper.selectById(id);
        user.setStatus(status);
        baseMapper.updateById(user);
    }

    @Override
    public SysUser getUserInfoByUserName(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public Map<String, Object> getUserInfo(String username) {
        SysUser sysUser = this.getUserInfoByUserName(username);
        List<RouterVo> routerVoList = sysMenuService.getUserMenuList(sysUser.getId());
        List<String> permsList = sysMenuService.getUserButtonList(sysUser.getId());
        Map<String, Object> result = new HashMap<>();
        result.put("name", username);
        result.put("avatar", "https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
        result.put("roles", "[\"admin\"]");
        result.put("routers", routerVoList);
        result.put("buttons", permsList);
        return result;
    }
}
