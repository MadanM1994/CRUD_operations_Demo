package com.madan.crud_operations_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
@ComponentScan({"com.madan.crud_operations_demo.repository", "com.madan.crud_operations_demo.controller", "com.madan.crud_operations_demo.service"})
public class CrudOperationsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudOperationsDemoApplication.class, args);
    }

}
