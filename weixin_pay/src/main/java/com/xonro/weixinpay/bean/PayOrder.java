package com.xonro.weixinpay.bean;

import com.xonro.weixinpay.helper.VariableHelper;
import com.xonro.weixinpay.service.PayConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 支付订单模型
 * @author louie modified by Alex
 * @date 2017-12-28
 */
@Component
public class PayOrder implements Serializable{
    private VariableHelper variableHelper = new VariableHelper();

    @Autowired
    private PayConfService payConfService;
    /**
     * 设备号，自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
     */
    private String device_info = "WEB";
    /**
     * 随机字符串
     */
    private String nonce_str;
    /**
     *商品描述
     */
    private String body;
    /**
     * 商品详情
     */
    private String detail;
    /**
     * 附加数据
     */
    private String attach;
    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 标价币种,符合ISO 4217标准的三位字母代码，默认人民币：CNY
     */
    private String fee_type = "CNY";
    /**
     * 标价金额
     */
    private Integer total_fee;
    /**
     * 终端IP
     */
    private String spbill_create_ip;
    /**
     * 交易起始时间
     */
    private String time_start;
    /**
     * 交易结束时间
     */
    private String time_expire;
    /**
     * 订单优惠标记
     */
    private String goods_tag;
    /**
     * 通知地址
     */
    private String notify_url;
    /**
     * 交易类型,取值如下：JSAPI，NATIVE，APP等
     */
    private String trade_type = "JSAPI";
    /**
     * 商品ID,trade_type=NATIVE时（即扫码支付），此参数必传。此参数为二维码中包含的商品ID，商户自行定义
     */
    private String product_id;
    /**
     * 指定支付方式 上传此参数no_credit--可限制用户不能使用信用卡支付
     */
    private String limit_pay;
    /**
     * 用户标识 ,trade_type=JSAPI时（即公众号支付），此参数必传
     */
    private String openid;

    public Map<String,String> getDefaultOrderDatas(String body, String tradeNo, Integer totalFee, String userIp, String openid) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        PayOrder order = new PayOrder();
        order.setBody(body);
        order.setOut_trade_no(tradeNo);
        order.setTotal_fee(totalFee);
        order.setSpbill_create_ip(userIp);
        order.setOpenid(openid);
        order.setNotify_url(payConfService.getConfFromCache().getNotifyUrl());

        return variableHelper.removeEmptyValue(order);
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    public String getGoods_tag() {
        return goods_tag;
    }

    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getLimit_pay() {
        return limit_pay;
    }

    public void setLimit_pay(String limit_pay) {
        this.limit_pay = limit_pay;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
