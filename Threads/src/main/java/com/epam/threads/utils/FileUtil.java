package com.epam.threads.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Andrey Yun on 28.01.16.
 */
public class FileUtil {

    private static final Logger LOGGER = Logger.getLogger(FileUtil.class);

    public static final String EXHANGE_RATE_PROPERTIES_FILE = "rates/exchangeRate.properties";
    public static final String ACCOUNTS_DIR = "accounts/";
    public static final String PROPERTIES_EXT = ".properties";

    private static ReentrantLock lock = new ReentrantLock();

    public static Properties loadPropertiesFromFile(String filename) {
        Properties properties = new Properties();
        try {
            if (lock.isLocked()) {
                LOGGER.warn("locked in read");
            }
            lock.tryLock(2, TimeUnit.SECONDS);
            LOGGER.debug("read from properties...");
            URL url = FileUtil.class.getClassLoader().getResource(filename);
            InputStream is = url.openStream();
            properties.load(is);
            is.close();
            LOGGER.debug("finished reading from properties...");
        } catch (Exception e) {
            LOGGER.error("Couldn't load properties from file [" + filename + "]", e);
        } finally {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
        return properties;
    }

    public static void savePropertiesToFile(String filename, Properties properties) {
        if (CollectionUtils.isNotEmpty(properties.keySet())) {
            try {
                if (lock.isLocked()) {
                    LOGGER.warn("locked in write");
                }
                lock.tryLock(2, TimeUnit.SECONDS);
                LOGGER.debug("writing to file...");
                String path = FileUtil.class.getClassLoader().getResource(filename).getFile();
                OutputStream os = new FileOutputStream(new File(path));
                properties.store(os, null);
                os.close();
                LOGGER.debug("finished writing to file...");
            } catch (Exception e) {
                LOGGER.error("Could not save properties to file [" + filename + "]", e);
            } finally {
                if (lock.isLocked()) {
                    lock.unlock();
                }
            }
        }
    }

    public static synchronized boolean removeFile(String filename) {
        String path = FileUtil.class.getResource(filename).getFile();
        File file = new File(path);
        if (file != null) {
            if (file.delete()) {
                LOGGER.info("File " + filename + " was successfully deleted");
                return true;
            } else {
                LOGGER.warn("File " + filename + " was not deleted");
                return false;
            }
        }
        return false;
    }
}
