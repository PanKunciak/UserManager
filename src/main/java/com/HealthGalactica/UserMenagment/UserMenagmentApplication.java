package com.HealthGalactica.UserMenagment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class UserMenagmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserMenagmentApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry) {
				registry.addResourceHandler("/css/**")
						.addResourceLocations("classpath:/static/css/");
			}
		};
	}


}
