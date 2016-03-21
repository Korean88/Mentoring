package com.epam.mentoring.ws.util;

import com.epam.mentoring.ws.model.User;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 * Created by Andrey on 21.03.2016.
 */
public class UserJsonHelperTest {

    @Test
    public void shouldFindUserByLogin() {
        User eve = new User("Eve", "Nigel", "eve", "eve@evil.com");
        User user = UserJsonHelper.findUserByLogin("eve");
        assertThat(user, equalTo(eve));
    }

    @Test
    public void shouldReturnNullIfUserDoesNotExist() {
        User user = UserJsonHelper.findUserByLogin("noUser");
        assertThat(user, is(nullValue()));
    }

}
