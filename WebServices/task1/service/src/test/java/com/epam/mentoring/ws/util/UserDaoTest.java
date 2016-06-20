package com.epam.mentoring.ws.util;

import com.epam.mentoring.ws.model.User;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Andrey on 21.03.2016.
 */
public class UserDaoTest {

    @Test
    public void shouldFindUserByLogin() {
        User eve = new User("Eve", "Nigel", "eve", "eve@evil.com");
        User user = UserDao.findUserByLogin("eve");
        assertThat(user, equalTo(eve));
    }

    @Test
    public void shouldReturnNullIfUserDoesNotExist() {
        User user = UserDao.findUserByLogin("noUser");
        assertThat(user, is(nullValue()));
    }

}
