package com.mybatis.spring.sysDict.controller;

import com.mybatis.spring.sysDict.pojo.SysDict;
import com.mybatis.spring.sysDict.service.SysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @ClassName DictController
 * @Description TODO
 * @Author zhaoasong
 * @Date 2020-2-14 20:15
 **/
@Controller
@RequestMapping("/dict")
@Api(value = "/dict",tags = "字典模块")
public class DictController {
    @Autowired
    private SysDictService dictService;

    @RequestMapping("/list")
    @ApiOperation(response = ModelAndView.class,notes = "字段页面",httpMethod = "GET",value = "/dict/list")
    public ModelAndView docts(SysDict sysDict, Integer offset, Integer limit) {
        ModelAndView mv = new ModelAndView("/dict/dict");
        List<SysDict> dicts = dictService.findBySysDict(sysDict, offset, limit);
        mv.addObject("dicts", dicts);
        return mv;
    }

    @RequestMapping(value = "goEdit", method = RequestMethod.GET)
    @ApiOperation(value = "/dict/goEdit" ,notes = "跳转到编辑字典页面",httpMethod = "GET",response = ModelAndView.class)
    public ModelAndView add(Long id) {
        ModelAndView mv = new ModelAndView("/dict/dict_add");
        SysDict sysDict;
        if (id != null && id.intValue() != 0) {
            sysDict = dictService.selectByPrimaryKey(id);
        } else {
            sysDict = new SysDict();
        }
        mv.addObject("model", sysDict);
        return mv;
    }

    @RequestMapping(value = "saveEdit", method = RequestMethod.POST)
    @ApiOperation(value ="/dict/saveEdit",notes = "保存字典编辑",httpMethod = "POST",response = ModelAndView.class)
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
    @ApiOperation(value ="/dict/delete",notes = "字典删除",httpMethod = "GET",response = ModelMap.class)
    public ModelMap delete(Long id) {
        ModelMap map = new ModelMap();
        try {
            int i = dictService.deleteByPrimaryKey(id);
            boolean flag = i > 0 ? true : false;
            map.put("success", flag);
        } catch (Exception e) {
            map.put("success", false);
            map.put("msg", e.getMessage());
        }
        return map;
    }
}
