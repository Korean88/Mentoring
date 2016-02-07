package com.epam.threads.dao;

import java.util.Map;

/**
 * Created by Andrey Yun on 06.02.2016.
 */
public interface CurrencyExchangeDao {

    void saveAllRatesToFile(Map<String, Double> allRates);

}
