package com.mybatis.spring.user.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mybatis.spring.common.responseUtil.ResponseResult;
import com.mybatis.spring.enums.ResultCodeStatus;
import com.mybatis.spring.user.mapper.SysPermissionMenuMapper;
import com.mybatis.spring.user.mapper.SysUserMapper;
import com.mybatis.spring.user.pojo.ActiveUser;
import com.mybatis.spring.user.pojo.SysPermission;
import com.mybatis.spring.user.pojo.SysUser;
import com.mybatis.spring.user.pojo.SysUserExample;
import com.mybatis.spring.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/10/19.
 */
@Service
public class UserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
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
            return ResponseResult.build(ResultCodeStatus.NOTEXISTUSER.getCode(), ResultCodeStatus.NOTEXISTUSER.getMessage());
        }

        //2.存在用户，比较密码是否正确
        SysUser sysUser = users.get(0);
        String pwd_db = sysUser.getPassword();
        String salt = sysUser.getSalt();//获取盐
        String pwd_salt = pwd + salt;//密码+盐值
        LOGGER.debug("登录密码验证用户的盐值： "+ pwd_salt);
        String pwd_input = DigestUtils.md5DigestAsHex(pwd_salt.getBytes());//Spring带的MD5加密算法
        if (!pwd_db.equals(pwd_input)) {
            return ResponseResult.build(ResultCodeStatus.ERRORPWDORUSER.getCode(), ResultCodeStatus.ERRORPWDORUSER.getMessage());
        }

        //3. 封装数据（activeuser）
        String userId = sysUser.getId();
        String permissionIds =  sysPermissionMenuMapper.queryPermissionIds(userId);
        List<SysPermission> permissionsMenuList = sysPermissionMenuMapper.queryByMenuPermission(permissionIds);
        List<SysPermission> userPermission = sysPermissionMenuMapper.queryByPermission(permissionIds);
        ActiveUser activeUser = new ActiveUser();
        activeUser.setUserid(sysUser.getId());
        activeUser.setUsercode(sysUser.getUsercode());
        activeUser.setUsername(sysUser.getUsername());
        activeUser.setMenus(permissionsMenuList);
        activeUser.setPermissions(userPermission);
        return ResponseResult.ok(activeUser);
    }

    public static void main(String[] args) {
        String pwd = "admin";
        SysUser sysUser = new SysUser();
        sysUser.setSalt("admin");
        String pwd_salt = pwd+sysUser.getSalt();
        System.out.println("加密之后的密码====》"+DigestUtils.md5DigestAsHex(pwd_salt.getBytes()));

        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        System.out.println("随机生成的ID====》"+uuid);
    }
}
