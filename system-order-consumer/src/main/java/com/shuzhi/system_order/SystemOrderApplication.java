package com.shuzhi.system_order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
@MapperScan("com.shuzhi.system_order.Mapper")
public class SystemOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemOrderApplication.class, args);
    }

}
