package com.mentoring.gradle.service;

import com.mentoring.gradle.dao.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by Andrey Yun on 28.02.2016.
 */
public class GreetingServiceImpl implements GreetingService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public String sayHello() throws IOException {
        String name = messageDao.retrieveMessageFromFile();
        return "Hello " + name;
    }
}
