package com.shuzhi.gateway.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Description:
 * Author: SHUZHI
 * Date: 2023/12/31
 *
 * @version 1.0
 */
@Component
public class LoggingFilter implements GlobalFilter {
    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 输出请求信息
        logger.info("Gateway Request: {}", exchange.getRequest().getPath());

        // 调用链中的下一个过滤器
        return chain.filter(exchange);
    }
}
