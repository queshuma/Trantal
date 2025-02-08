package com.shuzhi.gateway.Config;

import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

/**
 * Description:
 * Author: SHUZHI
 * Date: 2023/12/6
 *
 * @version 1.0
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public CommandLineRunner openApiGroups(
            RouteDefinitionLocator locator,
            SwaggerUiConfigParameters swaggerUiParameters) {
        return args -> Objects.requireNonNull(locator
                        .getRouteDefinitions().collectList().block())
                .stream()
                .map(RouteDefinition::getId)
                .filter(id -> id.matches("system-.*"))
                .map(id -> id.replace("system-", ""))
                .forEach(swaggerUiParameters::addGroup);
    }
}
