package com.epam.threads.dao;

import com.epam.threads.exceptions.MissingAccountException;
import com.epam.threads.exceptions.UnauthorizedAccountAccessException;
import com.epam.threads.model.Account;
import com.epam.threads.model.User;

/**
 * Created by Andrey Yun on 28.01.16.
 */
public interface AccountDao {

    void saveUserAccount(Account account);

    void removeUserAccount(Account account) throws MissingAccountException;

    Account readUserAccountFromFile(String accountName, User user) throws MissingAccountException, UnauthorizedAccountAccessException;
}
