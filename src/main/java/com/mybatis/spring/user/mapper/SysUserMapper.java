package com.mybatis.spring.user.mapper;


import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mybatis.spring.user.pojo.SysUser;
import com.mybatis.spring.user.pojo.SysUserExample;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper  extends BaseMapper<SysUser> {
    int countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(String id);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}