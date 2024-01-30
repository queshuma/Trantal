package com.shuzhi.system_objclasses;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.shuzhi.system_objclasses")
@EnableDiscoveryClient
@MapperScan("com.shuzhi.system_objclasses.Mapper")
public class SystemObjClassApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemObjClassApplication.class, args);
    }

}
