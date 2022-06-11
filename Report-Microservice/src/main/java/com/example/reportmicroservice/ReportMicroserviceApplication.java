package com.example.reportmicroservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition
@SpringBootApplication
public class ReportMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReportMicroserviceApplication.class, args);
    }

}
