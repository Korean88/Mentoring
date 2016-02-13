package com.epam.concurrency.car;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Andrey Yun on 13.02.2016.
 */
public class Runner {

    public static final int NUMBER_OF_CARS = 100;

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(NUMBER_OF_CARS);
        Random random = new Random();
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < NUMBER_OF_CARS; i++) {
            exec.execute(new Car(random.nextInt(50), latch));
        }
        exec.shutdown();
    }
}
