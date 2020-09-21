package com.cdfg.thdfhcl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan("com.cdfg.thdfhcl.dao")
@SpringBootApplication
public class ThdfhclApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThdfhclApplication.class, args);
    }

}
