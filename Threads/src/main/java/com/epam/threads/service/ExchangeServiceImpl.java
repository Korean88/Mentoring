package com.epam.threads.service;

import com.epam.threads.dao.AccountDao;
import com.epam.threads.exceptions.CustomBusinessException;
import com.epam.threads.exceptions.InsufficientFundsException;
import com.epam.threads.model.Account;
import com.epam.threads.model.User;
import com.epam.threads.utils.FileUtil;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.Properties;

/**
 * Created by Andrey Yun on 07.02.2016.
 */
public class ExchangeServiceImpl implements ExchangeService {

    private Logger LOG = Logger.getLogger(ExchangeServiceImpl.class);

    private final AccountDao accountDao;

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ExchangeServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public synchronized void transferToSubAccount(String accountName, User user, CurrencyExchange currencyExchange,
                                                  BigDecimal amountInBaseCurrency) {
        try {
            LOG.debug("Start converting/transferring money within an account");
            synchronized (this) {
                Account account = accountDao.readUserAccountFromFile(accountName, user);
                if (amountInBaseCurrency.compareTo(account.getAmounts().get(currencyExchange.getCurrencyFrom())) > 0) {
                    throw new InsufficientFundsException(amountInBaseCurrency, account, currencyExchange);
                }
                Properties properties = FileUtil.loadPropertiesFromFile(FileUtil.EXHANGE_RATE_PROPERTIES_FILE);
                BigDecimal initialAmountFrom = account.getAmounts().get(currencyExchange.getCurrencyFrom());
                BigDecimal initialAmountTo = account.getAmounts().get(currencyExchange.getCurrencyTo());
                BigDecimal rate = new BigDecimal(currencyExchange.getExchangeRate(properties,
                        currencyExchange.getCurrencyFrom(), currencyExchange.getCurrencyTo()));
                LOG.debug("INITIAL_FROM: " + initialAmountFrom + currencyExchange.getCurrencyFrom() +
                        "; INITIAL_TO: " + initialAmountTo + currencyExchange.getCurrencyTo() + "; RATE: " + rate);
                BigDecimal convertedInitialAmountFrom = amountInBaseCurrency.multiply(rate);
                BigDecimal eventualAmountTo = initialAmountTo.add(convertedInitialAmountFrom);
                account.getAmounts().put(currencyExchange.getCurrencyTo(), eventualAmountTo.setScale(4, BigDecimal.ROUND_HALF_UP));
                BigDecimal remainderFromInitial = initialAmountFrom.subtract(amountInBaseCurrency);
                account.getAmounts().put(currencyExchange.getCurrencyFrom(), remainderFromInitial.setScale(4, BigDecimal.ROUND_HALF_UP));
                LOG.debug("Adding " + convertedInitialAmountFrom + currencyExchange.getCurrencyTo() + " to " +
                        initialAmountTo + currencyExchange.getCurrencyTo());
                LOG.debug("EVENTUAL_FROM: " + account.getAmounts().get(currencyExchange.getCurrencyFrom()) +
                        currencyExchange.getCurrencyFrom() + "; EVENTUAL_TO: " +
                        account.getAmounts().get(currencyExchange.getCurrencyTo()) + currencyExchange.getCurrencyTo());
                accountDao.saveUserAccount(account);
            }
            LOG.debug("Finished converting/transferring money");
        } catch (CustomBusinessException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
