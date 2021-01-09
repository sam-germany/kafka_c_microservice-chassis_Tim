package com.course.microservice.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.course.microservice.api.filter.CheckEmailFilter;

@Configuration
public class SecurityFilterConfig {

	@Bean
	public FilterRegistrationBean<CheckEmailFilter> checkEmailFilter() {

		var registrationBean = new FilterRegistrationBean<CheckEmailFilter>();

		registrationBean.setFilter(new CheckEmailFilter());
		registrationBean.addUrlPatterns("/api/chassis/security/email/*");

		return registrationBean;
	}
}
/*
to set the Filter we need to define in a class with @Configuration
their we need to create a  "new FilterRegistrationBean<CheckEmailFilter>()"
after that we have to set the Filter "registrationBean.setFilter(new CheckEmailFilter());"
and set the url means which url called by the user then this filter will be applied

 this url is called    "/api/chassis/security/email/*"

then  this filter will be applied   ".setFilter(new CheckEmailFilter())"


 */




