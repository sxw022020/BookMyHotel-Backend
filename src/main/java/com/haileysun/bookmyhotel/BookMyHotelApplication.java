package com.haileysun.bookmyhotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication
 *  - A convenience annotation provided by Spring Boot that combines three other annotations:
 *    @Configuration, @EnableAutoConfiguration, and @ComponentScan.
 *
 * @Configuration: Indicates that the class is a configuration class that declares Spring beans.
 *
 * @EnableAutoConfiguration: Enables Spring Boot's auto-configuration feature, which automatically
 * configures Spring beans based on the classpath and the configured properties.
 *
 * @ComponentScan: Scans the classpath for Spring components and registers them as beans.
 *
 * By using @SpringBootApplication, you can configure your Spring Boot application with minimal effort,
 * as it takes care of the necessary configuration and component scanning.
 */
@SpringBootApplication
public class BookMyHotelApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookMyHotelApplication.class, args);
    }

}
