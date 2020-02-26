package com.mybatis.spring.sysDict.mapper;

import com.mybatis.spring.sysDict.pojo.SysDict;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SysDictMapper {
    @Delete("delete from sys_dict where id = #{id}")
    int deleteByPrimaryKey(Long id);

    @Insert("insert into sys_dict (code,name,value) values(#{code} ,#{name} ,#{value})")
    int insert(SysDict record);

    int insertSelective(SysDict record);

    @Select("select * from sys_dict where id = #{id}")
    SysDict selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDict record);
    @Update("update sys_dict set id = #{id},name=#{name},code=#{code},value=#{value} where id = #{id}")
    int updateByPrimaryKey(SysDict record);

    @Select("select  * from sys_dict")
    List<SysDict> findAll();
}