package com.epam.springmvc;

import com.epam.springmvc.dao.MealDao;
import com.epam.springmvc.dao.UserDao;
import com.epam.springmvc.model.Meal;
import com.epam.springmvc.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
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
    private UserDao userDao;

    @Resource
    MealDao mealDao;

    @Test
    @Rollback(value = true)
    public void shouldPersistUser() {
        User user = new User();
        user.setLogin("Vasiliy");
        user.setPassword("123456");
        user.setFirstName("Vasya");
        user.setLastName("Pupkin");
        userDao.addUser(user);
        assertThat(user.getId(), is(notNullValue()));
    }

    @Test
    public void shouldLoginJonnyDepp() {
        String login = "JonnyD";
        String password = "spring";
        User res = userDao.login(login, password);
        assertThat(res.getId(), is(notNullValue()));
        assertThat(res.getFirstName(), is("John"));
        assertThat(res.getLastName(), is("Depp"));
    }

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
