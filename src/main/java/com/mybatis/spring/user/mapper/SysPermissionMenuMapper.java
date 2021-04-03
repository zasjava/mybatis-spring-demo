package com.mybatis.spring.user.mapper;


import com.mybatis.spring.user.pojo.SysPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysPermissionMenuMapper {
    //返回用户所用户的菜单
    List<SysPermission> queryByMenuPermission(@Param("permissionIds") String permissionIds);

    //权限url
    List<SysPermission> queryByPermission(@Param("permissionIds") String permissionIds);

    String queryPermissionIds(@Param("userId") String userId);
}