package com.epam.mentoring.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Andrey Yun on 31.03.2016.
 */

@Component
public class EntityManagerProvider {

    private static final Logger LOG = Logger.getLogger(EntityManagerProvider.class);

    private static final String EMBEDDED_DB_PATH = "db/employees.odb";
    private EntityManager em;
    private EntityManagerFactory emf;

    @PostConstruct
    public void init() {
        LOG.debug("In postConstruct...");
        emf = Persistence.createEntityManagerFactory(EMBEDDED_DB_PATH);
        em = emf.createEntityManager();
    }

    public EntityManager getEm() {
        return em;
    }

    @PreDestroy
    public void close() {
        em.close();
        emf.close();
        LOG.debug("In PreDestroy...");
    }

}
