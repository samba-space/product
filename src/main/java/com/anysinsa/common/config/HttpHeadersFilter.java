package com.anysinsa.common.config;

import io.opentelemetry.api.trace.Span;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HttpHeadersFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            Span currentSpan = Span.current();

            if (currentSpan != null) {
                // ALB 헤더 정보 추가
                String forwardedFor = httpRequest.getHeader("X-Forwarded-For");
                String forwardedProto = httpRequest.getHeader("X-Forwarded-Proto");
                String forwardedPort = httpRequest.getHeader("X-Forwarded-Port");

                if (forwardedFor != null) {
                    currentSpan.setAttribute("http.client_ip", forwardedFor);
                }
                if (forwardedProto != null) {
                    currentSpan.setAttribute("http.scheme", forwardedProto);
                }
                if (forwardedPort != null) {
                    currentSpan.setAttribute("http.port", forwardedPort);
                }

                // 요청 메서드와 URI 정보 추가
                currentSpan.setAttribute("http.method", httpRequest.getMethod());
                currentSpan.setAttribute("http.url", httpRequest.getRequestURI());
            }
        }

        chain.doFilter(request, response);
    }
}