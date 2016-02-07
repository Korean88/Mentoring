package com.epam.threads.service;

import com.epam.threads.exceptions.NoExchangeRateFoundException;
import com.epam.threads.model.Currency;

import java.util.Properties;

/**
 * Created by Andrey Yun on 06.02.2016.
 */
public enum CurrencyExchange {

    EUR_USD(Currency.EUR, Currency.USD) {
        @Override
        public double getExchangeRate(Properties properties) throws NoExchangeRateFoundException {
            return getDoubleValueFromProps(properties, "EurUsd", Currency.EUR, Currency.USD);
        }
    },
    EUR_KZT(Currency.EUR, Currency.KZT) {
        @Override
        public double getExchangeRate(Properties properties) throws NoExchangeRateFoundException {
            return getDoubleValueFromProps(properties, "EurKzt", Currency.EUR, Currency.KZT);
        }
    },
    USD_EUR(Currency.USD, Currency.EUR) {
        @Override
        public double getExchangeRate(Properties properties) throws NoExchangeRateFoundException {
            return getDoubleValueFromProps(properties, "UsdEur", Currency.USD, Currency.EUR);
        }
    },
    USD_KZT(Currency.USD, Currency.KZT) {
        @Override
        public double getExchangeRate(Properties properties) throws NoExchangeRateFoundException {
            return getDoubleValueFromProps(properties, "UsdKzt", Currency.USD, Currency.KZT);
        }
    },
    KZT_EUR(Currency.KZT, Currency.EUR) {
        @Override
        public double getExchangeRate(Properties properties) throws NoExchangeRateFoundException {
            return getDoubleValueFromProps(properties, "KztEur", Currency.KZT, Currency.EUR);
        }
    },
    KZT_USD(Currency.KZT, Currency.USD) {
        @Override
        public double getExchangeRate(Properties properties) throws NoExchangeRateFoundException {
            return getDoubleValueFromProps(properties, "EurKzt", Currency.EUR, Currency.KZT);
        }
    };

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

    private static double getDoubleValueFromProps(Properties properties, String key,
                                                  Currency baseCurrency,
                                                  Currency foreignCurrency) throws NoExchangeRateFoundException {
        if (properties != null && properties.get(key) != null) {
            return Double.parseDouble((String) properties.get(key));
        } else {
            throw new NoExchangeRateFoundException(baseCurrency, foreignCurrency);
        }
    }

    public abstract double getExchangeRate(Properties properties) throws NoExchangeRateFoundException;
}
