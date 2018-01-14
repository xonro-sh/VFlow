package com.xonro.serviceno;

import com.xonro.serviceno.bean.QrCode;
import com.xonro.serviceno.bean.custom.CustomInfo;
import com.xonro.serviceno.bean.WechatMessage;
import com.xonro.serviceno.helper.RedisHelper;
import com.xonro.serviceno.helper.ServiceNoHelper;
import com.xonro.serviceno.service.CustomService;
import com.xonro.serviceno.service.TokenService;
import com.xonro.serviceno.service.WechatService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
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

    @Autowired
    private WechatService wechatService;

    @Test
    public void testCreateQrCode() throws IOException {
        QrCode qrCode = wechatService.createQrCode(1000l, RandomStringUtils.randomAlphanumeric(16));
        FileUtils.writeByteArrayToFile(new File("qrcode.png"),qrCode.getQrCode());
    }

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
    }

    @Test
    public void  testCustom(){
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
    }

}
