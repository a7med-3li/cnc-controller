package com.cnc.app;

import org.springframework.boot.SpringApplication;

public class TestCncControllerApplication {

    public static void main(String[] args) {
        SpringApplication.from(app::main).with(TestcontainersConfiguration.class).run(args);
    }

}
