package com.mentoring.gradle.dao;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Andrey Yun on 28.02.2016.
 */


public class MessageDaoImpl implements MessageDao {

    private static final Logger LOG = Logger.getLogger(MessageDaoImpl.class);
    @Override
    public String retrieveMessageFromFile() throws IOException {
        String res = "";
        InputStream is = null;
        try {
            is = getClass().getClassLoader().getResourceAsStream("messages.txt");
            res = IOUtils.toString(is);
        } catch (IOException e) {
            LOG.error("IO exception", e);
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return res;
    }
}
