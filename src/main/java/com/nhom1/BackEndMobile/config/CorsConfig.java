package com.nhom1.BackEndMobile.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Áp dụng cho tất cả endpoint
                .allowedOrigins("*") // Hoặc: "http://localhost:8000"
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Cho phép các method
                .allowedHeaders("*") // Cho phép mọi header
                .allowCredentials(false) // Nếu không dùng cookie thì để false
                .maxAge(3600); // Cache preflight trong 1h
    }
}