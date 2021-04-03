package com.mybatis.spring.user.service;

import com.baomidou.mybatisplus.service.IService;
import com.mybatis.spring.common.responseUtil.ResponseResult;
import com.mybatis.spring.user.pojo.SysUser;

/**
 * Created by Administrator on 2017/10/19.
 */
public interface UserService extends IService<SysUser> {
    public ResponseResult authrity(String username, String pwd);
}
