package com.xonro.serviceno.bean.massmessage;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author Alex
 * @date 2018/1/15
 */
public class MassSpeedResult {
    private Integer speed;
    private Integer realspeed;
    @JSONField(name = "speed")
    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }
    @JSONField(name = "realspeed")
    public Integer getRealspeed() {
        return realspeed;
    }

    public void setRealspeed(Integer realspeed) {
        this.realspeed = realspeed;
    }
}
