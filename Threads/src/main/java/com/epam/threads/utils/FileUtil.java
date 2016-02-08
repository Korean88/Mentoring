package com.epam.threads.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Andrey Yun on 28.01.16.
 */
public class FileUtil {

    private static final Logger LOGGER = Logger.getLogger(FileUtil.class);

    public static final String EXHANGE_RATE_PROPERTIES_FILE = "datafiles/rates/exchangeRate.properties";
    public static final String ACCOUNTS_DIR = "datafiles/accounts/";
    public static final String PROPERTIES_EXT = ".properties";

    public static synchronized Properties loadPropertiesFromFile(String filename) {
        Properties properties = new Properties();
        File file = new File(filename);
        try {
            LOGGER.debug("read from properties...");
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            InputStream is = Channels.newInputStream(randomAccessFile.getChannel().position(0L));
            properties.load(is);
            randomAccessFile.getChannel().close();
            is.close();
            LOGGER.debug("finished reading from properties...");
        } catch (Exception e) {
            LOGGER.error("Couldn't load properties from file [" + filename + "]", e);
        }
        return properties;
    }

    public static synchronized void savePropertiesToFile(String filename, Properties properties) {
        if (CollectionUtils.isNotEmpty(properties.keySet())) {
            try {
                LOGGER.debug("writing to file...");
                FileChannel fileChannel = FileChannel.open(Paths.get(filename), StandardOpenOption.WRITE);
                OutputStream os = Channels.newOutputStream(fileChannel.position(0L));
                properties.store(os, null);
                os.close();
                fileChannel.close();
                LOGGER.debug("finished writing to file...");
            } catch (Exception e) {
                LOGGER.error("Could not save properties to file [" + filename + "]", e);
            }
        }
    }

    public static synchronized boolean removeFile(String filename) {
        File file = new File(filename);
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
