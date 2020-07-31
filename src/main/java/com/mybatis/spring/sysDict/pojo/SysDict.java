package com.mybatis.spring.sysDict.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel(value="com-mybatis-spring-sysDict-pojo-SysDict")
public class SysDict implements Serializable {
    @ApiModelProperty(value="")
    private Long id;

    /**
    * 编码
    */
    @ApiModelProperty(value="编码")
    private String code;

    /**
    * 名称
    */
    @ApiModelProperty(value="名称")
    private String name;

    /**
    * 值
    */
    @ApiModelProperty(value="值")
    private String value;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}