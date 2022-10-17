package com.rydzwr;

import com.rydzwr.filter.LoggedUsersFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.FilterRegistration;

@SpringBootApplication
public class UserAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthApplication.class, args);
	}

	@Bean
	FilterRegistrationBean<LoggedUsersFilter> usersFilterFilterRegistrationBean() {
		final FilterRegistrationBean<LoggedUsersFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
		filterFilterRegistrationBean.setFilter(new LoggedUsersFilter());
		filterFilterRegistrationBean.addUrlPatterns("/index/*");
		return filterFilterRegistrationBean;
	}
}
