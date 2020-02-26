package com.mybatis.spring.sysDict.controller;

import com.mybatis.spring.sysDict.pojo.SysDict;
import com.mybatis.spring.sysDict.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.List;

/**
 * @ClassName DictController
 * @Description TODO
 * @Author zhaoasong
 * @Date 2020-2-14 20:15
 **/
@Controller
@RequestMapping("/dict")
public class DictController {
	@Autowired
	private DictService dictService;

	@RequestMapping("/list")
	public ModelAndView docts(SysDict sysDict, Integer offset, Integer limit) {
		ModelAndView mv = new ModelAndView("/dict/dict");
		List<SysDict> dicts = dictService.findBySysDict(sysDict, offset, limit);
		mv.addObject("dicts", dicts);
		return mv;
	}

	@RequestMapping(value = "goAdd",method = RequestMethod.GET)
	public ModelAndView add(Long id ) {
		ModelAndView mv = new ModelAndView("/dict/dict_add");
		SysDict sysDict;
		if (id != null && id.intValue() != 0) {
			sysDict = dictService.findById(id);
		} else {
			sysDict = new SysDict();
		}
		mv.addObject("model",sysDict);
		return mv;
	}
	@RequestMapping(value = "doAdd",method = RequestMethod.POST)
	public ModelAndView save(SysDict sysDict) {
		ModelAndView mv = new ModelAndView();
		try {
			dictService.saveOrUpdate(sysDict);
			mv.setViewName("redirect:list");
		} catch (Exception e) {
			mv.setViewName("dict/dict_add");
			mv.addObject("msg", e.getMessage());
			mv.addObject("model", sysDict);
		}
		return mv;
	}

	@RequestMapping(value = "delete")
	@ResponseBody
	public ModelMap delete(Long id) {
		ModelMap map = new ModelMap();
		try {
			boolean success = dictService.deleteById(id);
			map.put("success", success);
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
}
