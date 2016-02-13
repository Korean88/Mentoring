package com.epam.threads.dao;

import com.epam.threads.utils.FileUtil;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Properties;

/**
 * Created by Andrey Yun on 06.02.2016.
 */
public class CurrencyExchangeDaoImpl implements CurrencyExchangeDao {

    private static final Logger LOG = Logger.getLogger(CurrencyExchangeDaoImpl.class);

    @Override
    public void saveAllRatesToFile(Map<String, Double> allRates) {
        if (allRates != null && !allRates.isEmpty()) {
            LOG.debug("Saving all rates to properties file started");
            Properties properties = new Properties();
            for (String key : allRates.keySet()) {
                properties.put(key, allRates.get(key));
            }
            FileUtil fileUtil = new FileUtil();
            fileUtil.savePropertiesToFile(FileUtil.EXHANGE_RATE_PROPERTIES_FILE, properties);
            LOG.debug("Saving all rates to properties file finished");
        }
    }
}
