package com.epam.threads.service;

import com.epam.threads.config.SpringConfig;
import com.epam.threads.dao.AccountDao;
import com.epam.threads.dao.AccountDaoImpl;
import com.epam.threads.exceptions.CustomBusinessException;
import com.epam.threads.exceptions.InsufficientFundsException;
import com.epam.threads.model.Account;
import com.epam.threads.model.User;
import com.epam.threads.utils.FileUtil;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Properties;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Andrey Yun on 07.02.2016.
 */
public class ExchangeServiceImpl implements ExchangeService {

    private Logger LOG = Logger.getLogger(ExchangeServiceImpl.class);

    @Override
    public synchronized void transferToSubAccount(String accountName, User user, CurrencyExchange currencyExchange,
                                     BigDecimal amountInBaseCurrency) {
        try {
            LOG.debug("Start converting/transferring money within an account");
            ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
            AccountDao accountDao = (AccountDao) ctx.getBean(SpringConfig.ACCOUNT_DAO);
            Account account = accountDao.readUserAccountFromFile(accountName, user);
            if (amountInBaseCurrency.compareTo(account.getAmounts().get(currencyExchange.getCurrencyFrom())) > 0) {
                throw new InsufficientFundsException(amountInBaseCurrency, account, currencyExchange);
            }
            Properties properties = FileUtil.loadPropertiesFromFile(FileUtil.EXHANGE_RATE_PROPERTIES_FILE);
            BigDecimal initialAmountFrom = account.getAmounts().get(currencyExchange.getCurrencyFrom());
            BigDecimal initialAmountTo = account.getAmounts().get(currencyExchange.getCurrencyTo());
            BigDecimal rate = new BigDecimal(currencyExchange.getExchangeRate(properties,
                    currencyExchange.getCurrencyFrom(), currencyExchange.getCurrencyTo()));
            BigDecimal convertedInitialAmountFrom = initialAmountFrom.multiply(rate);
            BigDecimal eventualAmountTo = initialAmountTo.add(convertedInitialAmountFrom);
            account.getAmounts().put(currencyExchange.getCurrencyTo(), eventualAmountTo.setScale(20, BigDecimal.ROUND_HALF_UP));
            BigDecimal remainderFromInitial = initialAmountFrom.subtract(amountInBaseCurrency);
            account.getAmounts().put(currencyExchange.getCurrencyFrom(), remainderFromInitial.setScale(20, BigDecimal.ROUND_HALF_UP));
            accountDao.saveUserAccount(account);
            LOG.debug("Finished converting/transferring money");
        } catch (CustomBusinessException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
