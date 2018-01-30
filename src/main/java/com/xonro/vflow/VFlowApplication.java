package com.xonro.vflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Alex
 * @date 2018/1/29
 */
@SpringBootApplication
@EnableScheduling
@EnableCaching
@ComponentScan(basePackages = "com.xonro")
public class VFlowApplication{
	public static void main(String[] args) {
		SpringApplication.run(VFlowApplication.class, args);
	}
}
