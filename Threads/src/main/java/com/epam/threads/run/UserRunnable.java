package com.epam.threads.run;

import com.epam.threads.model.User;
import com.epam.threads.service.CurrencyExchange;
import com.epam.threads.service.ExchangeService;
import org.apache.log4j.Logger;

import java.math.BigDecimal;

/**
 * Created by Andrey Yun on 07.02.2016.
 */
public class UserRunnable implements Runnable {

    private static final Logger LOG = Logger.getLogger(UserRunnable.class);

    private static final int REPEATS = 5;

    private User user;
    private String accountName;
    private CurrencyExchange currencyExchange;
    private ExchangeService exchangeService;

    public UserRunnable() {
    }

    public UserRunnable(User user, String accountName, CurrencyExchange currencyExchange) {
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

    public ExchangeService getExchangeService() {
        return exchangeService;
    }

    public void setExchangeService(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @Override
    public void run() {
        LOG.debug(this.toString() + " starting...");
        LOG.info("thread started: " + Thread.currentThread().getId());
        for (int i = 0; i < REPEATS; i++) {
            LOG.debug(this.toString() + ": repeat " + i);
            BigDecimal amount = new BigDecimal(10);
            exchangeService.transferToSubAccount(accountName, user, currencyExchange, amount);
        }
        LOG.debug(this.toString() + " finished");
    }

    @Override
    public String toString() {
        return "UserRunnable#" + Thread.currentThread() + " (" + user.getName() + ", " + accountName + ")";
    }
}
