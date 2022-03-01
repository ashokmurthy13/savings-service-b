package com.kadmos.savings.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class KadmosSavingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(KadmosSavingsApplication.class, args);
    }

}
