package com.mybatis.spring.goods.controller;


import com.mybatis.spring.common.DatagridResult;
import com.mybatis.spring.common.ResponseResult;
import com.mybatis.spring.goods.pojo.TbItemParam;
import com.mybatis.spring.goods.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/10/23.
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;

    //查询商品规格参数
    @RequestMapping("/list")
    @ResponseBody
    public DatagridResult getItemParam(){
        DatagridResult result = new DatagridResult();
        List<TbItemParam>  list  = itemParamService.queryByListParam();
        result.setTotal(list.size());
        result.setRows(list);
        return result;
    }
    //查询规格参数模板
    @RequestMapping("/query/itemcatid/{cid}")
    @ResponseBody
    public ResponseResult getItemByCid(@PathVariable Long cid){
            TbItemParam  tbItemParam = itemParamService.queryByCidParam(cid);
            return ResponseResult.ok(tbItemParam);
    }
    //保存规格参数
    @RequestMapping("/save/{cid}")
    @ResponseBody
    public ResponseResult saveItemParam(@PathVariable Long cid,String paramData){
        TbItemParam  tbItemParam = new TbItemParam();
        tbItemParam.setItemCatId(cid);
        tbItemParam.setParamData(paramData);
        return   itemParamService.saveItemParam(tbItemParam);
    }
    //展示规格参数
    @RequestMapping("/show/{itemId}")
    public String  showParamData(Model model,@PathVariable Long itemId){
        String paramData = itemParamService.getByItemIdParamData(itemId);
        model.addAttribute("paramData",paramData);
        return "paramdata";
    }

}
