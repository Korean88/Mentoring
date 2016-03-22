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

//    @Test
//    public void shouldConvertUserToXml() throws JAXBException {
//        String res = UserHelper.convertUserToXml("eve");
//        assertThat(res, not(nullValue()));
//        assertThat(res, startsWith("<?xml "));
//        System.out.println(res);
//    }

    @Test
    public void shouldConvertXmlToUser() {
        //GIVEN
        String email = "eve@evil.com";
        String firstName = "Eve";
        String lastName = "Nigel";
        String login = "eve";
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                "<user><email>" + email + "</email><firstName>" + firstName + "</firstName>" +
                "<lastName>" + lastName + "</lastName><login>" + login + "</login></user>";
        //WHEN
        User user = UserDao.convertXmlToUser(xml);
        //THEN
        assertThat(user.getEmail(), equalTo(email));
        assertThat(user.getFirstName(), equalTo(firstName));
        assertThat(user.getLastName(), equalTo(lastName));
        assertThat(user.getLogin(), equalTo(login));
    }

}
