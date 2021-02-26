package com.mybatis.spring.goods.controller;

import com.mybatis.spring.common.ResponseResult;
import com.mybatis.spring.common.ResultCode;
import com.mybatis.spring.goods.pojo.TbItemParamItem;
import com.mybatis.spring.goods.service.ItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemParamItemController {
    @Autowired
    private ItemParamItemService itemParamItemService;

    @RequestMapping("/itemParamItem/{itemId}")
    @ResponseBody
    public ResponseResult getItemParamItemByItemById(@PathVariable Long itemId) {
        TbItemParamItem data = itemParamItemService.getItemParamItemByItemById(itemId);
        ResponseResult result = new ResponseResult();
        if (!ObjectUtils.isEmpty(data)) {
            result.setMsg(ResultCode.SUCCESS.getMessage());
            result.setStatus(ResultCode.SUCCESS.getCode());
            result.setData(data);
        } else {
            result.setStatus(ResultCode.ISNULL.getCode());
            result.setMsg(ResultCode.ISNULL.getMessage());
        }
        return result;
    }
}
