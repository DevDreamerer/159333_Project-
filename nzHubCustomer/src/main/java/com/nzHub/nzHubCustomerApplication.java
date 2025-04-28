package com.nzHub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.nzHub.mapper")
public class nzHubCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(nzHubCustomerApplication.class, args);
    }

}
