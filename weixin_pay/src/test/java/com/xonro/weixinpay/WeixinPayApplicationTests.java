package com.xonro.weixinpay;

import com.xonro.weixinpay.service.PayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeixinPayApplicationTests {
	@Autowired
	private PayService payService;
	@Test
	public void contextLoads() {
//		//用微信订单号测试查询订单
//		payService.orderQueryByTransactionId("4200000033201712272251643207");
//		//用商户订单号测试查询订单
//		payService.orderQueryByOutTradeNo("WedoOrderCfp_201712000000000503");
//		//用商户订单号测试关闭订单
//		payService.closeOrderByOutTradeNo("WedoOrderCfp_201712000000000503");
		//用微信订单号测试退款
		payService.refundByTransactionId("4200000033201712272251643207", "WedoRefund_2017120000000000003",
				10,10,"","","");
	}

}
