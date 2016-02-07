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

    private ReentrantLock lock = new ReentrantLock();

    public Properties loadPropertiesFromFile(String filename) {
        Properties properties = new Properties();
        File file = new File(filename);
        try {
            LOGGER.debug("read. trying to lock...");
            lock.tryLock(2, TimeUnit.SECONDS);
            LOGGER.debug("read. locked: " + lock.isLocked());
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
//            FileLock lock = randomAccessFile.getChannel().lock();
            InputStream is = Channels.newInputStream(randomAccessFile.getChannel().position(0L));
            properties.load(is);
//            if (lock != null) {
//                lock.release();
//            }
            randomAccessFile.getChannel().close();
            is.close();
        } catch (Exception e) {
            LOGGER.error("Couldn't load properties from file [" + filename + "]", e);
        } finally {
            if (lock.isLocked()) {
                lock.unlock();
                LOGGER.debug("read. lock unlocked");
            }
        }
        return properties;
    }

    public void savePropertiesToFile(String filename, Properties properties) {
        if (CollectionUtils.isNotEmpty(properties.keySet())) {
            try {
                LOGGER.debug("write. trying to lock...");
                lock.tryLock(2, TimeUnit.SECONDS);
                LOGGER.debug("write. locked: " + lock.isLocked());
                FileChannel fileChannel = FileChannel.open(Paths.get(filename), StandardOpenOption.WRITE);
//                FileLock lock = fileChannel.lock();
//                LOGGER.debug("write. lock valid: " + lock.isValid());
                OutputStream os = Channels.newOutputStream(fileChannel.position(0L));
                properties.store(os, null);
                os.close();
                fileChannel.close();
            } catch (Exception e) {
                LOGGER.error("Could not save properties to file [" + filename + "]", e);
            } finally {
                if (lock.isLocked()) {
                    lock.unlock();
                    LOGGER.debug("lock unlocked");
                }
            }
        }
    }

    public boolean removeFile(String filename) {
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
