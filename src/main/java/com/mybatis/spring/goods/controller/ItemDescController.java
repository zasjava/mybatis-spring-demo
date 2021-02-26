package com.mybatis.spring.goods.controller;

import com.mybatis.spring.common.ResponseResult;
import com.mybatis.spring.common.ResultCode;
import com.mybatis.spring.goods.mapper.TbItemDescMapper;
import com.mybatis.spring.goods.pojo.TbItemDesc;
import com.mybatis.spring.goods.service.TbItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemDescController {
    @Autowired
    private TbItemDescService tbItemDescService;

    @RequestMapping("/itemDesc/{itemId}")
    @ResponseBody
    public ResponseResult getItemDescByItemId(@PathVariable Long itemId) {
        TbItemDesc tbItemDesc = tbItemDescService.getItemDescByItemId(itemId);
        ResponseResult result = new ResponseResult();
        if (!ObjectUtils.isEmpty(tbItemDesc)) {
            result.setMsg(ResultCode.SUCCESS.getMessage());
            result.setStatus(ResultCode.SUCCESS.getCode());
            result.setData(tbItemDesc);
        } else {
            result.setStatus(ResultCode.ISNULL.getCode());
            result.setMsg(ResultCode.ISNULL.getMessage());
        }
        return result;
    }
}
