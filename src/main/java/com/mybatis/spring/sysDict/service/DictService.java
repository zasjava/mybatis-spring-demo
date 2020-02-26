package com.mybatis.spring.sysDict.service;

import com.mybatis.spring.sysDict.pojo.SysDict;

import java.util.List;

/**
 * @ClassName DictService
 * @Description TODO
 * @Author zhaoasong
 * @Date 2020-2-14 19:57
 **/
public interface DictService {
	SysDict findById(Long id);

	List<SysDict> findBySysDict(SysDict sysDict, Integer offset, Integer limit);

	boolean saveOrUpdate(SysDict sysDict);

	boolean deleteById(Long id);

}
