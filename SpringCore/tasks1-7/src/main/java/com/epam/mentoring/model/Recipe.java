package com.epam.mentoring.model;

import java.util.Map;

/**
 * Created by Andrey on 04.04.2016.
 */
public class Recipe {

    private Integer id;
    private Map<String, String> ingredients;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<String, String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<String, String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", ingredients=" + ingredients +
                '}';
    }
}
