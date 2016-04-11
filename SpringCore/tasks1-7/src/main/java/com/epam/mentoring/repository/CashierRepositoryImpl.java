package com.epam.mentoring.repository;

import com.epam.mentoring.model.Cashier;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Andrey on 04.04.2016.
 */

public class CashierRepositoryImpl implements GenericRepository<Cashier> {

    @Resource(name = "cashierResource")   //Resource annotation (task4)
    private Map<Integer, Cashier> cashierResource;

    @Override
    public Cashier findById(Integer id) {
        return cashierResource.get(id);
    }

    @Override
    public Map<Integer, Cashier> getAll() {
        return cashierResource;
    }

}
