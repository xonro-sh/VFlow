package com.xonro.serviceno.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xonro.serviceno.bean.custom.CustomArticlesMessage;
import com.xonro.serviceno.bean.custom.CustomInfo;
import com.xonro.serviceno.bean.custom.CustomMessageMain;
import com.xonro.serviceno.bean.custom.CustomServiceResult;
import com.xonro.serviceno.bean.RedisConn;
import com.xonro.serviceno.exception.WechatException;
import com.xonro.serviceno.helper.UrlBuilder;
import com.xonro.serviceno.service.CustomService;
import com.xonro.serviceno.web.RequestExecutor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex
 * @date 2018/1/10
 */
@Service
public class CustomServiceImpl implements CustomService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UrlBuilder urlBuilder;
    /**
     * 增加增加账号 最多能有100个客服
     * @param kfAccount 完整的客服账号 格式为：账号前缀@公众号微信号
     * @param nickName 客服昵称
     * @param password 客服账号登录密码，该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码
     * @return 增加客服是否成功
     */
    @Override
    public CustomServiceResult addCustom(String kfAccount, String nickName, String password) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("kf_account", kfAccount);
        jsonObject.put("nickname", nickName);
        jsonObject.put("password", StringUtils.isEmpty(password)?"": DigestUtils.md5Hex(password));
        CustomServiceResult customServiceResult;
        try {
            customServiceResult = new RequestExecutor(urlBuilder.buildCustomServiceAdd())
                    .execute(jsonObject.toJSONString())
                    .getResponseAsObject(CustomServiceResult.class);
            return customServiceResult;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
       return null;
    }

    /**
     * 修改客服账号
     *
     * @param kfAccount 完整的客服账号 格式为：账号前缀@公众号微信号
     * @param nickName  客服昵称
     * @param password  客服账号登录密码，该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码
     * @return 修改客服是否成功
     */
    @Override
    public CustomServiceResult updateCustom(@NotNull String kfAccount, @NotNull String nickName, String password) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("kf_account", kfAccount);
        jsonObject.put("nickname", nickName);
        jsonObject.put("password", StringUtils.isEmpty(password)?"": DigestUtils.md5Hex(password));
        CustomServiceResult customServiceResult;
        try {
            customServiceResult = new RequestExecutor(urlBuilder.buildCustomServiceUpdate())
                    .execute(jsonObject.toJSONString())
                    .getResponseAsObject(CustomServiceResult.class);
            return customServiceResult;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 删除客服账号
     *
     * @param kfAccount 完整的客服账号 格式为：账号前缀@公众号微信号
     * @param nickName  客服昵称
     * @param password  客服账号登录密码，该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码
     * @return 删除客服是否成功
     */
    @Override
    public CustomServiceResult delCustom(@NotNull String kfAccount,String nickName, String password) {
        CustomServiceResult customServiceResult;
        try {
            customServiceResult = new RequestExecutor(urlBuilder.buildCustomServiceDel(kfAccount, nickName, password)).execute().getResponseAsObject(CustomServiceResult.class);
            return customServiceResult;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 设置客服账号的头像
     * @param title        标题
     * @param kfAccount 完整的客服账号 格式为：账号前缀@公众号微信号
     * @return 是否成功
     */
    @Override
    public CustomServiceResult uploadHeadImg(String title,byte[] head,String kfAccount) {
        try {
            return new RequestExecutor(urlBuilder.buildCustomServiceHeadImg(kfAccount))
                    .upload(title, head)
                    .getResponseAsObject(CustomServiceResult.class);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 获取所有客服账号
     * @return 所有客服账号对象
     */
    @Override
    public List<CustomInfo> getKfList() {
        try {
            String result = new RequestExecutor(urlBuilder.buildGetKfList()).execute().getResponseAsObject(String.class);
            JSONObject json = JSON.parseObject(result);
            return JSON.parseArray(json.getString("kf_list"), CustomInfo.class);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 客服发消息
     *
     * @param customMessageMain 消息信息（bean）
     * @return 消息是否发送成功
     */
    @Override
    public CustomServiceResult customSend(CustomMessageMain customMessageMain) {
        CustomServiceResult customServiceResult;
        try {
            customServiceResult = new RequestExecutor(urlBuilder.buildCustomSend()).
                    execute(JSON.toJSONString(customMessageMain))
                    .getResponseAsObject(CustomServiceResult.class);
            return customServiceResult;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 客服输入状态
     *
     * @param toUser 普通用户（openid）
     * @param typing "Typing"：对用户下发“正在输入"状态 "CancelTyping"：取消对用户的”正在输入"状态
     * @return 是否成功
     */
    @Override
    public CustomServiceResult customTyping(String toUser, String typing) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("touser", toUser);
        jsonObject.put("command", typing);
        CustomServiceResult customServiceResult;
        try {
            customServiceResult = new RequestExecutor(urlBuilder.buildCustomServiceUpdate())
                    .execute(jsonObject.toJSONString())
                    .getResponseAsObject(CustomServiceResult.class);
            return customServiceResult;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

}
