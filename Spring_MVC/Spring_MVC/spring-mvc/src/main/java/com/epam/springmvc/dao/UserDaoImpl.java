package com.epam.springmvc.dao;

import com.epam.springmvc.model.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Created by Andrey on 14.05.2016.
 */

@Repository(value = "userDao")
public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public User findByLogin(String login) {
        TypedQuery<User> query = em.createNamedQuery(User.FIND_BY_LOGIN, User.class);
        query.setParameter("login", login);
        User res = null;
        try {
            res = query.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.warn("No user found for login: " + login);
        }
        return res;
    }
}
