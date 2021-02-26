package com.mybatis.spring.goods.controller;

import com.mybatis.spring.common.DatagridResult;
import com.mybatis.spring.common.ResponseResult;
import com.mybatis.spring.goods.pojo.TbItem;
import com.mybatis.spring.goods.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/10/18.
 */
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId) {
        return itemService.getItemById(itemId);
    }

    @RequestMapping("/items")
    @ResponseBody
    public DatagridResult getItemList(@RequestParam(name = "page",defaultValue = "1") Integer page, @RequestParam(name = "rows",defaultValue = "30") Integer rows) {
        return itemService.getItemList(page, rows);
    }

    //保存商品
    @RequestMapping("/item/save")
    @ResponseBody
    public ResponseResult saveItem(TbItem tbItem, String desc, String itemParams) throws Exception {
        return itemService.saveItem(tbItem, desc, itemParams);
    }
}
