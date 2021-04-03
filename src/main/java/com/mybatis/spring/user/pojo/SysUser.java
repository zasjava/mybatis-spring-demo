package com.mybatis.spring.user.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "sys_user")
public class SysUser implements Serializable {
    @TableId(value = "id",type = IdType.UUID)
    private String id;
    /**
     * 用户编码
     */
    private String usercode;
    /**
     *  用户名称
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     *  盐值
     */
    private String salt;
    /**
     *  是否锁定状态
     */
    private String locked;
}