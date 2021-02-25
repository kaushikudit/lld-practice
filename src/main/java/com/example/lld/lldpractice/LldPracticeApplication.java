package com.example.lld.lldpractice;

import com.example.lld.lldpractice.apicounttracker.interceptor.ApiCountTrackerInterceptor;
import com.example.lld.lldpractice.apicounttracker.interceptor.RateLimiterInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class LldPracticeApplication extends SpringBootServletInitializer implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(LldPracticeApplication.class, args);
	}

	@Autowired
	private ApiCountTrackerInterceptor interceptor;

	@Autowired
	private RateLimiterInterceptor rateLimiterInterceptor;

	@Override
	public void addInterceptors(final InterceptorRegistry registry) {

		registry.addInterceptor(interceptor)
				.addPathPatterns("/user/**");

		registry.addInterceptor(rateLimiterInterceptor)
				.addPathPatterns("/user");

	}

}
