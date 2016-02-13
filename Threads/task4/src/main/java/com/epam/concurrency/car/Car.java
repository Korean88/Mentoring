package com.epam.concurrency.car;

import org.apache.log4j.Logger;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Andrey Yun on 13.02.2016.
 */

public class Car implements Runnable {

    private static final long MAX_DISTANCE = 10000;
    private final CountDownLatch latch;
    private static final Logger LOGGER = Logger.getLogger(Car.class);

    private long friction = 100;
    private long distance;
    private boolean winner;

    private static int counter;
    public final int ID = ++counter;

    public Car(long friction, CountDownLatch latch) {
        this.friction = friction;
        this.latch = latch;
    }

    public String getName() {
        return "Car" + ID;
    }

    @Override
    public void run() {
        try {
            while (distance < MAX_DISTANCE) {
                Thread.sleep(friction);
                distance += 100;
                LOGGER.info(getName() + " " + distance);
            }
            latch.countDown();
            if (latch.getCount() == Runner.NUMBER_OF_CARS - 1) {
                winner = true;
            }
            LOGGER.debug(getName() + ": latch count: " + latch.getCount());
            latch.await();
            if (winner) {
                LOGGER.info("The winner is " + getName() + "!");
            }
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
    }

}
