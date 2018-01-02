package com.xonro.weixinpay.service.impl;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import com.xonro.weixinpay.bean.PayOrder;
import com.xonro.weixinpay.bean.PayOrderResult;
import com.xonro.weixinpay.enums.WechatEnum;
import com.xonro.weixinpay.service.PayService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.net.InetAddress;
import java.util.Map;

/**
 *  微信支付相关服务实现类
 * @author louie modified by Alex
 * @date 2018-1-2
 */
@Service
public class PayServiceImpl implements PayService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private WXPayConfig wxPayConfig;
    @Autowired
    private PayOrder payOrder;

    /**
     * 统一下单
     * @param body 商品描述
     * @param tradeNo 交易类型
     * @param totalFee 标价金额
     * @param openId 用户标识
     * @return 预支付信息
     */
    @Override
    public PayOrderResult payOrder(@NotNull String body, String tradeNo, Integer totalFee, String openId){
        WXPay wxPay = new WXPay(wxPayConfig, WXPayConstants.SignType.MD5, true);
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            Map<String, String> pppp = payOrder.getDefaultOrderDatas(body,tradeNo,totalFee,ip,openId);
            Map<String,String> resData = wxPay.unifiedOrder(pppp);
            if (validateWxPayResult(resData)){
                PayOrderResult result = new PayOrderResult();
                BeanUtils.populate(result,resData);
                return result;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    private boolean validateWxPayResult(Map<String,String> resDatas){
        if (resDatas != null && !resDatas.isEmpty()){
            String reCode = resDatas.get("return_code");
            if (StringUtils.isNotEmpty(reCode) && WechatEnum.RETURN_CODE_SUCCESS.getValue().equals(reCode)){
                return true;
            }
            logger.error(resDatas.get("return_msg"),new Throwable("request wxpay service error"));
        }else {
            logger.error("service response is empty",new Throwable("request wxpay service error"));
        }
        return false;
    }
}
