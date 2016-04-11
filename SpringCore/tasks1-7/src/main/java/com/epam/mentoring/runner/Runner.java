package com.epam.mentoring.runner;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Andrey on 04.04.2016.
 */
public class Runner {

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        //Get bean by type (task7)
        Task1 task1 = context.getBean(Task1.class);
        //Get bean by name (task7)
//        Task1 task1 = (Task1) context.getBean("task1");
        task1.performTask1_4();

        //Get bean by name and type (task7)
        Task6 task6 = context.getBean("task6", Task6.class);
        task6.performTask6();

        context.close();
    }
}
