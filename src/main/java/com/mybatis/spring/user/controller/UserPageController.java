package com.mybatis.spring.user.controller;

import io.github.yedaxia.apidocs.Ignore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2017/10/18.
 */

@Controller
public class UserPageController {
    @RequestMapping(value = "/user/{page}", method = {RequestMethod.GET})
    public String showPage(@PathVariable String page) {
        return "/user/" + page;
    }
}
