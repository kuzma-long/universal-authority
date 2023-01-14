package com.clong.system.utils;

import com.clong.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuHelper {
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        List<SysMenu> trees = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList) {
            if (sysMenu.getParentId() ==0){
                trees.add(findChildren(sysMenu,sysMenuList));
            }
        }
        return trees;
    }

    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> sysMenuList) {
        sysMenu.setChildren(new ArrayList<>());
        for (SysMenu menu : sysMenuList) {
            if (Long.parseLong(sysMenu.getId())==menu.getParentId()){
                if (sysMenu.getChildren()==null){
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(findChildren(menu,sysMenuList));
            }
        }
        return sysMenu;
    }
}
