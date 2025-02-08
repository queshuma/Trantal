package com.shuzhi.system_order.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * Author: SHUZHI
 * Date: 2023/12/6
 *
 * @version 1.0
 */
@Configuration
public class SpringFoxConfig {
    @Bean
    public GroupedOpenApi publicApi () {
        return GroupedOpenApi.builder()
                .group( "order" )
                .packagesToScan( "com.shuzhi.system_order.Controller" )
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI () {
        return  new  OpenAPI ()
                .info(new Info().title( "SHUZHI WEBSITE API -- SHOP MODEL" )
                        .description( "SHUZHI WEB API" )
                        .version( "1.0" )
                        .license( new License( ).name( "Apache 2.0" ).url( "http://springdoc.org" )));
    }
}
