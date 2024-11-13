package com.anysinsa.common.config;

import feign.RequestInterceptor;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public RequestInterceptor traceIdInterceptor() {
        return template -> {
            String traceId = MDC.get("traceId");
            if (traceId != null) {
                template.header("X-B3-TraceId", traceId);
            }
        };
    }
}
