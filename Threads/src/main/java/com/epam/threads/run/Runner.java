package com.epam.threads.run;

import com.epam.threads.model.User;
import com.epam.threads.service.CurrencyExchange;
import com.google.common.collect.Sets;
import org.apache.log4j.Logger;

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
    private static final String ACCOUNT_NAME4 = "Stewart01";

    public static void main(String[] args) {
        User mrBlack = createUserWithSingleAccount(ACCOUNT_NAME1, 1, "Jack Black");
        User mrJackson = createUserWithSingleAccount(ACCOUNT_NAME2, 2, "Peter Jackson");
        User mrsSmith = createUserWithSingleAccount(ACCOUNT_NAME3, 3, "Barbara Smith");
        User msStewart = createUserWithSingleAccount(ACCOUNT_NAME4, 4, "Jessica Stewart");

        Runnable thread1 = new UserThread(mrBlack, ACCOUNT_NAME1, CurrencyExchange.EUR_KZT);
//        Runnable thread2 = new UserThread(mrJackson, ACCOUNT_NAME2, CurrencyExchange.USD_EUR);
//        Runnable thread3 = new UserThread(mrsSmith, ACCOUNT_NAME3, CurrencyExchange.EUR_USD);
//        Runnable thread4 = new UserThread(msStewart, ACCOUNT_NAME4, CurrencyExchange.KZT_EUR);
        Runnable thread5 = new UserThread(mrBlack, ACCOUNT_NAME1, CurrencyExchange.USD_KZT);
//        Runnable thread6 = new UserThread(mrJackson, ACCOUNT_NAME2, CurrencyExchange.KZT_USD);
//        Runnable thread7 = new UserThread(mrsSmith, ACCOUNT_NAME3, CurrencyExchange.USD_EUR);
//        Runnable thread8 = new UserThread(msStewart, ACCOUNT_NAME4, CurrencyExchange.EUR_KZT);
//        Runnable thread9 = new UserThread(mrBlack, ACCOUNT_NAME1, CurrencyExchange.USD_EUR);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.submit(thread1);
//        exec.submit(thread2);
//        exec.submit(thread3);
//        exec.submit(thread4);
        exec.submit(thread5);
//        exec.submit(thread6);
//        exec.submit(thread7);
//        exec.submit(thread8);
//        exec.submit(thread9);
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
