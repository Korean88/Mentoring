package com.epam.mentoring.repository;

import java.util.Map;

/**
 * Created by Andrey on 10.04.2016.
 */
public interface GenericRepository<T> {

    T findById(Integer id);

    Map<Integer, T> getAll();

}
