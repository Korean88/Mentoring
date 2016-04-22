package com.epam.springmvc.dao;


import com.epam.springmvc.model.User;

/**
 * Created by Andrey Yun on 14.04.2016.
 */
public interface UserDao {

    User addUser(User user);

    User login(String login, String password);

    User findById(Integer id);

    boolean updateUser(Integer id, User user);

    boolean deleteUser(Integer id);
}
