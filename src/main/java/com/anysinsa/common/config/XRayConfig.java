package com.anysinsa.common.config;

import jakarta.servlet.Filter;
import com.amazonaws.xray.jakarta.servlet.AWSXRayServletFilter;  // jakarta 패키지 사용
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XRayConfig {
    @Bean
    public Filter tracingFilter() {
        return new AWSXRayServletFilter("product-service");
    }
}