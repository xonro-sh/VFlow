package com.xonro.vflow.enums;

/**
 * vflow枚举
 * @author Alex
 * @date 2018/1/26
 */
public enum VflowEnums {
    /**
     * 菜单常量
     */
    MENU_UP("up","菜单向上换位置"),
    MENU_DOWN("down","菜单向下换位置")
    ;

    private VflowEnums(String value,String desc){
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
