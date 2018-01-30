package com.xonro.weixinpay;

import com.xonro.weixinpay.service.impl.PayConfRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
@ComponentScan(basePackages = "com.xonro")
public class WeixinPayApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeixinPayApplication.class, args);
	}

	@Bean
	public PayConfRunner schedulerRunner() {
		return new PayConfRunner();
	}
}
