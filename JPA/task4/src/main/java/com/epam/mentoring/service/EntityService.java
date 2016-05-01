package com.epam.mentoring.service;

/**
 * Created by Andrey on 10.04.2016.
 */
public interface EntityService<T> {

    T add(T entity);

    T find(Integer id);

    boolean delete(Integer id);

    boolean update(Integer id, T entity);

}
