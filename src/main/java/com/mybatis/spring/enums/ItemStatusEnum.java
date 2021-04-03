package com.mybatis.spring.enums;

public enum ItemStatusEnum {
    NORMAL(1, "正常"),
    UP(2, "下架"),
    DELETE(3, "已删除");
    private int status;
    private String value;

    private ItemStatusEnum(int status, String value) {
        this.status = status;
        this.value = value;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
