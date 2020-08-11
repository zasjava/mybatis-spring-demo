package com.mybatis.spring.index;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName IndexController
 * @Description TODO
 * @Author zhaoasong
 * @Date 2020-2-14 13:59
 **/
@Controller
@Api(value = "/", tags = "首页访问")
public class IndexController {
	@RequestMapping(value = {"/"},method = RequestMethod.GET)
	@ApiOperation(value = "首页", notes = "去首页页面", httpMethod = "GET", response = ModelAndView.class)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/{path}/index" ,method = RequestMethod.GET)
	@ApiOperation(value = "跳转页面",notes = "模块首页跳转",httpMethod = "GET" ,response = String.class)
	public String commonIndex(@PathVariable("path") String name) {
		String path = "/"+name+"/"+name;
		return path;
	}
}
