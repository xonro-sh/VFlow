package com.xonro.serviceno.service;


import com.xonro.serviceno.bean.user.UserInfo;

/**
 * 用户管理相关业务服务接口
 * @author louie
 * @date 2018-01-11
 */
public interface UserService {

    /**
     * 获取用户基本信息
     * @param openId 普通用户的标识，对当前公众号唯一
     * @return
     */
    public UserInfo getUserInfo(String openId);

}
