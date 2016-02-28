package com.mentoring.gradle.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by Andrey Yun on 28.02.2016.
 */
public class GreetingServiceTest {

    @Test
    public void shouldUseDaoToGetMessage() throws IOException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ServiceConfig.class);
        GreetingService service = ctx.getBean("service", GreetingService.class);
        String res = service.sayHello();
        assertThat(res, equalTo("Hello World"));
    }

}
