package com.mybatis.spring.sysDict.service;

import java.util.List;
import com.mybatis.spring.sysDict.pojo.SysDictExample;
import com.mybatis.spring.sysDict.pojo.SysDict;
public interface SysDictService{


    long countByExample(SysDictExample example);

    int deleteByExample(SysDictExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysDict record);

    int insertSelective(SysDict record);

    List<SysDict> selectByExample(SysDictExample example);

    SysDict selectByPrimaryKey(Long id);

    int updateByExampleSelective(SysDict record,SysDictExample example);

    int updateByExample(SysDict record,SysDictExample example);

    int updateByPrimaryKeySelective(SysDict record);

    int updateByPrimaryKey(SysDict record);

    List<SysDict> findBySysDict(SysDict sysDict, Integer offset, Integer limit);

    void saveOrUpdate(SysDict sysDict);
}
