package com.epam.threads.exceptions;

import com.epam.threads.model.Currency;

/**
 * Created by Andrey Yun on 06.02.2016.
 */
public class NoExchangeRateFoundException extends CustomBusinessException {

    private final Currency baseCurrency;
    private final Currency foreignCurrency;

    public NoExchangeRateFoundException(Currency baseCurrency, Currency foreignCurrency) {
        this.baseCurrency = baseCurrency;
        this.foreignCurrency = foreignCurrency;
    }

    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    public Currency getForeignCurrency() {
        return foreignCurrency;
    }

    @Override
    public String getMessage() {
        return "No exchange rate was found for base currency [" + baseCurrency + "] and foreign currency [" + foreignCurrency + "]";
    }
}
