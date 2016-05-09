package com.epam.mentoring.model;

/**
 * Created by Andrey on 04.04.2016.
 */
public class Meal {

    private Integer id;
    private Recipe recipe;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", recipe=" + recipe +
                '}';
    }
}
