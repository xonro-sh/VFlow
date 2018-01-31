package com.xonro.weixinpay.web;

import com.alibaba.fastjson.JSON;
import com.xonro.serviceno.bean.TableResponse;
import com.xonro.weixinpay.bean.BaseResponse;
import com.xonro.weixinpay.bean.PayOrderResult;
import com.xonro.weixinpay.bean.WxPayConf;
import com.xonro.weixinpay.exception.WxPayException;
import com.xonro.weixinpay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 支付服务控制器
 * @author louie
 * @date 2018-1-2
 */

@RestController
@RequestMapping(value = "/wechat")
public class PayController {
    @Autowired
    private PayService payService;

    /**
     * 统一下单
     * @param body 商品描述
     * @param tradeNo 交易类型
     * @param totalFee 标价金额
     * @param openId 用户标识
     * @return 预支付信息
     */
    @RequestMapping(value = "/payOrder")
    public PayOrderResult createPayOrder(@RequestParam String body, @RequestParam String tradeNo, @RequestParam Integer totalFee, String openId){
        return payService.payOrder(body,tradeNo, totalFee, openId);
    }

    /**
     * 更新微信支付配置
     * @param data json数据
     * @return 结果
     */
    @RequestMapping(value = "/updateWxPayConf")
    public BaseResponse updateWxPayConf(String data){
        return payService.updateWxPayConf(JSON.parseObject(data, WxPayConf.class));
    }

    /**
     * 获取微信支付配置
     * @return 结果
     */
    @RequestMapping(value = "/getWxPayConf")
    public BaseResponse getWxPayConf(){
        return payService.getWxPayConf();
    }

    @RequestMapping(value = "/getWxBill")
    public String getWxBill(String billDate, Integer page, Integer limit){
        return JSON.toJSONString(payService.getWxBill(billDate, page, limit));
    }

    @RequestMapping(value = "/getOrderByTransactionId")
    public BaseResponse getOrderByTransactionId(String transactionId){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setOk(true);
        try {
            baseResponse.setData(payService.orderQueryByTransactionId(transactionId));
        } catch (WxPayException e) {
            baseResponse.setOk(false);
            baseResponse.setCode(e.getErrorCode());
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }
}
