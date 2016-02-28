package com.mentoring.gradle.dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by Andrey Yun on 28.02.2016.
 */
public class MessageDaoTest {

    @Test
    public void shouldGetMessageFromFile() throws IOException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(DaoConfig.class);
        MessageDao messageDao = ctx.getBean("dao", MessageDao.class);
        String res = messageDao.retrieveMessageFromFile();
        assertThat(res, equalTo("World"));
    }
}
