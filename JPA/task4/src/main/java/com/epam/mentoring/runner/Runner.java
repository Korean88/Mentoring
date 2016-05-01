package com.epam.mentoring.runner;

import com.epam.mentoring.config.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Andrey Yun on 30.03.2016.
 */
public class Runner {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        EntityManipulator entityManipulator = ctx.getBean(EntityManipulator.class, SpringConfig.ENTITY_MANIPULATOR);
        entityManipulator.createEntities();
        ctx.close();
    }

}
