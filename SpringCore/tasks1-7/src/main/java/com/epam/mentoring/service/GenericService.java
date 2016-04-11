package com.epam.mentoring.service;

import java.util.Map;

/**
 * Created by Andrey on 05.04.2016.
 */

public interface GenericService<T> {

    T findById(Integer id);

    void print(Integer id);

    Map<Integer, T> getAll();

}
