package com.mybatis.spring.goods.service;

import com.mybatis.spring.goods.pojo.TbItemParamItem;

public interface ItemParamItemService {
    TbItemParamItem getItemParamItemByItemById(Long itemId);
}
