package com.vf.admin;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.vf.admin.mapper")
@SpringBootApplication
public class AdminBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(AdminBootstrap.class);
    }
}
