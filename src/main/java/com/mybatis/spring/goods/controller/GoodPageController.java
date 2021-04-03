package com.mybatis.spring.goods.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 后台首页接口
 */
@Controller
public class GoodPageController {
    /**
     * 跳转到后台首页
     * @return
     */
    @RequestMapping("/good/index")
    public ModelAndView showIndex() {
        return new ModelAndView("/good/index");
    }

    /**
     * 后台匹配页面{path}
     * @param page 路径页面
     * @return
     */
    @RequestMapping("/good/{page}")
    public String showPage(@PathVariable String page) {
        return "/good/" + page;
    }
}
