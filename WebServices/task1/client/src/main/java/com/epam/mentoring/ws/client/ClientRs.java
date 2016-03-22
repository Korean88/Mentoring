package com.epam.mentoring.ws.client;

import com.epam.mentoring.ws.model.User;
import org.apache.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Andrey on 22.03.2016.
 */
public class ClientRs {

    private static final String BASE_URI = "http://localhost:8081/task1";
    private static final Logger LOGGER = Logger.getLogger(ClientRs.class);
    public static final Client client = ClientBuilder.newClient();

    public User[] getAllUsers() {
        return client.target(BASE_URI)
                .path("all")
                .request()
                .accept(MediaType.APPLICATION_XML)
                .get(new GenericType<User[]>() {});
    }

    public User findUser(String login) {
        return client.target(BASE_URI)
                .path("user")
                .path(login)
                .request()
                .accept(MediaType.APPLICATION_XML)
                .get(new GenericType<User>() {});
    }

    public boolean createUser(String xml) {
        Response response = client.target(BASE_URI)
                .path("create")
                .request()
                .post(Entity.xml(xml), Response.class);
        if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
            LOGGER.info("A new user was created: " + response.getLocation());
            return true;
        } else {
            LOGGER.warn("User was not created. Status code: " + response.getStatus());
            return false;
        }
    }

    public boolean updateUser(String login, String json) {
        Response response = client.target(BASE_URI)
                .path("update")
                .path(login)
                .request()
                .put(Entity.json(json), Response.class);
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            LOGGER.info("A user with login " + login + " was updated");
            return true;
        } else {
            LOGGER.warn("A user with login " + login + " was not updated. Status: " +
                    response.getStatus());
            return false;
        }
    }

    public boolean deleteUser(String login) {
        Response response = client.target(BASE_URI)
                .path("delete")
                .path(login)
                .request()
                .delete();
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            LOGGER.info("A user with login " + login + " was deleted");
            return true;
        } else {
            LOGGER.warn("A user with login " + login + " was not deleted. Status: " +
                    response.getStatus());
            return false;
        }
    }
}
