package com.clong.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clong.model.system.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author clong
 * @since 2022-12-29
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> findMenuListUserId(@Param("userId") String userId);
}
