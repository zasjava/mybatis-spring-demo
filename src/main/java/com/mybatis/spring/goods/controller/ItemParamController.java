package com.mybatis.spring.goods.controller;


import com.mybatis.spring.common.responseUtil.DatagridResult;
import com.mybatis.spring.common.responseUtil.ResponseResult;
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
 * 商品参数列表
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;

    /**
     *  查询商品参数列表
     * @return
     */
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

    /**
     *  根据商品分类ID,查找商品参数模板
     * @param cid 商品类别ID
     * @return
     */
    @RequestMapping("/query/itemcatid/{cid}")
    @ResponseBody
    public ResponseResult getItemByCid(@PathVariable Long cid){
            TbItemParam  tbItemParam = itemParamService.queryByCidParam(cid);
            return ResponseResult.ok(tbItemParam);
    }
    //

    /**
     * 保存规格参数
     * @param cid 商品类别ID
     * @param paramData 规格参数字符串字段
     * @return
     */
    @RequestMapping("/save/{cid}")
    @ResponseBody
    public ResponseResult saveItemParam(@PathVariable Long cid,String paramData){
        TbItemParam  tbItemParam = new TbItemParam();
        tbItemParam.setItemCatId(cid);
        tbItemParam.setParamData(paramData);
        return   itemParamService.saveItemParam(tbItemParam);
    }

    /**
     * 根据商品ID，查找规格参数
     * @param model
     * @param itemId 商品ID
     * @return
     */
    @RequestMapping("/show/{itemId}")
    public String  showParamData(Model model,@PathVariable Long itemId){
        String paramData = itemParamService.getByItemIdParamData(itemId);
        model.addAttribute("paramData",paramData);
        return "paramdata";
    }

}
