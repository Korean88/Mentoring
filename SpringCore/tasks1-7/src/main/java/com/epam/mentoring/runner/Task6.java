package com.epam.mentoring.runner;

import com.epam.mentoring.model.Cashier;
import com.epam.mentoring.service.GenericService;

import javax.annotation.Resource;

/**
 * Created by Andrey on 11.04.2016.
 */
public class Task6 {

    //Get bean by alias (task7)
    @Resource(name = "aliasService")
    private GenericService<Cashier> cashierAliasService;

    public void performTask6() {
        System.out.println("***Task6***");
        cashierAliasService.print(1);
    }
}
