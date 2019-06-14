package com.jk.managementsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.jk.managementsystem.dao")
@SpringBootApplication
public class ManagementsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagementsystemApplication.class, args);
    }

}
