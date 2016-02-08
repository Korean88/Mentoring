package com.epam.threads.model;

/**
 * Created by Andrey Yun on 28.01.16.
 */
public enum Currency {

    USD("Usd"),
    EUR("Eur"),
    KZT("Kzt");

    private final String key;

    Currency(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
