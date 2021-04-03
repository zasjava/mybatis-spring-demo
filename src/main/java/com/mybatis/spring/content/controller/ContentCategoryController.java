package com.mybatis.spring.content.controller;

import com.mybatis.spring.common.responseUtil.ResponseResult;
import com.mybatis.spring.common.responseUtil.TreeResult;
import com.mybatis.spring.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 内容分类管理接口
 */
@Controller
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    /**
     * 根据内容类别父ID，查找下一级内容类别信息
     * @param id 内容类别父ID
     * @return
     */
    @RequestMapping(value = "/content/category/list",method = RequestMethod.GET)
    @ResponseBody
    public List<TreeResult> getContentCategory(@RequestParam(defaultValue = "0", value = "id") long id) {
        return contentCategoryService.getContentCategoryList(id);
    }

    /**
     * 保存内容节点
     * @param parentId 内容类别父ID
     * @param name 节点名称
     * @return
     */
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
