package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.example")
@SpringBootApplication
@ComponentScan("com.example")
public class CrudApplication {

  public static void main(String[] args) {
    SpringApplication.run(CrudApplication.class, args);
  }

}
