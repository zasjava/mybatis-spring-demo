package com.mybatis.spring.goods.service.impl;

import com.mybatis.spring.common.jsonUtil.JsonUtils;
import com.mybatis.spring.goods.mapper.TbItemDescMapper;
import com.mybatis.spring.goods.pojo.TbItemDesc;
import com.mybatis.spring.goods.service.TbItemDescService;
import com.mybatis.spring.jedis.JedisClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbItemDescServiceImpl implements TbItemDescService {
    private static final String REDIS_ITEM_DESC_KEY = "redis_item_desc_key:";
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private JedisClient jedisClient;

    @Override
    public TbItemDesc getItemDescByItemId(Long itemId) {
        String json = jedisClient.get(REDIS_ITEM_DESC_KEY + itemId);
        if (!StringUtils.isEmpty(json)) {
            return JsonUtils.jsonToPojo(json, TbItemDesc.class);
        }
        TbItemDesc tbItemDesc = tbItemDescMapper.getTbItemDescByItemId(itemId);
        jedisClient.set(REDIS_ITEM_DESC_KEY + itemId, JsonUtils.objectToJson(tbItemDesc));
        return tbItemDesc;
    }
}
