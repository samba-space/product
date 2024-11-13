package com.anysinsa.common.config;

import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.observation.ObservationPredicate;
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenTelemetryConfig {

    @Bean
    public OtlpGrpcSpanExporter otlpGrpcSpanExporter(@Value("${management.otlp.tracing.endpoint}") String endpoint) {
        return OtlpGrpcSpanExporter.builder()
                .setEndpoint(endpoint)
                .build();
    }
    
    @Bean
    public MeterFilter meterFilter() {
        return MeterFilter.deny(id -> {
            String uri = id.getTag("uri");
            return "/api/v1/products/health".equals(uri);
        });
    }
}