package com.mybatis.spring.search.service;

import com.mybatis.spring.common.responseUtil.ResponseResult;
import com.mybatis.spring.search.pojo.SearchResult;

/**
 * Created by Administrator on 2017/12/29 0029.
 */
public interface SearchService {

    public ResponseResult importItemIndex();

    SearchResult searchItem(String queryName, Integer page, Integer pageSize);

    ResponseResult  sysnIndex(long itemId);
}
