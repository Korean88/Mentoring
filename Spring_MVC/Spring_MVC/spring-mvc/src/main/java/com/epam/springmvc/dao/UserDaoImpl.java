package com.epam.springmvc.dao;


import com.epam.springmvc.model.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 * Created by Andrey Yun on 14.04.2016.
 */

@Repository(value = "userDao")
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager em;

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    @Override
    public User addUser(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public User login(String login, String password) {
        TypedQuery<User> queryLogin = em.createNamedQuery(User.FIND_BY_LOGIN_PASS, User.class);
        queryLogin.setParameter("login", login);
        queryLogin.setParameter("pass", password);
        try {
            return queryLogin.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.warn("No result found for login: " + login + " and password: " + password);
        }
        return null;
    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public boolean updateUser(Integer id, User user) {
        return false;
    }

    @Override
    public boolean deleteUser(Integer id) {
        return false;
    }
}
