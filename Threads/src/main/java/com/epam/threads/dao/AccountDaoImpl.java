package com.epam.threads.dao;

import com.epam.threads.exceptions.MissingAccountException;
import com.epam.threads.exceptions.UnauthorizedAccountAccessException;
import com.epam.threads.model.Account;
import com.epam.threads.model.Currency;
import com.epam.threads.model.User;
import com.epam.threads.utils.FileUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.util.Properties;

/**
 * Created by Andrey Yun on 06.02.2016.
 */
public class AccountDaoImpl implements AccountDao {

    private static final String USER_ID = "userId";

    @Override
    public void saveUserAccount(Account account) {
        Properties properties = new Properties();
        properties.put(USER_ID, "" + account.getOwner().getId());
        properties.put(Currency.EUR.name(), account.getAmounts().get(Currency.EUR).toPlainString());
        properties.put(Currency.USD.name(), account.getAmounts().get(Currency.USD).toPlainString());
        properties.put(Currency.KZT.name(), account.getAmounts().get(Currency.KZT).toPlainString());
        FileUtil.savePropertiesToFile(FileUtil.ACCOUNTS_DIR + account.getAccountName() + FileUtil.PROPERTIES_EXT,
                properties);
    }

    @Override
    public void removeUserAccount(Account account) throws MissingAccountException {
        if (!FileUtil.removeFile(FileUtil.ACCOUNTS_DIR + account.getAccountName() + FileUtil.PROPERTIES_EXT)) {
            throw new MissingAccountException(account.getAccountName(), account.getOwner());
        }
    }

    @Override
    public Account readUserAccountFromFile(String accountName, User user) throws MissingAccountException, UnauthorizedAccountAccessException {
        Properties properties = FileUtil.loadPropertiesFromFile(FileUtil.ACCOUNTS_DIR + accountName + FileUtil.PROPERTIES_EXT);
        if (CollectionUtils.isEmpty(properties.keySet())) {
            throw new MissingAccountException(accountName, user);
        }
        int ownerIdFromProps = Integer.parseInt(properties.getProperty(USER_ID));
        if (user.getId() != ownerIdFromProps) {
            throw new UnauthorizedAccountAccessException(user, accountName);
        }
        Account account = new Account();
        account.setAccountName(accountName);
        account.setOwner(user);
        putAmountForCurrency(properties, account, Currency.EUR);
        putAmountForCurrency(properties, account, Currency.USD);
        putAmountForCurrency(properties, account, Currency.KZT);
        return account;
    }

    private void putAmountForCurrency(Properties properties, Account account, Currency currency) {
        if (properties.getProperty(currency.name()) != null) {
            account.getAmounts().put(currency, new BigDecimal(properties.getProperty(currency.name())));
        }
    }
}
