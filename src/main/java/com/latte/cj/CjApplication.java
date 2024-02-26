package com.latte.cj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.latte.cj")
public class CjApplication {

    public static void main(String[] args) {
        SpringApplication.run(CjApplication.class, args);
    }

}
