package com.epam.mentoring.ws.client;

import com.epam.mentoring.ws.model.User;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by Andrey on 22.03.2016.
 */
public class ClientRsTest {

    public static final ClientRs client = new ClientRs();

    @Test
    public void shouldReteurnAllUsers() {
        User[] allUsers = client.getAllUsers();
        assertThat(allUsers, not(nullValue()));
        assertThat(allUsers.length, is(5));
    }

    @Test
    public void testAllCRUDOperations() {
        //Create
        String login = "billy";
        String userXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                "<user><email>billibob@tornton.com</email><firstName>Billy</firstName>" +
                "<lastName>Bob</lastName><login>" + login + "</login></user>";
        boolean created = client.createUser(userXml);
        assertThat(created, is(true));
        User[] all = client.getAllUsers();
        assertThat(all.length, is(6));

        //Update
        String newEmail = "new@email.com";
        String json = "{\"firstName\":\"Billy\",\"lastName\":\"Bob\",\"login\":\"" + login + "\",\"email\":\"" + newEmail + "\"}";
        boolean updated = client.updateUser(login, json);
        assertThat(updated, is(true));
        User found = client.findUser(login);
        assertThat(found, not(nullValue()));
        assertThat(found.getEmail(), equalTo(newEmail));

        //Delete
        boolean deleted = client.deleteUser(login);
        assertThat(deleted, is(true));
        User afterDelete = client.findUser(login);
        assertThat(afterDelete, is(nullValue()));
    }

    @Test
    public void shouldStoreAndDownloadImage() {
        String filename = "eve";
        boolean uploaded = client.uploadImage(filename + ClientRs.LOGO_EXT);
        assertThat(uploaded, is(true));

        boolean downloaded = client.downloadImage(filename);
        assertThat(downloaded, is(true));
    }

}
