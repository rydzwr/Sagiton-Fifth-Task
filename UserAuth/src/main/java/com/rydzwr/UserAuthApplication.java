package com.rydzwr;

import com.rydzwr.filter.LoggedUsersFilter;
import com.rydzwr.resolver.UserSessionCodeMethodArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@RequiredArgsConstructor
public class UserAuthApplication implements WebMvcConfigurer {
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new UserSessionCodeMethodArgumentResolver());
	}

	public static void main(String[] args) {
		SpringApplication.run(UserAuthApplication.class, args);
	}

	final LoggedUsersFilter loggedUsersFilter;

	@Bean
	FilterRegistrationBean<LoggedUsersFilter> usersFilterFilterRegistrationBean() {
		final FilterRegistrationBean<LoggedUsersFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
		filterFilterRegistrationBean.setFilter(loggedUsersFilter);
		filterFilterRegistrationBean.addUrlPatterns("/*");
		return filterFilterRegistrationBean;
	}
}
