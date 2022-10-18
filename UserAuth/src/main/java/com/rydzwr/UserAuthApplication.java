package com.rydzwr;

import com.rydzwr.filter.LoggedUsersFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import static java.util.Arrays.asList;

@SpringBootApplication
public class UserAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthApplication.class, args);
	}

	@Autowired
	LoggedUsersFilter loggedUsersFilter;

	@Bean
	FilterRegistrationBean<LoggedUsersFilter> usersFilterFilterRegistrationBean() {
		final FilterRegistrationBean<LoggedUsersFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
		filterFilterRegistrationBean.setFilter(loggedUsersFilter);
		filterFilterRegistrationBean.addUrlPatterns("/*");
		return filterFilterRegistrationBean;
	}
}
