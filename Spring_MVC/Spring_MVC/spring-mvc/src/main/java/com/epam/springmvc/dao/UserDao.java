package com.epam.springmvc.dao;

import com.epam.springmvc.model.User;

/**
 * Created by Andrey on 14.05.2016.
 */
public interface UserDao {

    User findByLogin(String login);
}
