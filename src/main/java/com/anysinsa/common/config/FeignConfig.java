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
            String spanId = MDC.get("spanId");
            if (traceId != null) {
                template.header("X-B3-TraceId", traceId);
                template.header("X-B3-SpanId", spanId);
                template.header("X-B3-Sampled", "1");
            }
        };
    }
}
