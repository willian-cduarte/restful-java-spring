package com.will.udemy.microservices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestfulWebServiceApplication {
    private static final Logger LOGGER = LogManager.getLogger(RestfulWebServiceApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(RestfulWebServiceApplication.class, args);

    }



}
