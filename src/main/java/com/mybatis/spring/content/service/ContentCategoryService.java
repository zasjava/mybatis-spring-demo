package com.mybatis.spring.content.service;

import com.mybatis.spring.common.responseUtil.TreeResult;

import java.util.List;

public interface ContentCategoryService {
    List<TreeResult> getContentCategoryList(long parentId);

    void saveContentCatory(long parentId, String name);
}
