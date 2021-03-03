package com.mybatis.spring.font.controller;

import com.mybatis.spring.content.pojo.TbContent;
import com.mybatis.spring.content.service.ContentService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Administrator on 2017/12/26 0026.
 */
@Controller
public class PageController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/font")
    public ModelAndView showIndex() {
        List<TbContent> list = contentService.getContentList(new Integer("89"), new Integer("1"), new Integer("10"));
        ModelAndView mv = new ModelAndView();
        mv.addObject("lists", list);
        mv.setViewName("/font/index");
        return mv;
    }
}
