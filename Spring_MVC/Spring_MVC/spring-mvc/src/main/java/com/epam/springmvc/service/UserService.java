package com.epam.springmvc.service;

import com.epam.springmvc.model.User;

/**
 * Created by Andrey on 14.05.2016.
 */
public interface UserService {

    User findByLogin(String login);
}
