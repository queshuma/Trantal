package com.shuzhi.system_shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shuzhi.system_shop.Mapper")
public class SystemShopConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemShopConsumerApplication.class, args);
    }

}
