package com.cc.bookmanger;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(value = "com.cc.bookmanger.mapper")
@ComponentScan(basePackages = {"com.cc"})
public class BookMangerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookMangerApplication.class, args);

    }


}
