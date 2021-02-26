package com.mybatis.spring.goods.controller;

import com.mybatis.spring.goods.pojo.TbItemCat;
import com.mybatis.spring.goods.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/20.
 */
@Controller
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List  getItemCatList(@RequestParam( defaultValue = "0",value = "id") Long parentId){
        List<TbItemCat> itemCatList = itemCatService.getItemCatList(parentId);
        List  resultList = new ArrayList();
        for ( TbItemCat itemS:itemCatList) {
            Map map = new HashMap();
            map.put("id",itemS.getId());
            map.put("text",itemS.getName());
            map.put("state",itemS.getIsParent()?"closed":"open");//状态,  父节点 closed,叶子节点 open
            resultList.add(map);
        }
        return  resultList;
    }

}
