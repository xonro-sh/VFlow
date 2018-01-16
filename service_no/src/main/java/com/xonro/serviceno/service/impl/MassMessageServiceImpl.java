package com.xonro.serviceno.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xonro.serviceno.bean.custom.CustomServiceResult;
import com.xonro.serviceno.bean.massmessage.MassMessageResult;
import com.xonro.serviceno.bean.massmessage.MassSpeedResult;
import com.xonro.serviceno.enums.WechatEnums;
import com.xonro.serviceno.exception.WechatException;
import com.xonro.serviceno.helper.UrlBuilder;
import com.xonro.serviceno.service.MassMessageService;
import com.xonro.serviceno.web.RequestExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Alex
 * @date 2018/1/12
 */
@Service
public class MassMessageServiceImpl implements MassMessageService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UrlBuilder urlBuilder;
    /**
     * 群发消息(图文消息)
     *
     * @param isToAll           用于设定是否向全部用户发送，值为true或false，选择true该消息群发给所有用户，选择false可根据tag_id发送给指定群组的用户
     * @param tagId             群发到的标签的tag_id，参加用户管理中用户分组接口，若is_to_all值为true，可不填写tag_id
     * @param mediaId           用于群发的消息的media_id
     * @param msgType           群发的消息类型，图文消息为mpnew
     * @param sendIgnoreReprint 图文消息被判定为转载时，是否继续群发。 1为继续群发（转载），0为停止群发。 该参数默认为0。
     * @return 群发结果
     */
    @Override
    public MassMessageResult sendAllByTagId(boolean isToAll, String tagId, String mediaId, String msgType, Integer sendIgnoreReprint) {
        //提交的参数
        Map<String, Object> filterParams = new HashMap<>(2);
        filterParams.put("is_to_all", false);
        filterParams.put("tag_id", tagId);
        Map<String, Object> mpnewsParams = new HashMap<>(1);
        mpnewsParams.put("media_id", mediaId);
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("filter", filterParams);
        dataParams.put("mpnews", mpnewsParams);
        dataParams.put("msgtype", msgType);
        dataParams.put("send_ignore_reprint", sendIgnoreReprint);
        MassMessageResult massMessageResult;
        try {
            massMessageResult = new RequestExecutor(urlBuilder.buildSendAllByTag()).executePostRequest(JSON.toJSONString(dataParams),MassMessageResult.class);
            return massMessageResult;
        } catch (IOException | WechatException e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 群发消息(其他)
     *
     * @param isToAll 用于设定是否向全部用户发送，值为true或false，选择true该消息群发给所有用户，选择false可根据tag_id发送给指定群组的用户
     * @param tagId   群发到的标签的tag_id，参加用户管理中用户分组接口，若is_to_all值为true，可不填写tag_id
     * @param content 内容 也可为mediaId wxcardId
     * @param msgType 群发的消息类型，文本消息为text，语音为voice，音乐为music，图片为image，视频为video，卡券为wxcard
     * @return 群发结果
     */
    @Override
    public MassMessageResult sendAllByTagId(boolean isToAll, String tagId, String content, String msgType) {
        //提交的参数
        Map<String, Object> filterParams = new HashMap<>(2);
        filterParams.put("is_to_all", false);
        filterParams.put("tag_id", tagId);
        Map<String, Object> mpnewsParams = new HashMap<>(1);
        if (msgType.equals(WechatEnums.MSG_TYPE_TEXT.getValue())){
            mpnewsParams.put("content", content);
        } else if (msgType.equals(WechatEnums.MSG_TYPE_WXCARD.getValue())) {
            mpnewsParams.put("card_id", content);
        } else {
            mpnewsParams.put("media_id", content);
        }
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("filter", filterParams);
        dataParams.put(msgType, mpnewsParams);
        dataParams.put("msgtype", msgType);
        MassMessageResult massMessageResult;
        try {
            massMessageResult = new RequestExecutor(urlBuilder.buildSendAllByTag()).executePostRequest(JSON.toJSONString(dataParams),MassMessageResult.class);
            return massMessageResult;
        } catch (IOException | WechatException e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 根据openID列表群发消息（图文消息）
     *
     * @param toUser            openID列表
     * @param sendIgnoreReprint 图文消息被判定为转载时，是否继续群发。 1为继续群发（转载），0为停止群发。 该参数默认为0。
     * @param mediaId           用于群发的消息的media_id
     * @return 群发结果
     */
    @Override
    public MassMessageResult sendAllByOpenId(List<String> toUser, Integer sendIgnoreReprint, String mediaId) {
        //提交的参数
        Map<String, Object> mpnewsParams = new HashMap<>(1);
        mpnewsParams.put("media_id", mediaId);
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("touser", toUser);
        dataParams.put("mpnews", mpnewsParams);
        dataParams.put("msgtype", "mpnews");
        dataParams.put("send_ignore_reprint", sendIgnoreReprint);
        MassMessageResult massMessageResult;
        try {
            massMessageResult = new RequestExecutor(urlBuilder.buildSendAllByOpenId()).executePostRequest(JSON.toJSONString(dataParams),MassMessageResult.class);
            return massMessageResult;
        } catch (IOException | WechatException e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 根据openID列表群发消息（文本消息，语音消息，图片消息，卡券消息 ）
     *
     * @param toUser  openID列表
     * @param msgType 群发的消息类型，文本消息为text，语音为voice，图片为image，卡券为wxcard
     * @param content 内容 media_id card_id
     * @return 群发结果
     */
    @Override
    public MassMessageResult sendAllByOpenId(List<String> toUser, String msgType, String content) throws WechatException {
        //提交的参数
        Map<String, Object> mpnewsParams = new HashMap<>(1);
        if (msgType.equals(WechatEnums.MSG_TYPE_TEXT.getValue())){
            mpnewsParams.put("content", content);
        } else if (msgType.equals(WechatEnums.MSG_TYPE_WXCARD.getValue())){
            mpnewsParams.put("card_id", content);
        } else if (msgType.equals(WechatEnums.MSG_TYPE_VIDEO.getValue()) || msgType.equals(WechatEnums.MSG_TYPE_IMAGE.getValue())){
            mpnewsParams.put("media_id", content);
        }
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("touser", toUser);
        dataParams.put(msgType, mpnewsParams);
        dataParams.put("msgtype", msgType);
        MassMessageResult massMessageResult;
        try {
            massMessageResult = new RequestExecutor(urlBuilder.buildSendAllByOpenId()).executePostRequest(JSON.toJSONString(dataParams),MassMessageResult.class);
            return massMessageResult;
        } catch (IOException | WechatException e) {
            logger.error(e.getMessage(),e);
            throw new WechatException("002", "群发消息失败！");
        }
    }

    /**
     * 删除群发 （图文和视频消息）
     *
     * @param msgId      发送出去的消息ID
     * @param articleIdx 要删除的文章在图文消息中的位置，第一篇编号为1，该字段不填或填0会删除全部文章
     * @return 删除结果
     */
    @Override
    public MassMessageResult delMassMessage(String msgId, String articleIdx) throws WechatException {
        //提交的参数
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("msg_id", msgId);
        dataParams.put("article_idx", articleIdx);
        MassMessageResult massMessageResult;
        try {
            massMessageResult = new RequestExecutor(urlBuilder.buildDelMassMessage()).executePostRequest(JSON.toJSONString(dataParams),MassMessageResult.class);
            return massMessageResult;
        } catch (IOException | WechatException e) {
            logger.error(e.getMessage(),e);
            throw new WechatException("003", "删除群发消息失败！");
        }
    }

    /**
     * 预览群发
     *
     * @param toWxName 微信号
     * @param msgType  群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video，卡券为wxcard
     * @param content  用于群发的消息的media_id或者发送文本消息时文本的内容
     * @return 预览群发结果
     */
    @Override
    public MassMessageResult previewMassMessage(String toWxName, String msgType, String content) throws WechatException {
        //提交的参数
        Map<String, Object> mpnewsParams = new HashMap<>(1);
        if (msgType.equals(WechatEnums.MSG_TYPE_TEXT.getValue())){
            mpnewsParams.put("content", content);
        } else if (msgType.equals(WechatEnums.MSG_TYPE_WXCARD.getValue())){
            mpnewsParams.put("card_id", content);
        } else {
            mpnewsParams.put("media_id", content);
        }
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("towxname", toWxName);
        dataParams.put(msgType, mpnewsParams);
        dataParams.put("msgtype", msgType);
        MassMessageResult massMessageResult;
        try {
            massMessageResult = new RequestExecutor(urlBuilder.buildPreviewUrl()).executePostRequest(JSON.toJSONString(dataParams),MassMessageResult.class);
            return massMessageResult;
        } catch (IOException | WechatException e) {
            logger.error(e.getMessage(),e);
            throw new WechatException("004", "预览群发消息失败！");
        }
    }

    /**
     * 查询群发消息发送状态
     *
     * @param msgId 群发消息后返回的消息id
     * @return 消息状态
     */
    @Override
    public MassMessageResult getMassMessageState(String msgId) throws WechatException {
        //提交的参数
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("msg_id", msgId);
        MassMessageResult massMessageResult;
        try {
            massMessageResult = new RequestExecutor(urlBuilder.buildGetStateUrl()).executePostRequest(JSON.toJSONString(dataParams),MassMessageResult.class);
            return massMessageResult;
        } catch (IOException | WechatException e) {
            logger.error(e.getMessage(),e);
            throw new WechatException("005", "查询群发消息失败，请确认消息ID是否正确！");
        }
    }

    /**
     * 查询群发速度
     *
     * @return 群发速度
     * @throws WechatException 异常
     */
    @Override
    public MassSpeedResult getMassSpeed() throws WechatException {
        try {
            String result = new RequestExecutor(urlBuilder.buildGetMassSpeedUrl()).executeGetRequest();
            return JSON.parseObject(result, MassSpeedResult.class);
        } catch (IOException | WechatException e) {
            logger.error(e.getMessage(),e);
            throw new WechatException("006", "查询群发速度失败");
        }
    }

    /**
     * 设置群发速度
     *
     * @param speed 速度 0-4
     * @return 群发速度
     * @throws WechatException 异常
     */
    @Override
    public MassMessageResult setMassSpeed(Integer speed) throws WechatException {
        //提交的参数
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("speed", speed);
        MassMessageResult massMessageResult;
        try {
            massMessageResult = new RequestExecutor(urlBuilder.buildSetMassSpeedUrl()).executePostRequest(JSON.toJSONString(dataParams),MassMessageResult.class);
            return massMessageResult;
        } catch (IOException | WechatException e) {
            logger.error(e.getMessage(),e);
            throw new WechatException("007", "设置群发速度失败，请确认速度是否正确！");
        }
    }

}
