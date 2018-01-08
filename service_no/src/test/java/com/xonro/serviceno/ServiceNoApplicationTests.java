package com.xonro.serviceno;

import com.xonro.serviceno.bean.WechatAccessToken;
import com.xonro.serviceno.helper.RedisHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceNoApplicationTests {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;

    @Autowired
    private RedisHelper redisHelper;

	@Test
	public void contextLoads() {
        redisHelper.set("123", "测试");
        System.out.println("进入了方法");
        String string = redisHelper.get("123").toString();
        System.err.println("string"+string);
    }

}
