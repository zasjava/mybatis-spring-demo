package com.mybatis.spring.user.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.mybatis.spring.common.responseUtil.DatagridResult;
import com.mybatis.spring.common.responseUtil.ResponseResult;
import com.mybatis.spring.user.pojo.SysUser;
import com.mybatis.spring.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 登录接口
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //用户登录

    /**
     * 用户登录
     *
     * @return
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        String usercode = request.getParameter("usercode");
        String password = request.getParameter("password");
        String randomcode = request.getParameter("randomcode");
        //非空校验
        if (StringUtils.isEmpty(usercode) || StringUtils.isEmpty(password) || StringUtils.isEmpty(randomcode)) {
            mv.setViewName("redirect:/user/forwardLoginJSP");
            return mv;
        }
        //验证码校验
       /* String verifyCode = (String) session.getAttribute("validateCode");
        if (!verifyCode.equals(randomcode)) {
            mv.setViewName("/user/error");
            mv.addObject("message", "验证码错误");
            return mv;
        }*/
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


    /**
     * 登陆页面
     */
    @RequestMapping(value = "/forwardLoginJSP", method = RequestMethod.GET)
    public String forwardLoginJSP() {
        return "/user/login";
    }

    /**
     *  分页获取用户列表
     * @param page 当前页
     * @param rows 页数
     * @return
     */
    @RequestMapping(value = "/users")
    @ResponseBody
    public DatagridResult getusersByPage(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                         @RequestParam(name = "rows", defaultValue = "30") Integer rows) {
        DatagridResult result = new DatagridResult();
        Page<SysUser> sysUserPage = userService.selectPage(new Page<SysUser>(page, rows));
        result.setTotal(sysUserPage.getRecords().size());
        result.setRows(sysUserPage.getRecords());
        return result;
    }

    /**
     *  保存用户信息
     * @param sysUser
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public ResponseResult saveOrUpdateUser(SysUser sysUser) {
        sysUser.setSalt(sysUser.getUsername());
        sysUser.setLocked("0");
        String pwd_salt = sysUser.getPassword()+sysUser.getSalt();
        sysUser.setPassword(DigestUtils.md5DigestAsHex(pwd_salt.getBytes()));
        boolean flag = userService.insertOrUpdate(sysUser);
        ResponseResult responseResult = new ResponseResult();
        if (flag) {
            responseResult.setStatus(200);
            responseResult.setMsg("保存成功");
        }else{
            responseResult.setStatus(400);
            responseResult.setMsg("保存失败，请稍后重试！");
        }
        return responseResult;
    }
}
