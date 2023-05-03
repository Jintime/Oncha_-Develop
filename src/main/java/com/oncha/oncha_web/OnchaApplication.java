package com.oncha.oncha_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OnchaApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnchaApplication.class, args);
    }

}
