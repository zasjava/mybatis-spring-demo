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
@Api(value = "/", tags = "首页访问")
@Controller
public class IndexController {

	//登录页面跳转
	@RequestMapping("/")
	public String loginJSP(){
		return "/user/login";
	}

	@ApiOperation(value = "跳转页面",notes = "模块首页跳转",httpMethod = "GET" ,response = String.class)
	@RequestMapping(value = "/{path}/index" ,method = RequestMethod.GET)
	public String commonIndex(@PathVariable("path") String name) {
		String path = "/"+name+"/"+name;
		return path;
	}
}
