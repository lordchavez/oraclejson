package com.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.web.service.ClienteService;
import com.web.service.ClienteServiceImpl;

@Configuration
public class AppConfig {
	
	public AppConfig() {
	}
	
	@Bean
	public ClienteService clienteService() {
		return new ClienteServiceImpl();
	}
	
//	@Bean
//	public AdminInterceptor adminInterceptor() {
//		return new AdminInterceptor();
//	}

}
