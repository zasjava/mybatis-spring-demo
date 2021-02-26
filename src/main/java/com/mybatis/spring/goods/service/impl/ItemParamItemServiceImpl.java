package com.mybatis.spring.goods.service.impl;

import com.mybatis.spring.goods.mapper.TbItemParamItemMapper;
import com.mybatis.spring.goods.pojo.TbItemParamItem;
import com.mybatis.spring.goods.service.ItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {
@Autowired
private TbItemParamItemMapper tbItemParamItemMapper;
    @Override
    public TbItemParamItem getItemParamItemByItemById(Long itemId) {
        return tbItemParamItemMapper.getItemParamItemByItemById(itemId);
    }
}
