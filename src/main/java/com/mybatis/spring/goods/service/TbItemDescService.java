package com.mybatis.spring.goods.service;

import com.mybatis.spring.goods.pojo.TbItemDesc;

public interface TbItemDescService {
    TbItemDesc getItemDescByItemId(Long itemId);
}
