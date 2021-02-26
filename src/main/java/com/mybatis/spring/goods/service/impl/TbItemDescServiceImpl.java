package com.mybatis.spring.goods.service.impl;

import com.mybatis.spring.goods.mapper.TbItemDescMapper;
import com.mybatis.spring.goods.pojo.TbItemDesc;
import com.mybatis.spring.goods.pojo.TbItemDescExample;
import com.mybatis.spring.goods.service.TbItemDescService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbItemDescServiceImpl implements TbItemDescService {
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Override
    public TbItemDesc getItemDescByItemId(Long itemId) {
        return tbItemDescMapper.getTbItemDescByItemId(itemId);
    }
}
