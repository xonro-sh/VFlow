package com.xonro.serviceno.service;

import com.xonro.serviceno.bean.massmessage.MassMessageResult;
import com.xonro.serviceno.exception.WechatException;

import java.util.List;

/**
 * 群发消息业务相关接口
 * @author Alex
 * @date 2018/1/12
 */
public interface MassMessageService {
    /**
     * 群发消息(图文消息)
     * @param isToAll 用于设定是否向全部用户发送，值为true或false，选择true该消息群发给所有用户，选择false可根据tag_id发送给指定群组的用户
     * @param tagId 群发到的标签的tag_id，参加用户管理中用户分组接口，若is_to_all值为true，可不填写tag_id
     * @param mediaId 用于群发的消息的media_id
     * @param msgType 群发的消息类型，图文消息为mpnew
     * @param sendIgnoreReprint 图文消息被判定为转载时，是否继续群发。 1为继续群发（转载），0为停止群发。 该参数默认为0。
     * @return 群发结果
     */
    MassMessageResult sendAllByTagId(boolean isToAll, String tagId, String mediaId, String msgType, Integer sendIgnoreReprint);

    /**
     * 群发消息(其他)
     * @param isToAll 用于设定是否向全部用户发送，值为true或false，选择true该消息群发给所有用户，选择false可根据tag_id发送给指定群组的用户
     * @param tagId 群发到的标签的tag_id，参加用户管理中用户分组接口，若is_to_all值为true，可不填写tag_id
     * @param content 内容 也可为mediaId wxcardId
     * @param msgType 群发的消息类型，文本消息为text，语音为voice，音乐为music，图片为image，视频为video，卡券为wxcard
     * @return 群发结果
     */
    MassMessageResult sendAllByTagId(boolean isToAll, String tagId, String content, String msgType);

    /**
     * 根据openID列表群发消息（图文消息）
     * @param toUser openID列表
     * @param sendIgnoreReprint 图文消息被判定为转载时，是否继续群发。 1为继续群发（转载），0为停止群发。 该参数默认为0。
     * @param mediaId 用于群发的消息的media_id
     * @return 群发结果
     */
    MassMessageResult sendAllByOpenId(List<String> toUser, Integer sendIgnoreReprint, String mediaId);

    /**
     * 根据openID列表群发消息（文本消息，语音消息，图片消息，卡券消息 ）
     * @param toUser openID列表
     * @param msgType  群发的消息类型，文本消息为text，语音为voice，图片为image，卡券为wxcard
     * @param content 内容 media_id card_id
     * @return 群发结果
     */
    MassMessageResult sendAllByOpenId(List<String> toUser, String msgType, String content) throws WechatException;
}
