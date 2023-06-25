package com.codecool.el_grande_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ElGrandeProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElGrandeProjectApplication.class, args);
    }

}
