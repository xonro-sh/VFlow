package com.xonro.weixinpay;

import com.xonro.weixinpay.bean.RefundByOutTradeNo;
import com.xonro.weixinpay.bean.RefundByTransactionId;
import com.xonro.weixinpay.exception.WxPayException;
import com.xonro.weixinpay.helper.AesHelper;
import com.xonro.weixinpay.service.PayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Security;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeixinPayApplicationTests {
	@Autowired
	private PayService payService;
	/**
	 * 商户号支付key
	 */
//	@Value("${wechat.pay.key}")
//	private String payKey;

	@Test
	public void contextLoads() throws WxPayException {
//		//用微信订单号测试查询订单
		System.err.println(payService.orderQueryByTransactionId("4200000033201712272251643207"));;
//		//用商户订单号测试查询订单
//		payService.orderQueryByOutTradeNo("WedoOrderCfp_201712000000000503");
//		//用商户订单号测试关闭订单
//		payService.closeOrderByOutTradeNo("WedoOrderCfp_201712000000000503");
		//用微信订单号测试退款
//		System.err.println(payService.refundByTransactionId(new RefundByTransactionId.Builder("4200000033201712272251643207", "WedoRefund_2017120000000000003",10,10).refundFeeType("CNY").build()));
//		System.err.println(payService.refundByOutTradeNo(new RefundByOutTradeNo.Builder("WedoOrderCfp_201712000000000503", "WedoRefund_2017120000000000003",10,10).build()));

//		payService.refundByTransactionId("4200000033201712272251643207", "WedoRefund_2017120000000000003",
//				10,10,"","","");

//		//下载对账单
//		payService.downloadBill("20171228", " ALL");
//		//用微信订单号查询退款
//		payService.refundQueryByTransactionId("4200000033201712272251643207", "");
//		AesHelper aesHelper =new AesHelper();
//		try {
//			System.err.println(aesHelper.encryptData("2345", payKey));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}
