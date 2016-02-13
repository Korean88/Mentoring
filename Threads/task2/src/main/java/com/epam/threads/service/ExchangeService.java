package com.epam.threads.service;

import com.epam.threads.model.User;

import java.math.BigDecimal;

/**
 * Created by Andrey Yun on 07.02.2016.
 */
public interface ExchangeService {

    void transferToSubAccount(String accountName, User user, CurrencyExchange currencyExchange, BigDecimal amountInBaseCurrency);

}
