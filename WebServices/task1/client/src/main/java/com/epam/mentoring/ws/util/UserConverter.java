package com.epam.mentoring.ws.util;

import com.epam.mentoring.ws.model.User;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Andrey on 22.03.2016.
 */
public class UserConverter {

    private static final Logger LOGGER = Logger.getLogger(UserConverter.class);

    public static String convertUserToXml(User user) {
        String res = null;
        if (user != null) {
            try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
                JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.marshal(user, os);
                res = new String(os.toByteArray());
            } catch (IOException e) {
                LOGGER.error("Could not convert user to XML", e);
            } catch (JAXBException e) {
                LOGGER.error("Could not marshall user", e);
            }
        }
        return res;
    }
}
