package com.mybatis.spring.user.controller;


import com.mybatis.spring.common.ResponseResult;
import com.mybatis.spring.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String authrity(Model model, HttpSession session, String usercode, String password, String randomcode) {
        //判断验证码是否正确
        /*String  verifyCode = (String)session.getAttribute("validateCode");
        if(!verifyCode.equals(randomcode)){
            model.addAttribute("message","验证码错误!!");
            return "error";
        }*/
        //调用方法
        ResponseResult result = userService.authrity(usercode, password);
        if (result.getStatus() == 400) {
            model.addAttribute("message", result.getMsg());
            return "/user/error";
        }
        //存入session信息
        session.setAttribute("activeUser", result.getData());
        return "redirect:/good/index";
    }
}
