package com.perustars.reportmicroservice;

import com.perustars.reportmicroservice.config.AxonConfig;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@OpenAPIDefinition
@SpringBootApplication
//@EnableDiscoveryClient
@Import({ AxonConfig.class })
public class ReportMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReportMicroserviceApplication.class, args);
    }

}
