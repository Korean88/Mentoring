package com.epam.mentoring.service;

import com.epam.mentoring.model.Cashier;
import com.epam.mentoring.repository.GenericRepository;

import java.util.Map;

/**
 * Created by Andrey on 05.04.2016.
 */

public class CashierServiceImpl implements GenericService<Cashier> {

    private final GenericRepository<Cashier> cashierRepository;

    public CashierServiceImpl(GenericRepository<Cashier> cashierRepository) {
        this.cashierRepository = cashierRepository;
    }

    @Override
    public Cashier findById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        return cashierRepository.findById(id);
    }

    @Override
    public void print(Integer id) {
        Cashier cashier = findById(id);
        System.out.println(cashier != null ? cashier : "Cashier with id " + id + " was not found");
    }

    @Override
    public Map<Integer, Cashier> getAll() {
        return cashierRepository.getAll();
    }

}
