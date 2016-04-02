package com.epam.mentoring.dao;

import com.epam.mentoring.model.IdentifiedEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * Created by Andrey Yun on 31.03.2016.
 */

@Repository
public abstract class EntityDao<T extends IdentifiedEntity> {

    private EntityManager em;
    private Class<T> tClass;
    private Logger LOG = Logger.getLogger(EntityDao.class);

    @Autowired
    private EntityManagerProvider entityManagerProvider;

    @PostConstruct
    public void init() {
        em = entityManagerProvider.getEm();
    }

    public EntityDao(Class<T> tClass) {
        this.tClass = tClass;
    }

    public T add(T entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        LOG.info("A new " + tClass.getName() + " with id [" + entity.getId() + "] was persisted successfully");
        return entity;
    }

    public T find(Integer id) {
        return em.find(tClass, id);
    }

    public boolean delete(Integer id) {
        T entity = find(id);
        if (entity != null) {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
            LOG.info(tClass.getName() + " with id [" + id + "] was deleted successfully");
            return true;
        } else {
            LOG.info(tClass.getName() + " with id [" + id + "] was not found for deletion");
        }
        return false;
    }

    public boolean update(Integer id, T entity) {
        T persisted = find(id);
        if (persisted != null) {
            em.getTransaction().begin();
            entity.setId(id);
            em.merge(entity);
            em.getTransaction().commit();
            LOG.info(tClass.getName() + " was updated successfully");
            return true;
        } else {
            LOG.info(tClass.getName() + " with id [" + id + "] was not found for update");
        }
        return false;
    }

}
