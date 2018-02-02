package com.xonro.serviceno.service;


import com.xonro.serviceno.bean.user.UserInfo;
import com.xonro.serviceno.exception.WechatException;

import java.util.List;

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

    /**
     * 保存用户信息
     * @param userInfo 需保存的用户对象，可通过获取用户信息接口获得
     */
    public void saveUser(UserInfo userInfo);

    /**
     * 删除用户
     * @param userInfo 需删除的用户信息
     */
    public void deleteUser(UserInfo userInfo);

    /**
     * 批量获取用户基本信息
     * @param openId 普通用户的标识，对当前公众号唯一
     * @return
     */
    public List<UserInfo> getUserInfoList(String openId) throws WechatException;

    /**
     * 更新批量获取的用户基本信息
     * @param openId 普通用户的标识，对当前公众号唯一
     * @return
     * @throws WechatException
     */
    public List<UserInfo> updateUserPut(String openId) throws WechatException;

    /**
     * 获取批量用户基本信息（每天更新一次缓存）
     * @param openId 普通用户的标识，对当前公众号唯一
     * @return
     * @throws WechatException
     */
    public List<UserInfo> getUser(String openId) throws WechatException;

    public List<UserInfo> updateRemark(String openId, String remark) throws WechatException;

    public List<UserInfo> updateUserPutByRemark(List<UserInfo> userInfos) throws WechatException;
    public void reload();
}
