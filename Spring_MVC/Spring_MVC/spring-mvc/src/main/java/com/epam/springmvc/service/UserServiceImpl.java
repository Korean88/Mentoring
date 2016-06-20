package com.epam.springmvc.service;

import com.epam.springmvc.dao.UserDao;
import com.epam.springmvc.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Andrey on 14.05.2016.
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }
}
