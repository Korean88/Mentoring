package com.epam.mentoring.service;

import com.epam.mentoring.model.Order;
import com.epam.mentoring.repository.GenericRepository;

import java.util.Date;
import java.util.Map;

/**
 * Created by Andrey on 10.04.2016.
 */

public class OrderServiceImpl implements GenericService<Order> {

    private GenericRepository<Order> orderRepository;
    private Date additionalParameter;

    public OrderServiceImpl(GenericRepository<Order> orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Date getAdditionalParameter() {
        return additionalParameter;
    }

    public void setAdditionalParameter(Date additionalParameter) {
        this.additionalParameter = additionalParameter;
    }

    @Override
    public Order findById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public void print(Integer id) {
        Order order = findById(id);
        System.out.println(order);
    }

    @Override
    public Map<Integer, Order> getAll() {
        return orderRepository.getAll();
    }
}
