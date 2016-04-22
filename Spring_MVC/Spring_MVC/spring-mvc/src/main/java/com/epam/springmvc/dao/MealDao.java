package com.epam.springmvc.dao;

import com.epam.springmvc.model.Meal;

import java.util.Set;

/**
 * Created by Andrey Yun on 16.04.2016.
 */
public interface MealDao {

    Set<Meal> findAll();

    Meal addMeal(Meal meal);

}
