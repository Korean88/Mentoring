package com.epam.threads.run;

import com.epam.threads.config.SpringConfig;
import com.epam.threads.model.User;
import com.epam.threads.service.CurrencyExchange;
import com.google.common.collect.Sets;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Andrey Yun on 07.02.2016.
 */
public class Runner {

    private static final Logger LOG = Logger.getLogger(Runner.class);

    private static final String ACCOUNT_NAME1 = "Black01";
    private static final String ACCOUNT_NAME2 = "Jackson01";
    private static final String ACCOUNT_NAME3 = "Smith01";

    public static void main(String[] args) {
        User mrBlack = createUserWithSingleAccount(ACCOUNT_NAME1, 1, "Jack Black");
        User mrJackson = createUserWithSingleAccount(ACCOUNT_NAME2, 2, "Peter Jackson");
        User mrsSmith = createUserWithSingleAccount(ACCOUNT_NAME3, 3, "Barbara Smith");
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        //-----------Black----------
        UserRunnable mrBlackRunnable1 = (UserRunnable) ctx.getBean(SpringConfig.USER_RUNNABLE);
        mrBlackRunnable1.setAccountName(ACCOUNT_NAME1);
        mrBlackRunnable1.setCurrencyExchange(CurrencyExchange.EUR_KZT);
        mrBlackRunnable1.setUser(mrBlack);

        UserRunnable mrBlackRunnable2 = (UserRunnable) ctx.getBean(SpringConfig.USER_RUNNABLE);
        mrBlackRunnable2.setAccountName(ACCOUNT_NAME1);
        mrBlackRunnable2.setCurrencyExchange(CurrencyExchange.USD_KZT);
        mrBlackRunnable2.setUser(mrBlack);

        UserRunnable mrBlackRunnable3 = (UserRunnable) ctx.getBean(SpringConfig.USER_RUNNABLE);
        mrBlackRunnable3.setAccountName(ACCOUNT_NAME1);
        mrBlackRunnable3.setCurrencyExchange(CurrencyExchange.KZT_EUR);
        mrBlackRunnable3.setUser(mrBlack);

        UserRunnable mrBlackRunnable4 = (UserRunnable) ctx.getBean(SpringConfig.USER_RUNNABLE);
        mrBlackRunnable4.setAccountName(ACCOUNT_NAME1);
        mrBlackRunnable4.setCurrencyExchange(CurrencyExchange.USD_EUR);
        mrBlackRunnable4.setUser(mrBlack);
        //----------Jackson-------------
        UserRunnable mrJacksonRunnable1 = (UserRunnable) ctx.getBean(SpringConfig.USER_RUNNABLE);
        mrJacksonRunnable1.setAccountName(ACCOUNT_NAME2);
        mrJacksonRunnable1.setCurrencyExchange(CurrencyExchange.USD_EUR);
        mrJacksonRunnable1.setUser(mrJackson);

        UserRunnable mrJacksonRunnable2 = (UserRunnable) ctx.getBean(SpringConfig.USER_RUNNABLE);
        mrJacksonRunnable2.setAccountName(ACCOUNT_NAME2);
        mrJacksonRunnable2.setCurrencyExchange(CurrencyExchange.USD_KZT);
        mrJacksonRunnable2.setUser(mrJackson);

        UserRunnable mrJacksonRunnable3 = (UserRunnable) ctx.getBean(SpringConfig.USER_RUNNABLE);
        mrJacksonRunnable3.setAccountName(ACCOUNT_NAME2);
        mrJacksonRunnable3.setCurrencyExchange(CurrencyExchange.KZT_EUR);
        mrJacksonRunnable3.setUser(mrJackson);

        UserRunnable mrJacksonRunnable4 = (UserRunnable) ctx.getBean(SpringConfig.USER_RUNNABLE);
        mrJacksonRunnable4.setAccountName(ACCOUNT_NAME2);
        mrJacksonRunnable4.setCurrencyExchange(CurrencyExchange.EUR_USD);
        mrJacksonRunnable4.setUser(mrJackson);
        //-----------Smith------------
        UserRunnable mrsSmithRunnable1 = (UserRunnable) ctx.getBean(SpringConfig.USER_RUNNABLE);
        mrsSmithRunnable1.setAccountName(ACCOUNT_NAME3);
        mrsSmithRunnable1.setCurrencyExchange(CurrencyExchange.USD_KZT);
        mrsSmithRunnable1.setUser(mrsSmith);

        UserRunnable mrsSmithRunnable2 = (UserRunnable) ctx.getBean(SpringConfig.USER_RUNNABLE);
        mrsSmithRunnable2.setAccountName(ACCOUNT_NAME3);
        mrsSmithRunnable2.setCurrencyExchange(CurrencyExchange.USD_EUR);
        mrsSmithRunnable2.setUser(mrsSmith);

        UserRunnable mrsSmithRunnable3 = (UserRunnable) ctx.getBean(SpringConfig.USER_RUNNABLE);
        mrsSmithRunnable3.setAccountName(ACCOUNT_NAME3);
        mrsSmithRunnable3.setCurrencyExchange(CurrencyExchange.EUR_KZT);
        mrsSmithRunnable3.setUser(mrsSmith);

        UserRunnable mrsSmithRunnable4 = (UserRunnable) ctx.getBean(SpringConfig.USER_RUNNABLE);
        mrsSmithRunnable4.setAccountName(ACCOUNT_NAME3);
        mrsSmithRunnable4.setCurrencyExchange(CurrencyExchange.KZT_EUR);
        mrsSmithRunnable4.setUser(mrsSmith);

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.submit(mrBlackRunnable1);
        exec.submit(mrBlackRunnable2);
        exec.submit(mrBlackRunnable3);
        exec.submit(mrBlackRunnable4);
        exec.submit(mrJacksonRunnable1);
        exec.submit(mrJacksonRunnable2);
        exec.submit(mrJacksonRunnable3);
        exec.submit(mrJacksonRunnable4);
        exec.submit(mrsSmithRunnable1);
        exec.submit(mrsSmithRunnable2);
        exec.submit(mrsSmithRunnable3);
        exec.submit(mrsSmithRunnable4);
        exec.shutdown();
    }

    private static User createUserWithSingleAccount(String accName, int userId, String userName) {
        User user = new User();
        user.setId(userId);
        user.setName(userName);
        user.setAccountNames(Sets.newHashSet(accName));
        return user;
    }
}
