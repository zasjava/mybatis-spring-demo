package com.mybatis.spring.search.controller;

import com.mybatis.spring.common.responseUtil.ResponseResult;
import com.mybatis.spring.search.service.SearchService;
import com.mybatis.spring.search.pojo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 商品信息同步接口
 */
@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping("/search")
    public ModelAndView searchItem(String keyword, @RequestParam(defaultValue = "0",value = "page") Integer page){
        SearchResult result = searchService.searchItem(keyword,page,10);
        ModelAndView mv = new ModelAndView();
        mv.addObject("query", keyword);
        mv.addObject("itemList",result.getItemList());
        mv.addObject("totalPages",result.getPageCount());
        mv.addObject("page",result.getCurPage());
        mv.addObject("recourdCount",result.getRecordCount());
        mv.setViewName("/font/search");
        return mv;
    }

    /**
     * 同步数据数据到solr服务器
     * @return
     */
    @RequestMapping("/index/item/import")
    @ResponseBody
    public ResponseResult importItemIndex(){
        return  searchService.importItemIndex();
    }
}