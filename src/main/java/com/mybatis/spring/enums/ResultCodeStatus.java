package com.mybatis.spring.enums;

/**
 * 枚举了一些常用API操作码
 * Created by macro on 2019/4/19.
 */

public enum ResultCodeStatus implements IErrorCode {
    SUCCESS(200, "操作成功"),
    ISNULL(201, "结果为null"),
    FAILED(500, "操作失败"),
    NOTEXISTUSER(400,"此用户不存在"),
    ERRORPWDORUSER(401,"用户名或者密码错误"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");

    private Integer code;
    private String message;

    private ResultCodeStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
