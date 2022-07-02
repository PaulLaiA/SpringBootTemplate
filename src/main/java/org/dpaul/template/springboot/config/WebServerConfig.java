package org.dpaul.template.springboot.config;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class WebServerConfig {
	/**
	 * @return 消息轉換器
	 */
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		return new HttpMessageConverters(new MappingJackson2HttpMessageConverter());
	}
}