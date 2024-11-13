package com.anysinsa.common.config;

import feign.RequestInterceptor;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public RequestInterceptor traceIdInterceptor(OpenTelemetry openTelemetry) {
        return template -> {
            Span span = openTelemetry.getTracer("feign").spanBuilder("feign-request").startSpan();
            template.header("traceparent", span.getSpanContext().getTraceId());
        };
    }
}
