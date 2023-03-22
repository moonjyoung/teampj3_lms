package com.greenart.lms_service.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springframework.context.annotation.Bean;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI monthlyCoffeeOpenAPI() {
        Info info = new Info().version("0.1.1").title("대학성적관리시스템 LMS API").description("대학성적관리시스템 LMS Restful API 명세서");
        final String securitySchemeName = "bearerAuth";
        OpenAPI openAPI = new OpenAPI().info(info);
        openAPI.addSecurityItem(new SecurityRequirement()
                .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
        return openAPI;
    }
}
