package com.mybatis.spring.search.mapper;


import com.mybatis.spring.search.pojo.SearchPojo;

import java.util.List;

/**
 * Created by Administrator on 2017/12/29 0029.
 */
public interface SearchMapper {

    List<SearchPojo> getItemSearch();

    SearchPojo getItemById(long itemId);
}
