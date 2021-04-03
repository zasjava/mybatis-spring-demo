package com.mybatis.spring.goods.controller;

import com.mybatis.spring.common.responseUtil.ResponseResult;
import com.mybatis.spring.enums.ResultCodeStatus;
import com.mybatis.spring.goods.pojo.TbItemDesc;
import com.mybatis.spring.goods.service.TbItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品详情接口
 */
@Controller
public class ItemDescController {
    @Autowired
    private TbItemDescService tbItemDescService;

    /**
     *  根据商品ID，查找商品详情
     * @param itemId 商品信息ID
     * @return
     */
    @RequestMapping("/itemDesc/{itemId}")
    @ResponseBody
    public ResponseResult getItemDescByItemId(@PathVariable Long itemId) {
        TbItemDesc tbItemDesc = tbItemDescService.getItemDescByItemId(itemId);
        ResponseResult result = new ResponseResult();
        if (!ObjectUtils.isEmpty(tbItemDesc)) {
            result.setMsg(ResultCodeStatus.SUCCESS.getMessage());
            result.setStatus(ResultCodeStatus.SUCCESS.getCode());
            result.setData(tbItemDesc);
        } else {
            result.setStatus(ResultCodeStatus.ISNULL.getCode());
            result.setMsg(ResultCodeStatus.ISNULL.getMessage());
        }
        return result;
    }
}
