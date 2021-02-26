package com.mybatis.spring.content.service;

import com.mybatis.spring.common.TreeResult;
import com.mybatis.spring.content.pojo.TbContentCategory;

import java.util.List;

public interface ContentCategoryService {
    List<TreeResult> getContentCategoryList(long parentId);

    void saveContentCatory(long parentId, String name);
}
