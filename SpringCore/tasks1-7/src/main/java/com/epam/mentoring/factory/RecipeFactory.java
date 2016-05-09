package com.epam.mentoring.factory;

import com.epam.mentoring.model.Recipe;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Andrey on 10.04.2016.
 */
public class RecipeFactory {

    private static AtomicInteger sequentialId = new AtomicInteger(0);
    private Map<MenuItem, Recipe> existingRecipes = new ConcurrentHashMap<>();

    public Recipe createHamburgerRecipe() {
        Recipe recipe;
        if (existingRecipes.get(MenuItem.HAMBURGER) == null) {
            recipe = new Recipe();
            recipe.setId(nextId());
            Map<String, String> ingredients = new HashMap<>();
            ingredients.put("bread", "2 pcs");
            ingredients.put("burger", "1 pcs");
            ingredients.put("salad", "2 pcs");
            ingredients.put("ketchup", "2 g");
            ingredients.put("mayonnaise", "2 g");
            recipe.setIngredients(ingredients);
            existingRecipes.put(MenuItem.HAMBURGER, recipe);
        } else {
            recipe = existingRecipes.get(MenuItem.HAMBURGER);
        }
        return recipe;
    }

    public Recipe createCheeseburgerRecipe() {
        Recipe recipe;
        if (existingRecipes.get(MenuItem.CHEESEBURGER) == null) {
            recipe = new Recipe();
            recipe.setId(nextId());
            Map<String, String> ingredients = new HashMap<>();
            ingredients.put("bread", "2 pcs");
            ingredients.put("burger", "1 pcs");
            ingredients.put("salad", "2 pcs");
            ingredients.put("cheese", "2 slices");
            ingredients.put("ketchup", "2 g");
            ingredients.put("mayonnaise", "2 g");
            recipe.setIngredients(ingredients);
            existingRecipes.put(MenuItem.CHEESEBURGER, recipe);
        } else {
            recipe = existingRecipes.get(MenuItem.CHEESEBURGER);
        }
        return recipe;
    }

    public Recipe createCokeRecipe() {
        Recipe recipe;
        if (existingRecipes.get(MenuItem.COKE) == null) {
            recipe = new Recipe();
            recipe.setId(nextId());
            Map<String, String> ingredients = new HashMap<>();
            ingredients.put("water", "200 ml");
            ingredients.put("soda", "3 g");
            ingredients.put("sugar", "20 g");
            ingredients.put("flavor", "25 g");
            ingredients.put("dye", "1 g");
            recipe.setIngredients(ingredients);
            existingRecipes.put(MenuItem.COKE, recipe);
        } else {
            recipe = existingRecipes.get(MenuItem.COKE);
        }
        return recipe;
    }

    public Recipe createGreekSaladRecipe() {
        Recipe recipe;
        if (existingRecipes.get(MenuItem.GREEK_SALAD) == null) {
            recipe = new Recipe();
            recipe.setId(nextId());
            Map<String, String> ingredients = new HashMap<>();
            ingredients.put("salad", "180 g");
            ingredients.put("olives", "100 g");
            ingredients.put("cheese", "150 g");
            ingredients.put("cucumbers", "120 g");
            ingredients.put("tomato", "100 g");
            ingredients.put("salt", "5 g");
            ingredients.put("olive oil", "10 g");
            recipe.setIngredients(ingredients);
            existingRecipes.put(MenuItem.GREEK_SALAD, recipe);
        } else {
            recipe = existingRecipes.get(MenuItem.GREEK_SALAD);
        }
        return recipe;
    }

    private synchronized Integer nextId() {
        return sequentialId.incrementAndGet();
    }
}
