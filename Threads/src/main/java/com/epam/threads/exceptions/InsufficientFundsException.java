package com.epam.threads.exceptions;

import com.epam.threads.model.Account;
import com.epam.threads.service.CurrencyExchange;

import java.math.BigDecimal;

/**
 * Created by Andrey Yun on 07.02.2016.
 */
public class InsufficientFundsException extends CustomBusinessException {

    private final BigDecimal amount;
    private final Account account;
    private final CurrencyExchange currencyExchange;

    public BigDecimal getAmount() {
        return amount;
    }

    public InsufficientFundsException(BigDecimal amount, Account account, CurrencyExchange currencyExchange) {
        this.amount = amount;
        this.account = account;
        this.currencyExchange = currencyExchange;
    }

    @Override
    public String getMessage() {
        return "There is not enough funds in " + currencyExchange.getCurrencyFrom() + " on account [" + account.getAccountName() +
                "] to exchange to " + currencyExchange.getCurrencyTo() + " using rate " + currencyExchange +
                ". The requested amount: " + amount + " " + currencyExchange.getCurrencyFrom();
    }
}
