package com.will.udemy.webapi.restfulwebservices;

import java.util.Locale;
import java.net.InetAddress;

import javafx.application.Application;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
public class RestfulWebServicesApplication {
    private static final Logger LOGGER = LogManager.getLogger(RestfulWebServicesApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RestfulWebServicesApplication.class, args);

        LOGGER.info(
                new StringBuilder(
                		"|" +
								"|========================================================================\n" +
                                "|Application " + RestfulWebServicesApplication.class.getName() + " is running!\n" +
								"|Access URLs:\n"+
								"|		Local:      http://127.0.0.1:$port$contextPath\n" +
								"|      External:   http://$hostAddress:$port$contextPath\n" +
								"|=========================================================================\n" +
						"|".trim()));

    }

    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }

}
