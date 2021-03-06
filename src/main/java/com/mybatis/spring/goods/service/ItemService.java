package com.mybatis.spring.goods.service;


import com.baomidou.mybatisplus.service.IService;
import com.mybatis.spring.common.responseUtil.DatagridResult;
import com.mybatis.spring.common.responseUtil.ResponseResult;
import com.mybatis.spring.goods.pojo.TbItem;

/**
 * Created by Administrator on 2017/10/18.
 */
public interface ItemService extends IService<TbItem> {
    public TbItem getItemById(long itemId);

    public DatagridResult getItemList(int page, int rows);

    ResponseResult saveItem(TbItem tbItem, String desc, String itemParams) throws Exception;

    boolean updateGoodsBatchById(String[] idArray,String goodStatus );
}
