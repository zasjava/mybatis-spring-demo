package com.mybatis.spring.sysDict.service.impl;

import com.github.pagehelper.PageHelper;
import com.mybatis.spring.sysDict.mapper.SysDictMapper;
import com.mybatis.spring.sysDict.pojo.SysDict;
import com.mybatis.spring.sysDict.pojo.SysDictExample;
import com.mybatis.spring.sysDict.service.SysDictService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysDictServiceImpl implements SysDictService{

    @Resource
    private SysDictMapper sysDictMapper;

    @Override
    public long countByExample(SysDictExample example) {
        return sysDictMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(SysDictExample example) {
        return sysDictMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return sysDictMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysDict record) {
        return sysDictMapper.insert(record);
    }

    @Override
    public int insertSelective(SysDict record) {
        return sysDictMapper.insertSelective(record);
    }

    @Override
    public List<SysDict> selectByExample(SysDictExample example) {
        return sysDictMapper.selectByExample(example);
    }

    @Override
    public SysDict selectByPrimaryKey(Long id) {
        return sysDictMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(SysDict record,SysDictExample example) {
        return sysDictMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(SysDict record,SysDictExample example) {
        return sysDictMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(SysDict record) {
        return sysDictMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysDict record) {
        return sysDictMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SysDict> findBySysDict(SysDict sysDict, Integer offset, Integer limit) {
        return sysDictMapper.selectByExample(null);
    }

    @Override
    public void saveOrUpdate(SysDict sysDict) {
        Long id = sysDict.getId();
        if (StringUtils.isEmpty(id)) {
            int i = sysDictMapper.insertSelective(sysDict);
            return;
        }
        sysDictMapper.updateByPrimaryKey(sysDict);
    }

    @Override
    public List<SysDict> lists(SysDict sysDict, Integer offset, Integer limit) {
        PageHelper.startPage(offset, limit);
        SysDictExample example = new SysDictExample();
        SysDictExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(sysDict.getName())) {
            criteria.andNameEqualTo(sysDict.getName());
        }
        if (!StringUtils.isEmpty(sysDict.getCode())) {
            criteria.andNameEqualTo(sysDict.getCode());
        }
        if (!StringUtils.isEmpty(sysDict.getValue())) {
            criteria.andNameEqualTo(sysDict.getValue());
        }
        return sysDictMapper.selectByExample(example);
    }

}
