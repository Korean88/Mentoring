package com.epam.springmvc;

import com.epam.springmvc.dao.MealDao;
import com.epam.springmvc.model.Meal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.Set;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by Andrey Yun on 15.04.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml"})
@Transactional
public class PersistenceTest {



    @Resource
    MealDao mealDao;

    @Test
    public void shouldFindAllMeals() {
        Set<Meal> allMeals = mealDao.findAll();
        assertThat(allMeals, not(empty()));
    }

    @Test
    @Rollback(value = false)
    public void shoudSaveMeal() throws Exception {
        Meal meal = new Meal();
        meal.setName("test");
        meal.setPrice(new BigDecimal(10));
        meal.setDiabetic(false);
        meal.setVegetarian(true);
        String fileName = "coke.jpg";
        File file = new File(getClass().getClassLoader().getResource(fileName).toURI());
        byte[] bytes = Files.readAllBytes(file.toPath());
        MultipartFile multipartFile = new MockMultipartFile(fileName, bytes);
        Meal saved = mealDao.addMeal(meal, multipartFile);
        assertThat(saved.getId(), is(notNullValue()));
    }
}
