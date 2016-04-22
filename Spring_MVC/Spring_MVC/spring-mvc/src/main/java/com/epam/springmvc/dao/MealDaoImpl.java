package com.epam.springmvc.dao;

import com.epam.springmvc.model.Meal;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Andrey Yun on 16.04.2016.
 */
@Repository
@Transactional
public class MealDaoImpl implements MealDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Set<Meal> findAll() {
        TypedQuery<Meal> mealsQuery = em.createNamedQuery(Meal.FIND_ALL, Meal.class);
        return new HashSet<>(mealsQuery.getResultList());
    }

    @Override
    public Meal addMeal(Meal meal) {
        em.persist(meal);
        return meal;
    }
}
