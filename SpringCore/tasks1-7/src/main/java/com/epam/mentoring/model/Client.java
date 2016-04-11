package com.epam.mentoring.model;

/**
 * Created by Andrey on 04.04.2016.
 */
public class Client extends Person {

    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Client{id:" + this.getId() +
                ", order=" + order +
                '}';
    }
}
