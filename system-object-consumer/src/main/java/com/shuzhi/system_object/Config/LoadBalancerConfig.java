package com.shuzhi.system_object.Config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Description:
 * Author: chentao
 * Date: 22 1月 2025
 *
 * @version 1.0
 */
@Configuration
public class LoadBalancerConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
