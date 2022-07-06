package org.dpaul.template.springboot.config;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebServerConfig implements WebMvcConfigurer {
	/**
	 * @return 消息轉換器
	 */
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		return new HttpMessageConverters(new MappingJackson2HttpMessageConverter());
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(0, new MappingJackson2HttpMessageConverter());
	}
}