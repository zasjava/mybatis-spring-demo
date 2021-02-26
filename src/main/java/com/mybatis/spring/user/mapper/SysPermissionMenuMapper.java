package com.mybatis.spring.user.mapper;


import com.mybatis.spring.user.pojo.SysPermission;

import java.util.List;

public interface SysPermissionMenuMapper {
    //返回用户所用户的菜单
    List<SysPermission> queryByMenuPermission(String userId);

    //权限url
    List<SysPermission> queryByPermission(String userId);
}