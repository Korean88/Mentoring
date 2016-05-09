package com.epam.springmvc.dao;

import com.epam.springmvc.model.Meal;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        saveFile(meal, multipartFile);
        em.persist(meal);
        return meal;
    }

    private void saveFile(Meal meal, MultipartFile multipartFile) {
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
    }

    @Override
    public Meal findById(Integer id) {
        TypedQuery<Meal> mealQuery = em.createNamedQuery(Meal.FIND_BY_ID, Meal.class);
        mealQuery.setParameter("id", id);
        Meal result = null;
        try {
            result = mealQuery.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.warn("No meal with id:" + id + " was found");
        }
        return result;
    }

    @Override
    public boolean editMeal(Integer id, Meal meal, MultipartFile file) {
        Meal old = findById(id);
        if (old != null) {
            old.setName(meal.getName());
            old.setPrice(meal.getPrice());
            old.setDiabetic(meal.getDiabetic());
            old.setVegetarian(meal.getVegetarian());
            if (file.getSize() > 0) {
                if (!removeFile(old.getImagePath())) {
                    LOGGER.warn("Couldn't remove file + " + old.getImagePath());
                }
                saveFile(old, file);
            }
            em.merge(old);
        } else {
            LOGGER.warn("Could not edit meal with id:" + id);
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteMeal(Integer id) {
        Meal meal = findById(id);
        if (meal != null) {
            em.remove(meal);
            return true;
        } else {
            LOGGER.warn("Meal with id " + id + " was not found");
            return false;
        }
    }

    private boolean removeFile(String path) {
        String filename = extractFileName(path);
        if (filename != null && !"".equals(filename)) {
            File file = new File(picsDir + File.separator + filename);
            return file.delete();
        } else {
            LOGGER.info("File [" + path + "] not found");
            return false;
        }
    }

    String extractFileName(String path) {
        String regex = ".+/([\\w[.]]+\\.[a-zA-Z0-9]+)\\z";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(path);
        if (matcher.matches()) {
            return matcher.group(1);
        } else {
            return "";
        }
    }
}
