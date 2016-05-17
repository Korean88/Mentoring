package com.epam.springmvc.dao;

import com.epam.springmvc.model.Meal;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Set;

/**
 * Created by Andrey Yun on 16.04.2016.
 */
public interface MealDao {

    Set<Meal> findAll();

    Meal addMeal(Meal meal, MultipartFile file);

    Meal findById(Integer id);

    boolean editMeal(Integer id, Meal meal, MultipartFile file);

    boolean deleteMeal(Integer id);

    File getFileForMeal(String imgPath);

}
