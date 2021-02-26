package com.mybatis.spring.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.mybatis.spring.common.JsonUtils;
import com.mybatis.spring.content.mapper.TbContentMapper;
import com.mybatis.spring.content.pojo.TbContent;
import com.mybatis.spring.content.pojo.TbContentExample;
import com.mybatis.spring.content.service.ContentService;
import com.mybatis.spring.jedis.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/12/26 0026.
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;

    @Autowired
    private JedisClient jedisClient;

    //保存广告位内容
    @Transactional
    @Override
    public void saveContent(TbContent tbContent) {
        tbContent.setUpdated(new Date());
        tbContent.setCreated(new Date());
        //删除对应的缓存数据
        jedisClient.hdel("IMAGE_INDE_AD", tbContent.getCategoryId() + "");
        tbContentMapper.insert(tbContent);
    }

    //大广告位展示
    @Override
    public List<TbContent> getContentList(long categoryId, Integer page, Integer rows) {
        //2.取redis中的数据
        String jsondatas = jedisClient.hget("IMAGE_INDE_AD", categoryId + "");
        if (!StringUtils.isEmpty(jsondatas)) {
            List<TbContent> tbContents = JsonUtils.jsonToList(jsondatas, TbContent.class);//转换为list类型
            return tbContents;
        }
        PageHelper.startPage(page, rows);
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> list = tbContentMapper.selectByExample(example);
        //1.把数据放入到缓存,字符串类型的数据
        jedisClient.hset("IMAGE_INDE_AD", categoryId + "", JsonUtils.objectToJson(list));
        return list;
    }
}
