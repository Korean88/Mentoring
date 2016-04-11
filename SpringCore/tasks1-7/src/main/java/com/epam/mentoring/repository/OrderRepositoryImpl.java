package com.epam.mentoring.repository;

import com.epam.mentoring.model.Order;

import java.util.Map;

/**
 * Created by Andrey on 10.04.2016.
 */
public class OrderRepositoryImpl implements GenericRepository<Order> {

    private Map<Integer, Order> orderResource;

    public Map<Integer, Order> getOrderResource() {
        return orderResource;
    }

    public void setOrderResource(Map<Integer, Order> orderResource) {
        this.orderResource = orderResource;
    }

    @Override
    public Order findById(Integer id) {
        return orderResource.get(id);
    }

    @Override
    public Map<Integer, Order> getAll() {
        return orderResource;
    }
}
