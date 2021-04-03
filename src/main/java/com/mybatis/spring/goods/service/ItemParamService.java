package com.mybatis.spring.goods.service;


import com.mybatis.spring.common.responseUtil.ResponseResult;
import com.mybatis.spring.goods.pojo.TbItemParam;

import java.util.List;

/**
 * Created by Administrator on 2017/10/23.
 */
public interface ItemParamService {

    public List<TbItemParam>  queryByListParam();

    public TbItemParam  queryByCidParam(long cid);

    public ResponseResult saveItemParam(TbItemParam tbItemParam);

    public  String  getByItemIdParamData(long itemId);
}
