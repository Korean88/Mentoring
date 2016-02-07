package com.epam.threads.exceptions;

import com.epam.threads.model.User;

/**
 * Created by Andrey Yun on 07.02.2016.
 */
public class UnauthorizedAccountAccessException extends CustomBusinessException {

    private final User user;
    private final String accountName;

    public User getUser() {
        return user;
    }

    public String getAccountName() {
        return accountName;
    }

    public UnauthorizedAccountAccessException(User user, String accountName) {
        this.user = user;
        this.accountName = accountName;
    }

    @Override
    public String getMessage() {
        return "The " + user.toString() + " is not authorized to access the account [" + accountName + "]";
    }
}
