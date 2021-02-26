package com.mybatis.spring.user.service;

import com.mybatis.spring.common.ResponseResult;

/**
 * Created by Administrator on 2017/10/19.
 */
public interface UserService {
    public ResponseResult authrity(String username, String pwd);

}
