package com.epam.mentoring.ws.util;

import com.epam.mentoring.ws.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.apache.log4j.Logger;

import javax.ws.rs.core.StreamingOutput;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
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
    private static final int TIME_TO_WAIT = 1;
    private static final int BUFFER_SIZE = 1024;

    public static User findUserByLogin(String login) {
        if (login == null) {
            throw new IllegalArgumentException("login must not be null");
        }
        try {
            if (lock.tryLock(TIME_TO_WAIT, TimeUnit.SECONDS)) {
                User[] users = getAllUsers();
                for (User user : users) {
                    if (login.equals(user.getLogin())) {
                        return user;
                    }
                }
            } else {
                throw new RuntimeException("The resource is locked by another thread");
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
            if (lock.tryLock(TIME_TO_WAIT, TimeUnit.SECONDS)) {
                objectMapper.writeValue(file, users);
            } else {
                throw new RuntimeException("The resource is locked by another thread");
            }
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
            if (lock.tryLock(TIME_TO_WAIT, TimeUnit.SECONDS)) {
                return objectMapper.readValue(file, User[].class);
            } else {
                throw new RuntimeException("The resource is locked by another thread");
            }
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
            if (lock.tryLock(TIME_TO_WAIT, TimeUnit.SECONDS)) {
                User[] users = getAllUsers();
                User[] newUsers = new User[users.length + 1];
                for (int i = 0; i < users.length; i++) {
                    newUsers[i] = users[i];
                }
                newUsers[users.length] = user;
                persistUsersInFile(newUsers);
            } else {
                throw new RuntimeException("The resource is locked by another thread");
            }
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
            if (lock.tryLock(TIME_TO_WAIT, TimeUnit.SECONDS)) {
                User[] allUsers = getAllUsers();
                for (int i = 0; i < allUsers.length; i++) {
                    if (Objects.equals(allUsers[i].getLogin(), login)) {
                        allUsers[i] = newUserInfo;
                        persistUsersInFile(allUsers);
                        return true;
                    }
                }
            } else {
                throw new RuntimeException("The resource is locked by another thread");
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
            if (lock.tryLock(TIME_TO_WAIT, TimeUnit.SECONDS)) {
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
            } else {
                throw new RuntimeException("The resource is locked by another thread");
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

    public static boolean uploadUserLogo(InputStream is, String filename) {
        try {
            if (lock.tryLock(TIME_TO_WAIT, TimeUnit.SECONDS)) {
                File file = new File(filename);
                if (file.exists()) {
                    file.delete();
                    LOGGER.info("File " + filename + " was deleted");
                }
                boolean uploaded = writeToFile(is, filename);
                if (uploaded) {
                    LOGGER.info("File uploaded to : " + filename);
                    return true;
                } else {
                    LOGGER.warn("Could not upload file to : " + filename);
                }
            } else {
                throw new RuntimeException("The resource is locked by another thread");
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

    private static boolean writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {
        try (OutputStream os = new FileOutputStream(new File(uploadedFileLocation))) {
            int read;
            byte[] bytes = new byte[BUFFER_SIZE];
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                os.write(bytes, 0, read);
            }
            os.flush();
            return true;
        } catch (IOException e) {
            LOGGER.error("Could not write image on disk", e);
        }
        return false;
    }

    private static File getFileByName(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            return file;
        } else {
            return null;
        }
    }

    public static StreamingOutput getStreamingOutput(String filename) {
        File file = getFileByName(filename);
        if (file == null) {
            throw new RuntimeException("No file with the name " + filename + " exists");
        }
        return output -> {
            try {
                byte[] data = Files.readAllBytes(file.toPath());
                output.write(data);
                output.flush();
            } catch (IOException e) {
                LOGGER.error("Could not create StreamingOutput for file: " + filename);
            } finally {
                output.close();
            }
        };
    }

}
