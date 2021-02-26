package com.mybatis.spring.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/10/18.
 */
@Controller
public class UserPageController {
    @RequestMapping("/user/{page}")
    public String showPage(@PathVariable String page) {
        return "/user/" + page;
    }
}
