package com.epam.springmvc.dao;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by Andrey Yun on 09.05.2016.
 */
public class MealDaoTest {

    private MealDaoImpl mealDao = new MealDaoImpl();

    @Test
    public void shouldExtractFileNameFromValidPath() {
        String path = "http://localhost:8080/fastfood/images/mushroom_bur.ger.png";
        String res = mealDao.extractFileName(path);
        assertThat(res, equalTo("mushroom_bur.ger.png"));
    }

    @Test
    public void shouldExtractFileNameFromValidPathWithDash() {
        String path = "/resources/img/vegi-burger.jpg";
        String res = mealDao.extractFileName(path);
        assertThat(res, equalTo("vegi-burger.jpg"));
    }
}
