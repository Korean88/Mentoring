package com.epam.springmvc.service;

import com.epam.springmvc.model.Meal;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StringMultipartFileEditor;

import java.util.Set;

/**
 * Created by Andrey Yun on 16.04.2016.
 */
public interface MealService {

    Set<Meal> fetchAllMeals();

    Meal addMeal(Meal meal, MultipartFile file);

}
