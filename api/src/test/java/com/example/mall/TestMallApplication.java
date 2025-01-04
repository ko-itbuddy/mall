package com.example.mall;

import com.example.mall.admin.ApiApplication;
import org.springframework.boot.SpringApplication;

public class TestMallApplication {

    public static void main(String[] args) {
        SpringApplication.from(ApiApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
