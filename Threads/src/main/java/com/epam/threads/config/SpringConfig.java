package com.epam.threads.config;

import com.epam.threads.dao.AccountDao;
import com.epam.threads.dao.AccountDaoImpl;
import com.epam.threads.run.UserRunnable;
import com.epam.threads.service.ExchangeService;
import com.epam.threads.service.ExchangeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * User: Andrey Yun
 * Date: 2/10/2016
 * Time: 10:43 AM
 */

@Configuration
public class SpringConfig {

    public static final String USER_RUNNABLE = "userRunnable";

    @Bean
    @Scope(value = "singleton")
    public ExchangeService createExchangeService() {
        ExchangeService exchangeService = new ExchangeServiceImpl(createAccountDao());
        return exchangeService;
    }

    @Bean
    public AccountDao createAccountDao() {
        return new AccountDaoImpl();
    }

    @Bean(name = SpringConfig.USER_RUNNABLE)
    @Scope(value = "prototype")
    public Runnable createUserRunnable() {
        UserRunnable userRunnable = new UserRunnable();
        userRunnable.setExchangeService(createExchangeService());
        return userRunnable;
    }
}
