package com.mybatis.spring.index;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "/", tags = "首页访问")
public class IndexController {
	@RequestMapping(value = {"/"})
	@ApiOperation(value = "首页", notes = "去首页页面", httpMethod = "GET", response = ModelAndView.class)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("now", new Date());
		return mv;
	}
}
