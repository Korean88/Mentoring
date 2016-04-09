package com.epam.mentoring.dao;

import com.epam.mentoring.model.IdentifiedEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Created by Andrey Yun on 31.03.2016.
 */

@Repository
@Transactional
public abstract class EntityDao<T extends IdentifiedEntity> {

    @PersistenceContext
    private EntityManager em;
    private Class<T> tClass;
    private Logger LOG = Logger.getLogger(EntityDao.class);

    public EntityDao(Class<T> tClass) {
        this.tClass = tClass;
    }

    public T add(T entity) {
        em.persist(entity);
        LOG.info("A new " + tClass.getName() + " with id [" + entity.getId() + "] was persisted successfully");
        return entity;
    }

    public T find(Integer id) {
        return em.find(tClass, id);
    }

    public boolean delete(Integer id) {
        T entity = find(id);
        if (entity != null) {
            em.remove(entity);
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
            entity.setId(id);
            em.merge(entity);
            LOG.info(tClass.getName() + " was updated successfully");
            return true;
        } else {
            LOG.info(tClass.getName() + " with id [" + id + "] was not found for update");
        }
        return false;
    }

}
