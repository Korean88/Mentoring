package com.epam.mentoring.runner;

import com.epam.mentoring.model.Cashier;
import com.epam.mentoring.model.Client;
import com.epam.mentoring.model.Order;
import com.epam.mentoring.service.GenericService;
import com.epam.mentoring.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by Andrey on 11.04.2016.
 */
/*
 * Class for performing tasks 1-5
 */
public class Task1 {

    @Autowired //Autowiring by type (task4)
    private GenericService<Order> orderService;

    @Autowired
    private GenericService<Cashier> cashierService;

    @Autowired
    private GenericService<Client> clientService;

    public void performTask1_4() {
        System.out.println(((OrderServiceImpl)orderService).getAdditionalParameter());
        Map<Integer, Order> allOrders = orderService.getAll();
        for (Order o : allOrders.values()) {
            orderService.print(o.getId());
        }

        cashierService.print(1);
        clientService.print(1);
    }
}
