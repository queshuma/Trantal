package com.shuzhi.system_order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableFeignClients
@CrossOrigin(origins = {"0.0.0.0"})
@MapperScan("com.shuzhi.system_order.Mapper")
public class SystemOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemOrderApplication.class, args);
    }

}
