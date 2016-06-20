package com.epam.springmvc.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by Andrey Yun on 18.05.2016.
 */

@Aspect
@Component
public class FunctionalityAccessLogger {

    private static final Logger LOGGER = Logger.getLogger(FunctionalityAccessLogger.class);

    @Around("execution(* com.epam.springmvc.controller.CartController.addMealToCart(..))")
    public String logCartAddition(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.info(joinPoint.getSignature().getName() + " was called. " +
                "A meal with id [" + joinPoint.getArgs()[1] + "] was added to the cart.");
        return (String) joinPoint.proceed();
    }

    @Around("execution(* com.epam.springmvc.controller.CartController.deleteMealFromCart(..))")
    public String logCartDeletion(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.info(joinPoint.getSignature().getName() + " was called. " +
                "A meal with id [" + joinPoint.getArgs()[1] + "] was removed from the cart.");
        return (String) joinPoint.proceed();
    }
}
