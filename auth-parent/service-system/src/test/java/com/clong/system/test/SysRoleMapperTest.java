package com.clong.system.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.clong.model.system.SysRole;
import com.clong.system.mapper.SysRoleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SysRoleMapperTest {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Test
    public void testSelect(){
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_name","用户管理员");
        List<SysRole> list = sysRoleMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void deleteId(){
        int rows = sysRoleMapper.deleteById(2);
    }

    @Test
    public void update(){
        SysRole sysRole = sysRoleMapper.selectById(1);
        sysRole.setDescription("系统管理员尚硅谷");
        sysRoleMapper.updateById(sysRole);
    }

    @Test
    public void add() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("测试角色");
        sysRole.setRoleCode("testManager");
        sysRole.setDescription("测试角色");
        int rows = sysRoleMapper.insert(sysRole);
        System.out.println(rows);
    }

    @Test
    public void findAll() {
        List<SysRole> list = sysRoleMapper.selectList(null);
        list.forEach(System.out::println);
    }
}
