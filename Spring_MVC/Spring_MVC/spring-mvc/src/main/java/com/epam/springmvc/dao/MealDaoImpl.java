package com.epam.springmvc.dao;

import com.epam.springmvc.model.Meal;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StringMultipartFileEditor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Andrey Yun on 16.04.2016.
 */
@Repository
@Transactional
public class MealDaoImpl implements MealDao {

    private static final Logger LOGGER  =Logger.getLogger(MealDaoImpl.class);

    @Value("${tomcat.dir}")
    private String tomcatDir;

    @Value("${pics.url}")
    private String picsUrl;

    @Value("${pics.dir}")
    private String picsDir;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Set<Meal> findAll() {
        TypedQuery<Meal> mealsQuery = em.createNamedQuery(Meal.FIND_ALL, Meal.class);
        return new HashSet<>(mealsQuery.getResultList());
    }

    @Override
    public Meal addMeal(Meal meal, MultipartFile multipartFile) {
        if (!multipartFile.isEmpty()) {
            try {
                byte[] bytes = multipartFile.getBytes();
                File dir = new File(picsDir);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                File file = new File(picsDir + File.separator + multipartFile.getOriginalFilename());
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(bytes);
                fos.close();
                meal.setImagePath(picsUrl + multipartFile.getOriginalFilename());
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        em.persist(meal);
        return meal;
    }
}
