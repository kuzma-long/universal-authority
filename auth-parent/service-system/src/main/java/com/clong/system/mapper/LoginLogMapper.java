package com.clong.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clong.model.system.SysLoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author clong
 * @since 2022-12-24
 */
@Repository
@Mapper
public interface LoginLogMapper extends BaseMapper<SysLoginLog> {
}
