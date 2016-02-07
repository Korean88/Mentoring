package com.epam.threads.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrey Yun on 28.01.16.
 */
public class Account implements Serializable {

    private User owner;
    private final Map<Currency, BigDecimal> amounts = new HashMap<>();
    private String accountName;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Map<Currency, BigDecimal> getAmounts() {
        return amounts;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Account() {
        amounts.put(Currency.EUR, BigDecimal.ZERO);
        amounts.put(Currency.USD, BigDecimal.ZERO);
        amounts.put(Currency.KZT, BigDecimal.ZERO);
    }
}
