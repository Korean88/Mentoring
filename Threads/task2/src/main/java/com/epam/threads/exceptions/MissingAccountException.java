package com.epam.threads.exceptions;

import com.epam.threads.model.User;

/**
 * Created by Andrey Yun on 06.02.2016.
 */
public class MissingAccountException extends CustomBusinessException {

    private final String accountName;
    private final User user;

    public String getAccountName() {
        return accountName;
    }

    public User getUser() {
        return user;
    }

    public MissingAccountException(String accountName, User user) {
        this.accountName = accountName;
        this.user = user;
    }

    @Override
    public String getMessage() {
        return "The account [" + accountName + "] for " + user.toString() + " was not found";
    }
}
