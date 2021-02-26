package com.mybatis.spring.goods.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/10/18.
 */
@Controller
public class GoodPageController {

    @RequestMapping("/good/index")
    public String showIndex() {
        return "/good/index";
    }

    @RequestMapping("/good/{page}")
    public String showPage(@PathVariable String page) {
        return "/good/" + page;
    }
}
