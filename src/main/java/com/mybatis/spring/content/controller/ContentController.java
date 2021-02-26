package com.mybatis.spring.content.controller;

import com.mybatis.spring.common.DatagridResult;
import com.mybatis.spring.common.ResponseResult;
import com.mybatis.spring.common.TreeResult;
import com.mybatis.spring.content.pojo.TbContent;
import com.mybatis.spring.content.pojo.TbContentCategory;
import com.mybatis.spring.content.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/12/26 0026.
 */
@Controller
public class ContentController {

    @Autowired
    private ContentService contentService;

    //保存内容
    @RequestMapping("/content/save")
    @ResponseBody
    public ResponseResult saveContent(TbContent tbContent) {
        contentService.saveContent(tbContent);
        return ResponseResult.ok();
    }

    @RequestMapping("/content/query/list")
    @ResponseBody
    public DatagridResult getItemList( long categoryId,@RequestParam(name = "page",defaultValue = "1") Integer page, @RequestParam(name = "rows",defaultValue = "30") Integer rows) {
        List<TbContent> contentList = contentService.getContentList(categoryId, page, rows);
        DatagridResult datagridResult = new DatagridResult();
        datagridResult.setTotal(contentList.size());
        datagridResult.setRows(contentList);
        return datagridResult;
    }


}
