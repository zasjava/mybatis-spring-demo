package com.mybatis.spring.sysDict.controller;

import com.github.pagehelper.PageInfo;
import com.mybatis.spring.common.CommonResult;
import com.mybatis.spring.sysDict.pojo.SysDict;
import com.mybatis.spring.sysDict.service.SysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping("/lists")
    @ApiOperation(response = SysDict.class,notes = "分页查询字典列表",httpMethod = "GET",value = "/dict/lists")
    @ResponseBody
    public JSONObject lists(SysDict sysDict, @ApiParam("当前页") @RequestParam(value = "offset",defaultValue = "1") Integer offset, @ApiParam("每页显示行数") @RequestParam(value = "limit",defaultValue = "6") Integer limit){
        JSONObject jsonObject = new JSONObject();
        List<SysDict> lists =dictService.lists(sysDict, offset, limit);
        PageInfo<SysDict> pageInfo = new PageInfo<>(lists);
        jsonObject.put("total",pageInfo.getTotal());
        jsonObject.put("rows", pageInfo.getList());
        return jsonObject;
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

    @RequestMapping(value = "delete")
    @ResponseBody
    @ApiOperation(value ="/dict/delete",notes = "字典删除",httpMethod = "GET",response = ModelMap.class)
    public CommonResult delete(Long id) {
        ModelMap map = new ModelMap();
        try {
            if (dictService.deleteByPrimaryKey(id) > 0 ? true : false) {
                return CommonResult.success();
            }
            return CommonResult.success(200,"数据已经删除" ,null);
        } catch (Exception e) {
            return CommonResult.failed("系统出现异常，请稍后重试");
        }
    }
}
