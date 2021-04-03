package com.mybatis.spring.goods.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mybatis.spring.common.responseUtil.DatagridResult;
import com.mybatis.spring.common.UUIDUtil.IDUtils;
import com.mybatis.spring.common.responseUtil.ResponseResult;
import com.mybatis.spring.enums.ResultCodeStatus;
import com.mybatis.spring.goods.mapper.TbItemDescMapper;
import com.mybatis.spring.goods.mapper.TbItemMapper;
import com.mybatis.spring.goods.mapper.TbItemParamItemMapper;
import com.mybatis.spring.goods.pojo.*;
import com.mybatis.spring.goods.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/10/18.
 */
@Service
public class ItemServiceImpl extends ServiceImpl<TbItemMapper, TbItem> implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;
    @Autowired
    private JmsTemplate jmsTemplate;

    @Resource(name="topicDestination")
    private Destination destination;


    @Override
    public TbItem getItemById(long itemId) {
        return tbItemMapper.selectByPrimaryKey(itemId);
    }

    @Override
    public DatagridResult getItemList(int page, int rows) {
        PageHelper.startPage(page, rows);
        TbItemExample example = new TbItemExample();
        example.setOrderByClause("updated desc ,created desc");
        List<TbItem> list = tbItemMapper.selectByExample(example);
        DatagridResult result = new DatagridResult();
        PageInfo pageInfo = new PageInfo<TbItem>(list);
        result.setTotal(pageInfo.getTotal());//总条数
        result.setRows(list);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult saveItem(TbItem tbItem, String desc, String itemParams) throws Exception {
        ResponseResult result = ResponseResult.build(ResultCodeStatus.FAILED.getCode(), ResultCodeStatus.FAILED.getMessage());
        if (tbItem.getId() == null) {
            //补全主键,状态,创建时间,修改时间
            long itemId = IDUtils.genItemId();//使用工具类生成商品id
            tbItem.setId(itemId);
            tbItem.setCreated(new Date());
            tbItem.setUpdated(new Date());
            tbItem.setStatus((byte) 1);//商品状态，1-正常，2-下架，3-删除',
            ResponseResult result1 = tbItemMapper.insertSelective(tbItem) > 0 ? ResponseResult.ok() : result;//保存商品
            if (result1.getStatus() != 200) {
                throw new Exception();//抛出异常,spring会捕获到这个异常.回滚事务.
            }
            TbItemDesc tbItemDesc = new TbItemDesc();
            tbItemDesc.setUpdated(new Date());
            tbItemDesc.setItemDesc(itemParams);
            tbItemDesc.setItemId(itemId);
            tbItemDesc.setCreated(new Date());
            ResponseResult result2 = tbItemDescMapper.insertSelective(tbItemDesc) > 0 ? ResponseResult.ok() : result;//保存商品详情
            if (result2.getStatus() != 200) {
                throw new Exception();//抛出异常,spring会捕获到这个异常.回滚事务.
            }
            TbItemParamItem itemParamItem = new TbItemParamItem();
            itemParamItem.setCreated(new Date());
            itemParamItem.setUpdated(new Date());
            itemParamItem.setParamData(itemParams);
            itemParamItem.setItemId(itemId);
            ResponseResult result3 = tbItemParamItemMapper.insertSelective(itemParamItem) > 0 ? ResponseResult.ok() : result;//保存商品规格
            if (result3.getStatus() != 200) {
                throw new Exception();//抛出异常,spring会捕获到这个异常.回滚事务.
            }
        } else {
            tbItem.setUpdated(new Date());
            ResponseResult responseResult = tbItemMapper.updateByPrimaryKeySelective(tbItem) >=0 ? ResponseResult.ok() : result;//修改商品
            if (responseResult.getStatus() != 200) {
                throw new Exception();//抛出异常,spring会捕获到这个异常.回滚事务.
            }
            TbItemDesc tbItemDesc = new TbItemDesc();
            tbItemDesc.setUpdated(new Date());
            tbItemDesc.setItemDesc(desc);
            tbItemDesc.setItemId(tbItem.getId());
            ResponseResult responseResult1 = tbItemDescMapper.updateByPrimaryKeySelective(tbItemDesc) >=0 ? ResponseResult.ok() : result;//修改商品详情
            if (responseResult1.getStatus() != 200) {
                throw new Exception();//抛出异常,spring会捕获到这个异常.回滚事务.
            }
            TbItemParamItem itemParamItem = new TbItemParamItem();
            itemParamItem.setUpdated(new Date());
            itemParamItem.setItemId(tbItem.getId());
            itemParamItem.setParamData(itemParams);
            TbItemParamItemExample tbItemParamItemExample = new TbItemParamItemExample();
            tbItemParamItemExample.createCriteria().andItemIdEqualTo(tbItem.getId());
            ResponseResult responseResult2 = tbItemParamItemMapper.updateByExampleSelective(itemParamItem, tbItemParamItemExample) >= 0 ? ResponseResult.ok() : result;//修改商品规格
            if (responseResult2.getStatus() != 200) {
                throw new Exception();//抛出异常,spring会捕获到这个异常.回滚事务.
            }
        }
        //发送activeMQ消息  topic
        //发送一个商品添加消息
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(tbItem.getId() + "");
                return textMessage;
            }
        });
        return ResponseResult.ok();
    }

    @Override
    public boolean updateGoodsBatchById(String[] idArray,String goodStatus) {
        int length = tbItemMapper.updateGoodsBatchById(idArray,Byte.valueOf(goodStatus));
        if (length<=idArray.length&&length>0){
            return true;
        }
        return false;
    }


}
