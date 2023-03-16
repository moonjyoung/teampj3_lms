package com.greenart.lms_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*");
    }
    @Bean
    public OpenAPI lmsOpenAPI() {
        Info info = new Info().version("0.0.1").title("대학성적관리시스템 LMS API").description("대학성적관리시스템 LMS Restful API 명세서");
        return new OpenAPI().info(info);
    }
}
