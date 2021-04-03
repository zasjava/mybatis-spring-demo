package com.mybatis.spring.goods.controller;

import com.mybatis.spring.goods.pojo.TbItemCat;
import com.mybatis.spring.goods.service.ItemCatService;
import io.github.yedaxia.apidocs.Ignore;
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
 * 商品类别信息接口
 */
@Controller
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    /**
     *  商品分类自查询
     * @param parentId 上级ID字段
     * @return
     */
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
