package com.epam.mentoring.ws.rest;

import com.epam.mentoring.ws.model.User;
import com.epam.mentoring.ws.util.UserDao;
import org.apache.log4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Andrey on 22.03.2016.
 */
@Path("/")
public class UserRest {

    private static final Logger LOGGER = Logger.getLogger(UserRest.class);

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_XML})
    public User[] getAllUsersXml() {
        return UserDao.getAllUsers();
    }

    @GET
    @Path("/all-json")
    @Produces({MediaType.APPLICATION_JSON})
    public User[] getAllUsersJson() {
        return UserDao.getAllUsers();
    }

    @GET
    @Path("/user/{login}")
    @Produces({MediaType.APPLICATION_XML})
    public User findUserByLogin(@PathParam("login") String login) {
        return UserDao.findUserByLogin(login);
    }

    @POST
    @Path("/create")
    @Produces({MediaType.TEXT_PLAIN})
    @Consumes({MediaType.APPLICATION_XML})
    public Response createUser(String userXml) throws URISyntaxException {
        User user = UserDao.convertXmlToUser(userXml);
        UserDao.addNewUser(user);
        return Response.created(new URI("http://localhost:8081/task1/user/" + user.getLogin())).build();
    }

    @PUT
    @Path("/update/{login}")
    @Produces({MediaType.TEXT_PLAIN})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateUser(@PathParam("login") String login, User user) {
        boolean updated = UserDao.updateUser(login, user);
        if (updated) {
            return Response.ok("A user with login " + login + " was updated", MediaType.TEXT_PLAIN).build();
        } else {
            return Response.notModified().build();
        }
    }

    @DELETE
    @Path("/delete/{login}")
    @Produces({MediaType.TEXT_PLAIN})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response deleteUser(@PathParam("login") String login) {
        boolean deleted = UserDao.deleteUser(login);
        if (deleted) {
            return Response.ok("A user with login " + login + " was deleted", MediaType.TEXT_PLAIN).build();
        } else {
            return Response.notModified().build();
        }
    }

}
