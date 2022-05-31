package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.revature.filters.JwtRequestFilter;

@SpringBootApplication
public class FriendFinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(FriendFinderApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<JwtRequestFilter> logginResponseFilter() {
		FilterRegistrationBean<JwtRequestFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new JwtRequestFilter());
		registrationBean.addUrlPatterns("*");
		return registrationBean;
	}
}
