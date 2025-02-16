package com.shuzhi.gateway.Config;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * Description:
 * Author: SHUZHI
 * Date: 2023/12/31
 *
 * @version 1.0
 */
@Configuration
public class GatewayCorsConfig {


    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://49.232.61.41:8080");
//        config.addAllowedOrigin("http://122.237.142.114:8080");
//        config.addAllowedOrigin("*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);
        config.setMaxAge(360000L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }

    @Bean
    public GlobalFilter corsRejectedRequestLogger() {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            // 打印请求信息
            System.out.println("Request Method: " + request.getMethod());
            System.out.println("Request Path: " + request.getPath());
            System.out.println("Request Headers: " + request.getHeaders());

            // 如果是 OPTIONS 请求，直接返回 200
            if (CorsUtils.isPreFlightRequest(request)) {
                response.setStatusCode(HttpStatus.OK);
                return Mono.empty();
            }

            // 检查是否是跨域请求
            if (CorsUtils.isCorsRequest(request)) {
                HttpHeaders headers = request.getHeaders();
                String origin = headers.getOrigin();
                HttpMethod requestMethod = headers.getAccessControlRequestMethod();

                System.out.println("http from: " + origin + " method: " + requestMethod);
            }

            return chain.filter(exchange);
        };
    }
}
