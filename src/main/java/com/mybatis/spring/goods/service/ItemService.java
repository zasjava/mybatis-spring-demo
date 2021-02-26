package com.mybatis.spring.goods.service;


import com.mybatis.spring.common.DatagridResult;
import com.mybatis.spring.common.ResponseResult;
import com.mybatis.spring.goods.pojo.TbItem;

/**
 * Created by Administrator on 2017/10/18.
 */
public interface ItemService {
    public TbItem getItemById(long itemId);

    public DatagridResult getItemList(int page, int rows);

    ResponseResult saveItem(TbItem tbItem, String desc, String itemParams) throws Exception;
}
