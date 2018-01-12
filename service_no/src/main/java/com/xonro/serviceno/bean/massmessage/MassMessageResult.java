package com.xonro.serviceno.bean.massmessage;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author Alex
 * @date 2018/1/12
 */
public class MassMessageResult {
    private String errCode;
    private String errMsg;
    private String msgId;
    private String msgDataId;

    @JSONField(name = "errcode")
    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    @JSONField(name = "errmsg")
    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @JSONField(name = "msg_id")
    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @JSONField(name = "msg_data_id")
    public String getMsgDataId() {
        return msgDataId;
    }

    public void setMsgDataId(String msgDataId) {
        this.msgDataId = msgDataId;
    }
}
