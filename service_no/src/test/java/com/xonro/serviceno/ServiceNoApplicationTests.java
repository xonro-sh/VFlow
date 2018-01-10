package com.xonro.serviceno;

import com.xonro.serviceno.bean.WechatArticlesMessage;
import com.xonro.serviceno.bean.WechatMessage;
import com.xonro.serviceno.helper.RedisHelper;
import com.xonro.serviceno.helper.ServiceNoHelper;
import com.xonro.serviceno.service.CustomService;
import com.xonro.serviceno.service.TokenService;
import org.assertj.core.util.Compatibility;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceNoApplicationTests {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;

    @Autowired
    private RedisHelper redisHelper;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CustomService customService;
	@Test
	public void contextLoads() {
        redisHelper.set("123", "测试");
        System.out.println("进入了方法");
        String string = redisHelper.get("123").toString();
        System.err.println("string"+string);
    }

    @Test
    public void testGetAccessToken(){
        System.err.println(""+tokenService.getTokenFromCache());
        tokenService.getTokenFromCache();
    }

    @Test
    public void testBeanToXml() {
        WechatMessage imageMessage = new WechatMessage("","222", System.currentTimeMillis()/1000, "text","23333");
        //首字母转大写
        System.err.println(ServiceNoHelper.xmlInitialUppercase(ServiceNoHelper.beanToxml(imageMessage)));
    }

    @Test
    public void testWechatMessage(){
        customService.addCustom();
//        WechatMessage wechatMessage = new WechatMessage();
//        WechatArticlesMessage wechatArticlesMessage = new WechatArticlesMessage();
//        WechatArticlesMessage wechatArticlesMessage1 = new WechatArticlesMessage();
//        wechatArticlesMessage.setDescription("11111");
//        wechatArticlesMessage1.setDescription("222");
//        List<WechatArticlesMessage> wechatArticlesMessages = new ArrayList<>();
//        wechatArticlesMessages.add(wechatArticlesMessage);
//        wechatArticlesMessages.add(wechatArticlesMessage1);
//
//        System.err.println(ServiceNoHelper.beanToxml(wechatMessage).concat( ServiceNoHelper.getArticlesXml(wechatArticlesMessages)));
    }

}
