package com.mybatis.spring.sysDict.service.impl;

import com.mybatis.spring.sysDict.mapper.SysDictMapper;
import com.mybatis.spring.sysDict.pojo.SysDict;
import com.mybatis.spring.sysDict.pojo.SysDictExample;
import com.mybatis.spring.sysDict.service.DictService;
import com.sun.istack.internal.NotNull;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName DictServiceImpl
 * @Description TODO
 * @Author zhaoasong
 * @Date 2020-2-14 20:01
 **/
@Service
public class DictServiceImpl implements DictService {
	@Autowired
	private SysDictMapper sysDictMapper;
	@Override
	public SysDict findById(@NotNull Long id) {
		return sysDictMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SysDict> findBySysDict(SysDict sysDict, Integer offset, Integer limit) {
		return sysDictMapper.findAll();
	}

	@Override
	public boolean saveOrUpdate(SysDict sysDict) {
		if (sysDict.getId() == null) {
			return sysDictMapper.insert(sysDict) == 1;
		} else {
			return sysDictMapper.updateByPrimaryKey(sysDict) == 1;
		}
	}

	@Override
	public boolean deleteById(Long id) {
		return sysDictMapper.deleteByPrimaryKey(id) == 1;
	}
}
