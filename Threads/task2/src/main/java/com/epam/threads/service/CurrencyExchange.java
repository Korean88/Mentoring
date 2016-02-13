package com.epam.threads.service;

import com.epam.threads.exceptions.NoExchangeRateFoundException;
import com.epam.threads.model.Currency;

import java.util.Properties;

/**
 * Created by Andrey Yun on 06.02.2016.
 */
public enum CurrencyExchange {

    EUR_USD(Currency.EUR, Currency.USD),
    EUR_KZT(Currency.EUR, Currency.KZT),
    USD_EUR(Currency.USD, Currency.EUR),
    USD_KZT(Currency.USD, Currency.KZT),
    KZT_EUR(Currency.KZT, Currency.EUR),
    KZT_USD(Currency.KZT, Currency.USD);

    private final Currency currencyFrom;
    private final Currency currencyTo;

    public Currency getCurrencyFrom() {
        return currencyFrom;
    }

    public Currency getCurrencyTo() {
        return currencyTo;
    }

    CurrencyExchange(Currency currencyFrom, Currency currencyTo) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
    }

    public double getExchangeRate(Properties properties,
                                  Currency baseCurrency,
                                  Currency foreignCurrency) throws NoExchangeRateFoundException {
        String key = baseCurrency.getKey() + foreignCurrency.getKey();
        if (properties != null && properties.get(key) != null) {
            return Double.parseDouble((String) properties.get(key));
        } else {
            throw new NoExchangeRateFoundException(baseCurrency, foreignCurrency);
        }
    }

}
