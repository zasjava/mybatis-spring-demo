package com.mybatis.spring.sysDict.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * @ClassName IndexController
 * @Description TODO
 * @Author zhaoasong
 * @Date 2020-2-14 13:59
 **/
@Controller
public class IndexController {
	@RequestMapping(value = {"/","/index"})
	public ModelAndView dicts(){
		ModelAndView mv = new ModelAndView("/index/index");
		mv.addObject("now", new Date());
		return mv;
	}
}
