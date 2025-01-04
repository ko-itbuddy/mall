package com.example.mall;

import com.example.mall.admin.AdminApplication;
import org.springframework.boot.SpringApplication;

public class TestMallApplication {

    public static void main(String[] args) {
        SpringApplication.from(AdminApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
