package com.xonro.serviceno.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建二维码请求
 * @author louie
 * @date 2018-1-14
 */
public class CreateQrCode {
    private Long expire_seconds;
    private String action_name;
    private Map<String,Map<String,Object>> action_info;

    /**
     * 构建创建二维码的请求模型
     * @param expireSeconds 二维码有效时长
     * @param scene 场景值
     */
    public CreateQrCode(Long expireSeconds,Object scene){
        //场景参数
        Map<String,Object> sceneMap = new HashMap<>();
        if (scene instanceof String){
            if (expireSeconds <= 0){
                //二维码类型:永久的字符串参数值
                this.action_name = "QR_LIMIT_STR_SCENE";
            }else {
                //二维码类型:临时的字符串参数值
                this.action_name ="QR_STR_SCENE";
                this.expire_seconds = expireSeconds;
            }
            sceneMap.put("scene_str",scene);
        }else if (scene instanceof Integer){
            if (expireSeconds <= 0){
                //二维码类型:永久的整型参数值
                this.action_name = "QR_LIMIT_SCENE";
            }else {
                //二维码类型:临时的整型参数值
                this.action_name ="QR_SCENE";
                this.expire_seconds = expireSeconds;
            }
            sceneMap.put("scene_id",scene);
        }

        this.action_info = new HashMap<>();
        this.action_info.put("scene",sceneMap);
    }

    public Long getExpire_seconds() {
        return expire_seconds;
    }

    public void setExpire_seconds(Long expire_seconds) {
        this.expire_seconds = expire_seconds;
    }

    public String getAction_name() {
        return action_name;
    }

    public void setAction_name(String action_name) {
        this.action_name = action_name;
    }

    public Map<String, Map<String, Object>> getAction_info() {
        return action_info;
    }

    public void setAction_info(Map<String, Map<String, Object>> action_info) {
        this.action_info = action_info;
    }
}
