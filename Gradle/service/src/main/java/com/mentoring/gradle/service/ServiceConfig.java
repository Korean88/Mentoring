package com.mentoring.gradle.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Andrey Yun on 28.02.2016.
 */

@Configuration
@ComponentScan(basePackages = "com.mentoring.gradle.dao")
public class ServiceConfig {

    @Bean(name = "service")
    public GreetingService createGreetingService() {
        GreetingService greetingService = new GreetingServiceImpl();
        return greetingService;
    }

}
