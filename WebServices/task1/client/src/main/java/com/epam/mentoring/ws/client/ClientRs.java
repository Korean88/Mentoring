package com.epam.mentoring.ws.client;

import com.epam.mentoring.ws.model.User;
import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * Created by Andrey on 22.03.2016.
 */
public class ClientRs {

    private static final String BASE_URI = "http://localhost:8081/task1";
    private static final Logger LOGGER = Logger.getLogger(ClientRs.class);
    public static final Client client = ClientBuilder.newClient();
    private static final int BUFFER_SIZE = 1024;
    public static final String LOGO_EXT = ".jpg";

    public User[] getAllUsers() {
        return client.target(BASE_URI)
                .path("all")
                .request()
                .accept(MediaType.APPLICATION_XML)
                .get(new GenericType<User[]>() {
                });
    }

    public User findUser(String login) {
        return client.target(BASE_URI)
                .path("user")
                .path(login)
                .request()
                .accept(MediaType.APPLICATION_XML)
                .get(new GenericType<User>() {
                });
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

    public boolean uploadImage(String filename) {
        client.register(MultiPartFeature.class);
        URL url = getClass().getClassLoader().getResource(filename);
        if (url == null) {
            LOGGER.error("File " + filename + " was not found");
            return false;
        }
        File file = new File(url.getFile());
        FileDataBodyPart filePart = new FileDataBodyPart("image", file);
        filePart.setContentDisposition(FormDataContentDisposition
                .name("image").fileName(filename).build());
        MultiPart multipartEntity = new FormDataMultiPart()
                .bodyPart(filePart);
        Response response = client.target(BASE_URI)
                .path("image")
                .path("upload")
                .request()
                .post(Entity.entity(multipartEntity, MediaType.MULTIPART_FORM_DATA), Response.class);
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            LOGGER.info("Image " + filename + " was uploaded on server");
            return true;
        } else {
            LOGGER.warn("Image " + filename + " was not uploaded on server. Status: " + response.getStatus());
            return false;
        }
    }

    public boolean downloadImage(String filename) {
        Response response = client.target(BASE_URI)
                .path("image")
                .path("download")
                .path(filename)
                .request()
                .accept(MediaType.APPLICATION_OCTET_STREAM_TYPE)
                .get();
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            LOGGER.warn("Image " + filename + " was not downloaded. Status code: " + response.getStatus());
            return false;
        }
        String dirName = "downloaded";
        new File(dirName).mkdir();
        try (OutputStream os = new FileOutputStream(new File(dirName + File.separator + filename + LOGO_EXT));
             InputStream is = (InputStream) response.getEntity()) {
            int read;
            byte[] bytes = new byte[BUFFER_SIZE];
            while ((read = is.read(bytes)) != -1) {
                os.write(bytes, 0, read);
            }
            os.flush();
            LOGGER.info("Downloaded logo: " + filename + LOGO_EXT);
            return true;
        } catch (IOException e) {
            LOGGER.error("Could not write image on disk", e);
        }
        return false;
    }
}
