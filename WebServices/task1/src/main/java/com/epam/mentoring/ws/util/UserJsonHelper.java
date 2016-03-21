package com.epam.mentoring.ws.util;

import com.epam.mentoring.ws.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Andrey on 21.03.2016.
 */
public class UserJsonHelper {

    private static final Logger LOGGER = Logger.getLogger(UserJsonHelper.class);
    private static final String JSON_FILE_NAME = "users.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static File file = new File(UserJsonHelper.class.getClassLoader().getResource(JSON_FILE_NAME).getFile());

    public static User findUserByLogin(String login) {
        if (login == null) {
            throw new IllegalArgumentException("login must not be null");
        }
        User[] users = getAllUsers();
        for (User user : users) {
            if (login.equals(user.getLogin())) {
                return user;
            }
        }
        return null;
    }

    public static void persistUsersInFile(User[] users) {
        if (users == null) {
            throw new IllegalArgumentException("users must not be null");
        }
        try {
            objectMapper.writeValue(file, users);
        } catch (IOException e) {
            LOGGER.error("Could not save users to file '" + JSON_FILE_NAME + "'", e);
        }
    }

    public static User[] getAllUsers() {
        try {
            return objectMapper.readValue(file, User[].class);
        } catch (IOException e) {
            LOGGER.error("Could not read users from file '" + JSON_FILE_NAME + "'", e);
        }
        return null;
    }

    public static void addNewUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User must not be null");
        }
        User[] users = getAllUsers();
        User[] newUsers = new User[users.length + 1];
        for (int i = 0; i < users.length; i++) {
            newUsers[i] = users[i];
        }
        newUsers[users.length] = user;
        persistUsersInFile(newUsers);
    }

    public static void updateUser(String login, User newUserInfo) {
        if (login == null || newUserInfo == null) {
            throw new IllegalArgumentException("login and userInfo must not be null");
        }
        User[] allUsers = getAllUsers();
        for (int i = 0; i < allUsers.length; i++) {
            if (Objects.equals(allUsers[i].getLogin(), login)) {
                allUsers[i] = newUserInfo;
                persistUsersInFile(allUsers);
                return;
            }
        }
    }

    public static void deleteUser(String login) {
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
            UserJsonHelper.persistUsersInFile(userList.toArray(users));
        }


    }
}
