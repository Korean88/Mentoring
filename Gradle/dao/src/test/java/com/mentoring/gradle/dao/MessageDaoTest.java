package com.mentoring.gradle.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by Andrey Yun on 28.02.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoConfig.class})
public class MessageDaoTest {

    @Resource
    private MessageDao messageDao;

    @Test
    public void shouldGetMessageFromFile() throws IOException {
        String res = messageDao.retrieveMessageFromFile();
        assertThat(res, equalTo("World"));
    }
}
