package com.mentoring.gradle.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Andrey Yun on 28.02.2016.
 */

@Configuration
public class DaoConfig {

    @Bean(name = "dao")
    public MessageDao createMessageDao() {
        return new MessageDaoImpl();
    }
}
