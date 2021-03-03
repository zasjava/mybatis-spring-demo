package com.mybatis.spring.search.controller;

import com.mybatis.spring.common.ResponseResult;
import com.mybatis.spring.search.service.SearchService;
import com.mybatis.spring.search.pojo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2017/12/29 0029.
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

    @RequestMapping("/index/item/import")
    @ResponseBody
    public ResponseResult importItemIndex(){
        return  searchService.importItemIndex();
    }
}