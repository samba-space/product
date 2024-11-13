package com.anysinsa.common.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenTelemetryConfig {
//    @Bean
//    public OpenTelemetry openTelemetry() {
//        Resource resource = Resource.getDefault()
//                .merge(Resource.create(Attributes.builder()
//                        .put(ResourceAttributes.SERVICE_NAME, "product-service")
//                        .put(ResourceAttributes.DEPLOYMENT_ENVIRONMENT, "production")
//                        .build()));
//
//        SdkTracerProvider sdkTracerProvider = SdkTracerProvider.builder()
//                .setResource(resource)
//                .addSpanProcessor(BatchSpanProcessor.builder(
//                                OtlpGrpcSpanExporter.builder()
//                                        .setEndpoint("http://localhost:4317")
//                                        .build())
//                        .build())
//                .build();
//
//        return OpenTelemetrySdk.builder()
//                .setTracerProvider(sdkTracerProvider)
//                .setPropagators(ContextPropagators.create(W3CTraceContextPropagator.getInstance()))
//                .buildAndRegisterGlobal();
//    }
}