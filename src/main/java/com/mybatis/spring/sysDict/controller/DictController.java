package com.mybatis.spring.sysDict.controller;

import com.github.pagehelper.PageInfo;
import com.mybatis.spring.common.responseUtil.CommonResult;
import com.mybatis.spring.sysDict.pojo.SysDict;
import com.mybatis.spring.sysDict.service.SysDictService;
import io.github.yedaxia.apidocs.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName DictController
 * @Description TODO
 * @Author zhaoasong
 * @Date 2020-2-14 20:15
 **/
@Ignore
@Controller
@RequestMapping("/dict")
public class DictController {
    @Autowired
    private SysDictService dictService;

    @RequestMapping(value = "/dicts", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> lists(SysDict sysDict, @RequestParam(value = "offset", defaultValue = "1") Integer offset, @RequestParam(value = "limit", defaultValue = "6") Integer limit) {
        Map<String, Object> maps = new HashMap<String, Object>();
        List<SysDict> lists = dictService.lists(sysDict, offset, limit);
        PageInfo<SysDict> pageInfo = new PageInfo<>(lists);
        maps.put("total", pageInfo.getTotal());
        maps.put("rows", pageInfo.getList());
        return maps;
    }

    /**
     *保存字典数据
     *
     * @param sysDict 字典类
     * @return
     */
    @RequestMapping(value = "saveEdit", method = {RequestMethod.POST, RequestMethod.PUT})
    @ResponseBody
    public CommonResult save(SysDict sysDict) {
        ModelAndView mv = new ModelAndView();
        try {
            dictService.saveOrUpdate(sysDict);
            return CommonResult.success();
        } catch (Exception e) {
            return CommonResult.failed("系统出现异常，请稍后重试");
        }

    }

    //删除
    @RequestMapping(value = "{dictId}", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult delete(@PathVariable(value = "dictId") Long id) {
        System.out.println(id);
        ModelMap map = new ModelMap();
        try {
            if (dictService.deleteByPrimaryKey(id) > 0 ? true : false) {
                return CommonResult.success();
            }
            return CommonResult.success(200, "数据已经删除", null);
        } catch (Exception e) {
            return CommonResult.failed("系统出现异常，请稍后重试");
        }
    }
}
