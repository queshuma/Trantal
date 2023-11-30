package com.shuzhi.admin;



import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableAdminServer
//@EnableOpenApi
@SpringBootApplication
public class SystemAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemAdminApplication.class, args);
    }

}
