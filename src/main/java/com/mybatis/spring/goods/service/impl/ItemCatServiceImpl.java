package com.mybatis.spring.goods.service.impl;


import com.mybatis.spring.goods.mapper.TbItemCatMapper;
import com.mybatis.spring.goods.pojo.TbItemCat;
import com.mybatis.spring.goods.pojo.TbItemCatExample;
import com.mybatis.spring.goods.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/10/20.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper tbItemCatMapper;
    //查询商品分类信息
    @Override
    public List<TbItemCat> getItemCatList(long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
        return list;
    }
}
