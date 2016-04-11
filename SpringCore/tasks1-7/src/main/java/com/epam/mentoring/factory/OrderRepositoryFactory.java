package com.epam.mentoring.factory;

import com.epam.mentoring.model.Meal;
import com.epam.mentoring.model.Order;
import com.epam.mentoring.repository.GenericRepository;
import com.epam.mentoring.repository.OrderRepositoryImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Andrey on 10.04.2016.
 */
public class OrderRepositoryFactory {

    private MealFactory mealFactory = new MealFactory();
    private static int sequentialId;
    private Random random = new Random();

    public static int getSequentialId() {
        return sequentialId;
    }
    public static void setSequentialId(int sequentialId) {
        OrderRepositoryFactory.sequentialId = sequentialId;
    }

    public GenericRepository<Order> createOrderRepository() {
        int numberOfOrders = random.nextInt(3) + 1;
        OrderRepositoryImpl orderRepository = new OrderRepositoryImpl();
        Map<Integer, Order> orderMap = new HashMap<>();
        int initialId = sequentialId;
        int maxId = sequentialId + numberOfOrders;
        for (int i = initialId; i < maxId; i++) {
            int numberOfMeals = random.nextInt(3) + 1;
            MenuItem[] meals = new MenuItem[numberOfMeals];
            for (int j = 0; j < numberOfMeals; j++) {
                int mealNumberInMenu = random.nextInt(MenuItem.values().length);
                meals[j] = MenuItem.values()[mealNumberInMenu];
            }
            orderMap.put(i, produceOrder(meals));
        }
        orderRepository.setOrderResource(orderMap);
        return orderRepository;
    }

    private Order produceOrder(MenuItem... menuItems) {
        Order order = new Order();
        order.setId(nextId());
        List<Meal> meals = new ArrayList<>();
        for (MenuItem item : menuItems) {
            switch (item) {
                case HAMBURGER:
                    meals.add(mealFactory.produceHamburgerMeal());
                    break;
                case CHEESEBURGER:
                    meals.add(mealFactory.produceCheeseburgerMeal());
                    break;
                case COKE:
                    meals.add(mealFactory.produceCokeMeal());
                    break;
                case GREEK_SALAD:
                    meals.add(mealFactory.produceGreekSaladMeal());
                    break;
                default:
                    throw new IllegalArgumentException("We don't know the meal's recipe");
            }
        }
        order.setMeals(meals);
        return order;
    }

    private synchronized Integer nextId() {
        return sequentialId++;
    }
}
