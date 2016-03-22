package com.epam.mentoring.ws.util;

import com.epam.mentoring.ws.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Andrey on 21.03.2016.
 */
public class UserDao {

    private static final Logger LOGGER = Logger.getLogger(UserDao.class);
    private static final String JSON_FILE_NAME = "users.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static File file = new File(UserDao.class.getClassLoader().getResource(JSON_FILE_NAME).getFile());
    private static final ReentrantLock lock = new ReentrantLock();

    public static User findUserByLogin(String login) {
        if (login == null) {
            throw new IllegalArgumentException("login must not be null");
        }
        try {
            lock.tryLock(1, TimeUnit.SECONDS);
            User[] users = getAllUsers();
            for (User user : users) {
                if (login.equals(user.getLogin())) {
                    return user;
                }
            }
        } catch (InterruptedException e) {
            LOGGER.error("Lock has been interrupted", e);
        } finally {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
        return null;
    }

    public static void persistUsersInFile(User[] users) {
        if (users == null) {
            throw new IllegalArgumentException("users must not be null");
        }
        try {
            lock.tryLock(1, TimeUnit.SECONDS);
            objectMapper.writeValue(file, users);
        } catch (IOException e) {
            LOGGER.error("Could not save users to file '" + JSON_FILE_NAME + "'", e);
        } catch (InterruptedException e) {
            LOGGER.error("Lock has been interrupted", e);
        } finally {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
    }

    public static User[] getAllUsers() {
        try {
            lock.tryLock(1, TimeUnit.SECONDS);
            return objectMapper.readValue(file, User[].class);
        } catch (IOException e) {
            LOGGER.error("Could not read users from file '" + JSON_FILE_NAME + "'", e);
        } catch (InterruptedException e) {
            LOGGER.error("Lock has been interrupted", e);
        } finally {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
        return null;
    }

    public static void addNewUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User must not be null");
        }
        try {
            lock.tryLock(1, TimeUnit.SECONDS);
            User[] users = getAllUsers();
            User[] newUsers = new User[users.length + 1];
            for (int i = 0; i < users.length; i++) {
                newUsers[i] = users[i];
            }
            newUsers[users.length] = user;
            persistUsersInFile(newUsers);
        } catch (InterruptedException e) {
            LOGGER.error("Lock has been interrupted", e);
        } finally {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
    }

    public static boolean updateUser(String login, User newUserInfo) {
        if (login == null || newUserInfo == null) {
            throw new IllegalArgumentException("login and userInfo must not be null");
        }
        try {
            lock.tryLock(1, TimeUnit.SECONDS);
            User[] allUsers = getAllUsers();
            for (int i = 0; i < allUsers.length; i++) {
                if (Objects.equals(allUsers[i].getLogin(), login)) {
                    allUsers[i] = newUserInfo;
                    persistUsersInFile(allUsers);
                    return true;
                }
            }
            return false;
        } catch (InterruptedException e) {
            LOGGER.error("Lock has been interrupted", e);
            return false;
        } finally {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
    }

    public static boolean deleteUser(String login) {
        try {
            lock.tryLock(1, TimeUnit.SECONDS);
            User[] users = getAllUsers();
            List<User> userList = Lists.newArrayList(users);
            Iterator<User> iterator = userList.iterator();
            boolean found = false;
            while (iterator.hasNext()) {
                User current = iterator.next();
                if (Objects.equals(current.getLogin(), login)) {
                    iterator.remove();
                    found = true;
                    break;
                }
            }
            if (found) {
                User[] newUserArray = new User[userList.size()];
                UserDao.persistUsersInFile(userList.toArray(newUserArray));
                return true;
            }
        } catch (InterruptedException e) {
            LOGGER.error("Lock has been interrupted", e);
        } finally {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
        return false;
    }

    public static User convertXmlToUser(String userXml) {
        try (InputStream is = new ByteArrayInputStream(userXml.getBytes())) {
            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (User) unmarshaller.unmarshal(is);
        } catch (JAXBException e) {
            LOGGER.error("Could not unmarshall user", e);
        } catch (IOException e) {
            LOGGER.error("Could not read input xml", e);
        }
        return new User();
    }

//    public static String convertUserToXml(String login) {
//        User user = findUserByLogin(login);
//        return convertUserToXml(user);
//    }
//
//    public static String convertUserToXml(User user) {
//        String res = null;
//        if (user != null) {
//            try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
//                JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
//                Marshaller marshaller = jaxbContext.createMarshaller();
//                marshaller.marshal(user, os);
//                res = new String(os.toByteArray());
//            } catch (IOException e) {
//                LOGGER.error("Could not convert user to XML", e);
//            } catch (JAXBException e) {
//                LOGGER.error("Could not marshall user", e);
//            }
//        }
//        return res;
//    }
}
