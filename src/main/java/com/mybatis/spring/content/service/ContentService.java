package com.mybatis.spring.content.service;

import com.mybatis.spring.content.pojo.TbContent;

import java.util.List;

/**
 * Created by Administrator on 2017/12/26 0026.
 */
public interface ContentService {

    void saveContent(TbContent tbContent);

    List<TbContent> getContentList(long categoryId, Integer page, Integer rows);
}
