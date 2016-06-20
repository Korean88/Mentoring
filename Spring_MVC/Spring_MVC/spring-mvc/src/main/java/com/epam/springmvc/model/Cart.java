package com.epam.springmvc.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Andrey on 15.05.2016.
 */

@Component
@Scope("session")
public class Cart {

    private List<Meal> allMeals = new ArrayList<>();

    public List<Meal> getAllMeals() {
        if (allMeals.size() != 0) {
            allMeals.sort((Meal m1, Meal m2) -> m1.getName().compareTo(m2.getName()));
        }
        return allMeals;
    }

    public void setAllMeals(List<Meal> allMeals) {
        this.allMeals = allMeals;
    }

    public Map<Meal, Integer> summarize() {
        return allMeals.stream().collect(Collectors.groupingBy(Function.identity(),
                Collectors.summingInt(m -> 1)));

    }

    public int getCartSize() {
        return allMeals.size();
    }
}
