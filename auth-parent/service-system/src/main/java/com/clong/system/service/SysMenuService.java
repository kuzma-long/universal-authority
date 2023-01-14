package com.clong.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.clong.model.system.SysMenu;
import com.clong.model.vo.AssginMenuVo;
import com.clong.model.vo.RouterVo;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author clong
 * @since 2022-12-29
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> findNodes();

    void removeMenuById(String id);

    List<SysMenu> findMenuByRoleId(String roleId);

    void doAssign(AssginMenuVo assginMenuVo);

    List<RouterVo> getUserMenuList(String id);

    List<String> getUserButtonList(String id);
}
