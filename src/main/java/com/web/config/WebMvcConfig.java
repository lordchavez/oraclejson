package com.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	AdminInterceptor adminInterceptor;
	
	 // Static Resource Config
    @Override
    public void addResourceHandlers( ResourceHandlerRegistry registry ) {
 
    }
 
    @Override
    public void configureDefaultServletHandling( DefaultServletHandlerConfigurer configurer ) {
        configurer.enable();
    }
    
	@Override
    public void addInterceptors( InterceptorRegistry registry ) {
		registry.addInterceptor( adminInterceptor ).addPathPatterns( "/" );
	}

}
