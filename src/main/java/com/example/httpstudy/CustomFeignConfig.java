package com.example.httpstudy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.Request;

@Configuration
public class CustomFeignConfig {

	@Bean
	public Request.Options requestOptions() {
		return new Request.Options(); // 연결 타임아웃 5초, 읽기 타임아웃 10초
	}

	@Bean
	public Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL; // 요청 및 응답에 대한 상세 로그
	}
}
