package com.epam.mentoring.model;

import java.util.List;

/**
 * Created by Andrey on 04.04.2016.
 */
public class Order {

    private Integer id;
    private List<Meal> meals;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", meals=" + meals +
                '}';
    }
}
