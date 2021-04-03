package com.mybatis.spring.goods.controller;

import com.mybatis.spring.common.responseUtil.ResponseResult;
import com.mybatis.spring.enums.ResultCodeStatus;
import com.mybatis.spring.goods.pojo.TbItemParamItem;
import com.mybatis.spring.goods.service.ItemParamItemService;
import io.github.yedaxia.apidocs.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Ignore
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
            result.setMsg(ResultCodeStatus.SUCCESS.getMessage());
            result.setStatus(ResultCodeStatus.SUCCESS.getCode());
            result.setData(data);
        } else {
            result.setStatus(ResultCodeStatus.ISNULL.getCode());
            result.setMsg(ResultCodeStatus.ISNULL.getMessage());
        }
        return result;
    }
}
