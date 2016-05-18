package com.epam.springmvc.aspect;

import com.epam.springmvc.model.Meal;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Andrey on 17.05.2016.
 */

@Aspect
@Component
public class MeasureProfiler {

    private static final Logger LOGGER = Logger.getLogger(MeasureProfiler.class);

    @Around("execution(* com.epam.springmvc.service.MealService.getFileForMeal(..))")
    public File measureFileRetrieval(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        File res = (File) joinPoint.proceed();
        long end = System.currentTimeMillis();
        long time = end - start;
        LOGGER.info(joinPoint.getSignature().getName() + " with param [" +
                Arrays.asList(joinPoint.getArgs()).stream().map(o -> o.toString()).collect(Collectors.joining(", "))
                + "] was called. Processing took " + time + " ms"
        );
        return res;
    }

    @Around("execution(* com.epam.springmvc.service.MealService.fetchAllMeals(..))")
    public Set<Meal> measureFetchAllMeals(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Set<Meal> res = (Set<Meal>) joinPoint.proceed();
        long end = System.currentTimeMillis();
        long time = end - start;
        LOGGER.info(joinPoint.getSignature().getName() + " was called. Processing took " + time + " ms");
        return res;
    }

}
