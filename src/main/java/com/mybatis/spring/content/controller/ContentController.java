package com.mybatis.spring.content.controller;

import com.mybatis.spring.common.responseUtil.DatagridResult;
import com.mybatis.spring.common.responseUtil.ResponseResult;
import com.mybatis.spring.content.pojo.TbContent;
import com.mybatis.spring.content.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 内容模块管理接口
 */
@Controller
public class ContentController {

    @Autowired
    private ContentService contentService;

    /**
     *  保存内容
     * @param tbContent
     * @return
     */
    @RequestMapping(value = "/content/save" ,method = RequestMethod.POST )
    @ResponseBody
    public ResponseResult saveContent(TbContent tbContent) {
        contentService.saveContent(tbContent);
        return ResponseResult.ok();
    }

    /**
     * 根据内容类别ID，分页查找内容信息列表。
     * @param categoryId 内容类别ID
     * @param page 页数
     * @param rows 行数
     * @return
     */
    @RequestMapping(value = "/content/query/list",method = RequestMethod.GET )
    @ResponseBody
    public DatagridResult getItemList( long categoryId,@RequestParam(name = "page",defaultValue = "1") Integer page, @RequestParam(name = "rows",defaultValue = "30") Integer rows) {
        List<TbContent> contentList = contentService.getContentList(categoryId, page, rows);
        DatagridResult datagridResult = new DatagridResult();
        datagridResult.setTotal(contentList.size());
        datagridResult.setRows(contentList);
        return datagridResult;
    }
}
