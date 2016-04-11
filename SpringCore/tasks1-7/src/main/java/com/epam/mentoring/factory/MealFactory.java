package com.epam.mentoring.factory;

import com.epam.mentoring.model.Meal;
import com.epam.mentoring.model.Recipe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Andrey on 10.04.2016.
 */
public class MealFactory {

    private static AtomicInteger sequentialId = new AtomicInteger(0);
    private  RecipeFactory recipeFactory = new RecipeFactory();

    public Meal produceHamburgerMeal() {
        return createWithRecipe(recipeFactory.createHamburgerRecipe());
    }

    public Meal produceCheeseburgerMeal() {
        return createWithRecipe(recipeFactory.createCheeseburgerRecipe());
    }

    public Meal produceCokeMeal() {
        return createWithRecipe(recipeFactory.createCokeRecipe());
    }

    public Meal produceGreekSaladMeal() {
        return createWithRecipe(recipeFactory.createGreekSaladRecipe());
    }

    private Meal createWithRecipe(Recipe recipe) {
        Meal meal = new Meal();
        meal.setId(nextId());
        meal.setRecipe(recipe);
        return meal;
    }

    private synchronized Integer nextId() {
        return sequentialId.incrementAndGet();
    }
}
