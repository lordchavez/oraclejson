package com.web.config;

import com.web.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.web.service.ClienteService;
import com.web.service.ClienteServiceImpl;
import com.web.service.T3Service;
import com.web.service.T3ServiceImpl;

@Configuration
public class AppConfig {
	
	public AppConfig() {
	}

	@Bean
	public ClienteService clienteService() {
		return new ClienteServiceImpl();
	}

	@Bean
	public T3Service t3Service() {
		return new T3ServiceImpl();
	}


//	@Bean
//	public AdminInterceptor adminInterceptor() {
//		return new AdminInterceptor();
//	}

}
