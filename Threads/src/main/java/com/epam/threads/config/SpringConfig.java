package com.epam.threads.config;

import com.epam.threads.dao.AccountDao;
import com.epam.threads.dao.AccountDaoImpl;
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

    public static final String EXCHANGE_SERVICE_NAME = "exchangeService";
    public static final String ACCOUNT_DAO = "accountDao";

    @Bean(name = SpringConfig.EXCHANGE_SERVICE_NAME)
    @Scope(value = "singleton")
    public ExchangeService createExchangeService() {
        return new ExchangeServiceImpl();
    }

    @Bean(name = SpringConfig.ACCOUNT_DAO)
    public AccountDao createAccountDao() {
        return new AccountDaoImpl();
    }
}
