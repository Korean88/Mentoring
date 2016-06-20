package com.epam.mentoring.ws.rest;

import com.epam.mentoring.ws.model.User;
import com.epam.mentoring.ws.util.UserDao;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

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
import javax.ws.rs.core.StreamingOutput;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Andrey on 22.03.2016.
 */
@Path("/users")
public class UserRest {

    public static final String LOGO_EXT = ".jpg";

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_XML)
    public User[] getAllUsersXml() {
        return UserDao.getAllUsers();
    }

//    @GET
//    @Path("/")
//    @Produces(MediaType.APPLICATION_JSON)
//    public User[] getAllUsersJson() {
//        return UserDao.getAllUsers();
//    }

    @GET
    @Path("/{login}")
    @Produces(MediaType.APPLICATION_XML)
    public User findUserByLogin(@PathParam("login") String login) {
        return UserDao.findUserByLogin(login);
    }

    @POST
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_XML)
    public Response createUser(User user) throws URISyntaxException {
        UserDao.addNewUser(user);
        return Response.created(new URI("http://localhost:8081/task1/user/" + user.getLogin())).build();
    }

    @PUT
    @Path("/{login}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("login") String login, User user) {
        boolean updated = UserDao.updateUser(login, user);
        if (updated) {
            return Response.ok("A user with login " + login + " was updated", MediaType.TEXT_PLAIN).build();
        } else {
            return Response.notModified().build();
        }
    }

    @DELETE
    @Path("/{login}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("login") String login) {
        boolean deleted = UserDao.deleteUser(login);
        if (deleted) {
            return Response.ok("A user with login " + login + " was deleted", MediaType.TEXT_PLAIN).build();
        } else {
            return Response.notModified().build();
        }
    }

    @POST
    @Path("/image")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadImage(@FormDataParam("image") InputStream is,
                                @FormDataParam("image") FormDataContentDisposition contentDisposition) {
        String filename = contentDisposition.getFileName();
        boolean uploaded = UserDao.uploadUserLogo(is, filename);
        if (uploaded) {
            return Response.ok("Upload success", MediaType.TEXT_PLAIN).build();
        } else {
            return Response.notModified().build();
        }
    }

    @GET
    @Path("/image/{login}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFile(@PathParam("login") String login) {
        StreamingOutput so = UserDao.getStreamingOutput(login + LOGO_EXT);
        return Response.ok(so, MediaType.APPLICATION_OCTET_STREAM)
                .header("content-disposition","attachment; filename = " + login + LOGO_EXT)
                .build();
    }
}
