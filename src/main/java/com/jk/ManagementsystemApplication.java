package com.jk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.jk.dao")
@SpringBootApplication
public class ManagementsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagementsystemApplication.class, args);
    }

}
