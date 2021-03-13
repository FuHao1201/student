package com.project.student.common;

/**
 * 布尔值默认枚举
 */
public enum  BooleanEnum {

    YES("Y","是"),
    NO("N","否");

    /**
     * code值
     */
    private String code;

    /**
     * 字典值
     */
    private String value;

    BooleanEnum(String code,String value){
        this.code = code;
        this.value = value;
    }

    public String getCode() { return code;}

    public String getValue() { return value;}
}
