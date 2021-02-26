package com.mybatis.spring.goods.service;

import com.mybatis.spring.goods.pojo.TbItemCat;

import java.util.List;

/**
 * Created by Administrator on 2017/10/20.
 */
public interface ItemCatService {
    public List<TbItemCat> getItemCatList(long parentId);
}
