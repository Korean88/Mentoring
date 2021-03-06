package com.epam.springmvc.service;

import com.epam.springmvc.dao.MealDao;
import com.epam.springmvc.model.Meal;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Andrey Yun on 16.04.2016.
 */
@Service
public class MealServiceImpl implements MealService {

    private static final Logger LOG = Logger.getLogger(MealServiceImpl.class);

    @Resource
    private MealDao mealDao;

    @Override
    public Set<Meal> fetchAllMeals() {
        Set<Meal> allMeals = mealDao.findAll();
        if (allMeals == null) {
            LOG.warn("No meals found in the database!");
            return new HashSet<>();
        }
        return allMeals;
    }

    @Override
    public Meal addMeal(Meal meal, MultipartFile file) {
        return mealDao.addMeal(meal, file);
    }

    @Override
    public Meal findById(Integer id) {
        return mealDao.findById(id);
    }

    @Override
    public boolean editMeal(Integer id, Meal meal, MultipartFile file) {
        if (mealDao.editMeal(id, meal, file)) {
            return true;
        } else {
            LOG.warn("Could not edit Meal with id:" + id + ", " + meal);
            return false;
        }
    }

    @Override
    public boolean deleteMeal(Integer id) {
        return mealDao.deleteMeal(id);
    }

    @Override
    public File getFileForMeal(Integer id) {
        Meal meal = mealDao.findById(id);
        String imgPath = meal.getImagePath();
        if (imgPath != null && !imgPath.isEmpty()) {
            return mealDao.getFileForMeal(imgPath);
        }
        return null;
    }
}
