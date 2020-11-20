package com.hansheng.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.hansheng.springboot.mapper")
public class SpringBootDay3Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDay3Application.class, args);
    }

}
