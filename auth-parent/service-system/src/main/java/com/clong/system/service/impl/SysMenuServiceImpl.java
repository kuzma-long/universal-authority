package com.clong.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clong.model.system.SysMenu;
import com.clong.model.system.SysRoleMenu;
import com.clong.model.vo.AssginMenuVo;
import com.clong.model.vo.RouterVo;
import com.clong.system.exception.MyException;
import com.clong.system.mapper.SysMenuMapper;
import com.clong.system.mapper.SysRoleMenuMapper;
import com.clong.system.service.SysMenuService;
import com.clong.system.utils.MenuHelper;
import com.clong.system.utils.RouterHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author clong
 * @since 2022-12-29
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysMenu> findNodes() {
        List<SysMenu> sysMenuList = baseMapper.selectList(null);
        return MenuHelper.buildTree(sysMenuList);
    }

    @Override
    public void removeMenuById(String id) {
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            throw new MyException(201, "请先删除子菜单");
        }
        baseMapper.deleteById(id);
    }

    @Override
    public List<SysMenu> findMenuByRoleId(String roleId) {
        QueryWrapper<SysMenu> wrapperMenu = new QueryWrapper<>();
        wrapperMenu.eq("status", 1);
        List<SysMenu> menuList = baseMapper.selectList(wrapperMenu);
        QueryWrapper<SysRoleMenu> wrapperRoleMenu = new QueryWrapper<>();
        wrapperRoleMenu.eq("role_id", roleId);
        List<SysRoleMenu> roleMenus = sysRoleMenuMapper.selectList(wrapperRoleMenu);
        List<String> roleMenuIds = new ArrayList<>();
        for (SysRoleMenu roleMenu : roleMenus) {
            String menuId = roleMenu.getMenuId();
            roleMenuIds.add(menuId);
        }
        for (SysMenu sysMenu : menuList) {
            sysMenu.setSelect(roleMenuIds.contains(sysMenu.getId()));
        }
        return MenuHelper.buildTree(menuList);
    }

    @Override
    public void doAssign(AssginMenuVo assginMenuVo) {
        QueryWrapper<SysRoleMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", assginMenuVo.getRoleId());
        sysRoleMenuMapper.delete(wrapper);
        List<String> menuIdList = assginMenuVo.getMenuIdList();
        for (String menuId : menuIdList) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setRoleId(assginMenuVo.getRoleId());
            sysRoleMenuMapper.insert(sysRoleMenu);
        }
    }

    @Override
    public List<RouterVo> getUserMenuList(String userId) {
        List<SysMenu> sysMenuList = null;
        if (userId.equals("1")) {
            QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
            wrapper.eq("status", 1);
            wrapper.orderByAsc("sort_value");
            sysMenuList = baseMapper.selectList(wrapper);
        } else {
            sysMenuList = baseMapper.findMenuListUserId(userId);
        }
        List<SysMenu> sysMenuTreeList = MenuHelper.buildTree(sysMenuList);
        return RouterHelper.buildRouters(sysMenuTreeList);
    }

    @Override
    public List<String> getUserButtonList(String userId) {
        List<SysMenu> sysMenuList = null;
        if (userId.equals("1")) {
            sysMenuList = baseMapper.selectList(new QueryWrapper<SysMenu>().eq("status", 1));
        } else {
            sysMenuList = baseMapper.findMenuListUserId(userId);
        }
        List<String> permissionList = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList) {
            if (sysMenu.getType() == 2) {
                String perms = sysMenu.getPerms();
                permissionList.add(perms);
            }
        }
        return permissionList;
    }
}
