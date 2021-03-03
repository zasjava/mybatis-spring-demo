package com.mybatis.spring.user.controller;


import com.mybatis.spring.common.ResponseResult;
import com.mybatis.spring.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/10/19.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    //用户登录
    @RequestMapping("/user/login")
    public ModelAndView authrity(HttpSession session, String usercode, String password, String randomcode) {
        ModelAndView mv = new ModelAndView();
        //非空校验
        if (StringUtils.isEmpty(usercode) || StringUtils.isEmpty(password) || StringUtils.isEmpty(randomcode)) {
            mv.setViewName("redirect:/");
            return mv;
        }
        //验证码校验
        String verifyCode = (String) session.getAttribute("validateCode");
        if (!verifyCode.equals(randomcode)) {
            mv.setViewName("/user/error");
            mv.addObject("message", "验证码错误");
            return mv;
        }
        //登录
        ResponseResult result = userService.authrity(usercode, password);
        if (result.getStatus() == 400) {
            mv.addObject("message", result.getMsg());
            mv.setViewName("/user/error");
            return mv;
        }
        //用户信息存入session
        session.setAttribute("activeUser", result.getData());
        mv.setViewName("redirect:/good/index");
        return mv;
    }
}
