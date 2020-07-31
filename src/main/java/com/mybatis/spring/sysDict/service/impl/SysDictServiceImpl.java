package com.mybatis.spring.sysDict.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.mybatis.spring.sysDict.pojo.SysDictExample;
import com.mybatis.spring.sysDict.mapper.SysDictMapper;
import com.mybatis.spring.sysDict.pojo.SysDict;
import com.mybatis.spring.sysDict.service.SysDictService;
import org.springframework.util.StringUtils;

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
            sysDictMapper.insertSelective(sysDict);
            return;
        }
        sysDictMapper.updateByPrimaryKey(sysDict);
    }

}
