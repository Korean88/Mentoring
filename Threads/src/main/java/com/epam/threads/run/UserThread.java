package com.epam.threads.run;

import com.epam.threads.model.User;
import com.epam.threads.service.CurrencyExchange;
import com.epam.threads.service.ExchangeService;
import com.epam.threads.service.ExchangeServiceImpl;
import org.apache.log4j.Logger;

import java.math.BigDecimal;

/**
 * Created by Andrey Yun on 07.02.2016.
 */
public class UserThread implements Runnable {

    private static final Logger LOG = Logger.getLogger(UserThread.class);

    private static final int REPEATS = 100;

    private User user;
    private String accountName;
    private CurrencyExchange currencyExchange;

    private static int count;
    private final int ID = ++count;

    public UserThread() {
    }

    public UserThread(User user, String accountName, CurrencyExchange currencyExchange) {
        this.user = user;
        this.accountName = accountName;
        this.currencyExchange = currencyExchange;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public CurrencyExchange getCurrencyExchange() {
        return currencyExchange;
    }

    public void setCurrencyExchange(CurrencyExchange currencyExchange) {
        this.currencyExchange = currencyExchange;
    }

    @Override
    public void run() {
        LOG.debug(this.toString() + " starting...");
        for (int i = 0; i < REPEATS; i++) {
            LOG.debug(this.toString() + ": repeat " + i);
            BigDecimal amount = new BigDecimal(10);
            ExchangeService exchangeService = new ExchangeServiceImpl();
            exchangeService.transferToSubAccount(accountName, user, currencyExchange, amount);
        }
        LOG.debug(this.toString() + " finished");
    }

    @Override
    public String toString() {
        return "UserThread#" + ID + " (" + user.getName() + ", " + accountName + ")";
    }
}
