package com.xonro.report.enums;

/**
 * 报表枚举
 * @author Alex
 * @date 2018/2/11
 */
public enum ReportEnums {;

    private ReportEnums(String value,String desc){
        this.value = value;
        this.desc = desc;
    }

    private String value;
    private String desc;

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
