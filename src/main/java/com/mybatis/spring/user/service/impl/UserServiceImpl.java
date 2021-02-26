package com.mybatis.spring.user.service.impl;

import com.mybatis.spring.common.ResponseResult;
import com.mybatis.spring.common.ResultCode;
import com.mybatis.spring.user.mapper.SysPermissionMenuMapper;
import com.mybatis.spring.user.mapper.SysUserMapper;
import com.mybatis.spring.user.pojo.ActiveUser;
import com.mybatis.spring.user.pojo.SysPermission;
import com.mybatis.spring.user.pojo.SysUser;
import com.mybatis.spring.user.pojo.SysUserExample;
import com.mybatis.spring.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/10/19.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper userMapper;

    //注入mapper
    @Autowired
    private SysPermissionMenuMapper sysPermissionMenuMapper;

    @Override
    public ResponseResult authrity(String username, String pwd) {
        //1.根据用户名查询此用户是否存在,
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsercodeEqualTo(username);//添加查询
        List<SysUser> users = userMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(users)) {
            return ResponseResult.build(ResultCode.NOTEXISTUSER.getCode(), ResultCode.NOTEXISTUSER.getMessage());
        }

        //2.存在用户，比较密码是否正确
        SysUser sysUser = users.get(0);
        String pwd_db = sysUser.getPassword();
        String salt = sysUser.getSalt();//获取盐
        String pwd_salt = pwd + salt;//密码+盐值
        System.out.println("登录密码验证用户的盐值： "+ pwd_salt);
        String pwd_input = DigestUtils.md5DigestAsHex(pwd_salt.getBytes());//Spring带的MD5加密算法
        if (!pwd_db.equals(pwd_input)) {
            return ResponseResult.build(ResultCode.ERRORPWDORUSER.getCode(), ResultCode.ERRORPWDORUSER.getMessage());
        }

        //3. 封装数据（activeuser）
        String userId = sysUser.getId();
        List<SysPermission> permissionsMenuList = sysPermissionMenuMapper.queryByMenuPermission(userId);
        List<SysPermission> userPermission = sysPermissionMenuMapper.queryByPermission(userId);
        ActiveUser activeUser = new ActiveUser();
        activeUser.setUserid(sysUser.getId());
        activeUser.setUsercode(sysUser.getUsercode());
        activeUser.setUsername(sysUser.getUsername());
        activeUser.setMenus(permissionsMenuList);
        activeUser.setPermissions(userPermission);
        return ResponseResult.ok(activeUser);
    }
}
