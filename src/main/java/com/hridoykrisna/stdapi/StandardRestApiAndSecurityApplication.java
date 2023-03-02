package com.hridoykrisna.stdapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class StandardRestApiAndSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(StandardRestApiAndSecurityApplication.class, args);
    }

}
