package com.madan.crud_operations_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan("com.madan.crud_operations_demo.Dao")
public class CrudOperationsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudOperationsDemoApplication.class, args);
    }

}
