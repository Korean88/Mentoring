package com.epam.concurrency.car;

import org.apache.log4j.Logger;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Andrey Yun on 13.02.2016.
 */

public class Car implements Runnable {

    private static final long MAX_DISTANCE = 10000;
    private static final int DISTANCE_INCREMENT = 100;
    private final CountDownLatch latch;
    private static final Logger LOGGER = Logger.getLogger(Car.class);
    public static final int TIMEOUT_SECONDS = 5;

    private long friction = 100;
    private long distance;
    private boolean winner;
    private boolean interrupted;

    private static int counter;
    private final int ID = ++counter;

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
            long startTime = System.currentTimeMillis();
            while (distance < MAX_DISTANCE && !interrupted) {
                long currentTime = System.currentTimeMillis();
                if ((currentTime - startTime) / 1000 >= TIMEOUT_SECONDS) {
                    interrupted = true;
                }
                Thread.sleep(friction);
                distance += DISTANCE_INCREMENT;
                LOGGER.info(getName() + " " + distance);
            }
            latch.countDown();
            if (latch.getCount() == Runner.NUMBER_OF_CARS - 1) {
                winner = true;
            }
            LOGGER.debug(getName() + ": latch count: " + latch.getCount());
            if (interrupted) {
                LOGGER.warn(getName() + " was disqualified due to a timeout of " + TIMEOUT_SECONDS + " seconds");
            }
            latch.await();
            if (winner) {
                LOGGER.info("The winner is " + getName() + "!");
            }
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        return getName();
    }
}
