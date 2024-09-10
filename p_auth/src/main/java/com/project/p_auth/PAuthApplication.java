package com.project.p_auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(PAuthApplication.class, args);
    }

}
