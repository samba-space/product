package com.anysinsa.common.config;

import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.context.Context;
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;
import io.opentelemetry.sdk.trace.data.LinkData;
import io.opentelemetry.sdk.trace.samplers.Sampler;
import io.opentelemetry.sdk.trace.samplers.SamplingDecision;
import io.opentelemetry.sdk.trace.samplers.SamplingResult;
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
    public Sampler customSampler() {
        return new Sampler() {
            @Override
            public SamplingResult shouldSample(Context context, String traceId, String name,
                                               SpanKind spanKind, Attributes attributes, List<LinkData> parentLinks) {
                if (name.contains("/health")) {
                    return SamplingResult.create(SamplingDecision.DROP);
                }
                return SamplingResult.create(SamplingDecision.RECORD_AND_SAMPLE);
            }

            @Override
            public String getDescription() {
                return "CustomSampler";
            }
        };
    }
}