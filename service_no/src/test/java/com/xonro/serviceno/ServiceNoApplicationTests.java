package com.xonro.serviceno;

import com.xonro.serviceno.bean.custom.CustomInfo;
import com.xonro.serviceno.bean.WechatMessage;
import com.xonro.serviceno.helper.RedisHelper;
import com.xonro.serviceno.helper.ServiceNoHelper;
import com.xonro.serviceno.service.CustomService;
import com.xonro.serviceno.service.TokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

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
//
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

    @Test
    public void  testCustom(){
//        customService.addCustom("test@test01", "客服1", "123456");
//        customService.updateCustom("test@test01", "客服2", "123456");
//        customService.delCustom("test@test01", "客服2", "123456");
//        customService.uploadHeadImg(ServiceNoApplicationTests.class.getClassLoader().getResource("static/image/a67388a0gy1fiv9qdwp3vj20x40xcafo.jpg").getPath(), "123", "ceshi", "test@test01");
        List<CustomInfo> customInfos = customService.getKfList();
        System.err.println(customService.getKfList());

        String result = "{" +
                "    \"kf_list\": [" +
                "        {" +
                "            \"kf_account\": \"test1@test\", " +
                "            \"kf_nick\": \"ntest1\", " +
                "            \"kf_id\": \"1001\", " +
                "            \"kf_headimgurl\": \" http://mmbiz.qpic.cn/mmbiz/4whpV1VZl2iccsvYbHvnphkyGtnvjfUS8Ym0GSaLic0FD3vN0V8PILcibEGb2fPfEOmw/0\"" +
                "        }, " +
                "        {" +
                "            \"kf_account\": \"test2@test\", " +
                "            \"kf_nick\": \"ntest2\", " +
                "            \"kf_id\": \"1002\", " +
                "            \"kf_headimgurl\": \" http://mmbiz.qpic.cn/mmbiz/4whpV1VZl2iccsvYbHvnphkyGtnvjfUS8Ym0GSaLic0FD3vN0V8PILcibEGb2fPfEOmw /0\"" +
                "        }, " +
                "        {" +
                "            \"kf_account\": \"test3@test\", " +
                "            \"kf_nick\": \"ntest3\", " +
                "            \"kf_id\": \"1003\", " +
                "            \"kf_headimgurl\": \" http://mmbiz.qpic.cn/mmbiz/4whpV1VZl2iccsvYbHvnphkyGtnvjfUS8Ym0GSaLic0FD3vN0V8PILcibEGb2fPfEOmw /0\"" +
                "        }" +
                "    ]" +
                "}";
//        JSONObject j = JSON.parseObject(result);
//        List<CustomInfo> j1 = JSON.parseArray(j.getString("kf_list"), CustomInfo.class);
//        CustomInfo customInfo = new CustomInfo();
//        RedisConn redisConn = new RedisConn();
//        RedisConn redisConn1 = new RedisConn();
//        redisConn.setHost("1111");
//        redisConn1.setHost("1111");
//        List<RedisConn> redisConns = new ArrayList<>();
//        redisConns.add(redisConn);
//        redisConns.add(redisConn1);
//        customInfo.setKfAccount("111");
//        customInfo.setKfHeadimgurl("1111");
//        customInfo.setKfNick("111");
//        String j2 = JSON.toJSONString(customInfo);
//        System.err.println("j1"+j2);
//        CustomArticlesMessage customArticlesMessage = new CustomArticlesMessage("11","11","11","11");
//        CustomArticlesMessage customArticlesMessage1 = new CustomArticlesMessage("22","22","22","22");
//        List<CustomArticlesMessage> customArticlesMessages = new ArrayList<>();
//        customArticlesMessages.add(customArticlesMessage);
//        customArticlesMessages.add(customArticlesMessage1);
//        CustomMessageMain customMessageMain = new CustomMessageMain("11","news", customArticlesMessages, true, "21313");
//        System.err.println(JSON.toJSONString(customMessageMain));
    }

}
