package com.xonro.serviceno.helper;

import com.xonro.serviceno.enums.WechatEnums;
import com.xonro.serviceno.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 微信公众平台请求服务url构建器
 * @author Alex
 * @date 2018/1/8
 */
@Component
public class UrlBuilder {
    @Value("${wechat.appID}")
    private String appID;

    @Value("${wechat.appSecret}")
    private String appSecret;

    @Autowired
    TokenService tokenService;

    /**
     * 构架获取accessTocken的请求url
     * @return 构建完成的请求url
     */
    public String buildGetTokenUrl(){
        return WechatEnums.URL_TOKEN.getValue()+"&appid="+ appID+"&secret="+appSecret;
    }

    /**
     * 构建获取jsapi_ticket临时票据的请求url
     * @return 构建完成的请求url
     */
    public String buildJsApiTicketUrl(){
        return WechatEnums.URL_JSAPI_TICKET.getValue()+"access_token="+tokenService.getTokenFromCache()+"&type=jsapi";
    }

    /**
     * 构建添加客服账号的请求url
     * @return 构建完成的请求url
     */
    public String buildCustomServiceAdd(){
        return WechatEnums.URL_CUSTOMSERVICE_ADD.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 构建修改客服账号的请求url
     * @return 构建完成的请求url
     */
    public String buildCustomServiceUpdate(){
        return WechatEnums.URL_CUSTOMSERVICE_UPDATE.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 构建删除改客服账号的请求url
     * @param kfAccount 完整的客服账号 格式为：账号前缀@公众号微信号
     * @param nickName  客服昵称
     * @param password  客服账号登录密码，格式为密码明文的32位加密MD5值。该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码
     * @return 构建完成的请求url
     */
    public String buildCustomServiceDel(String kfAccount,String nickName, String password){
        return WechatEnums.URL_CUSTOMSERVICE_DEL.getValue()+"access_token="+tokenService.getTokenFromCache()+"&kf_account="+kfAccount+"&nickname="+nickName+"&password="+password;
    }

    /**
     * 构建设置客服账号头像的请求url
     * @param kfAccount 完整的客服账号 格式为：账号前缀@公众号微信号
     * @return 构建完成的请求url
     */
    public String buildCustomServiceHeadImg(String kfAccount){
        return WechatEnums.URL_CUSTOMSERVICE_UPLOADHEADIMG.getValue()+"access_token="+tokenService.getTokenFromCache()+"&kf_account="+kfAccount;
    }

    /**
     * 构建获取所有客服账号的请求url
     * @return 构建完成的请求url
     */
    public String buildGetKfList(){
        return WechatEnums.URL_CUSTOMSERVICE_GETKFLIST.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 构建客服发消息的请求url
     * @return 构建完成的请求url
     */
    public String buildCustomSend(){
        return WechatEnums.URL_CUSTOMSERVICE_SEND.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 客服输入状态的请求url
     * @return 构建完成的请求url
     */
    public String buildCustomTyping(){
        return WechatEnums.URL_CUSTOMSERVICE_TYPING.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 构建根据标签进行群发的请求url
     * @return 构建完成的请求url
     */
    public String buildSendAllByTag(){
        return WechatEnums.URL_MASSMESSAGE_SENDALL.getValue()+"access_token="+tokenService.getTokenFromCache();
    }
}
