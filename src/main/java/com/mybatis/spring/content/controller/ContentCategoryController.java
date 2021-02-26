package com.mybatis.spring.content.controller;

import com.mybatis.spring.common.DatagridResult;
import com.mybatis.spring.common.ResponseResult;
import com.mybatis.spring.common.TreeResult;
import com.mybatis.spring.content.pojo.TbContentCategory;
import com.mybatis.spring.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    //查询分类
    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<TreeResult> getContentCategory(@RequestParam(defaultValue = "0", value = "id") long id) {
        return contentCategoryService.getContentCategoryList(id);
    }

    //保存节点
    @RequestMapping("/content/category/create")
    @ResponseBody
    public ResponseResult saveCategory(long parentId, String name) {
        try {
            contentCategoryService.saveContentCatory(parentId, name);
            return ResponseResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.build(500, "保存失败");
        }
    }
}
