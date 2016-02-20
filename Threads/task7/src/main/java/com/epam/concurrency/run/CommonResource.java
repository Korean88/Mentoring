package com.epam.concurrency.run;

import org.apache.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Andrey Yun on 13.02.2016.
 */
public class CommonResource {

    private static final Logger LOG = Logger.getLogger(CommonResource.class);

    private AtomicInteger counter = new AtomicInteger(10);

    public int getCounter() {
        return counter.get();
    }

    public int decrementCounterIfGreaterThanFive() {
        return counter.updateAndGet(i -> {
            LOG.info(Thread.currentThread().getName() + " in decrement, before update: " + i);
            return i > 5 ? --i : i;
        });
    }

    public int incrementCounterIfLessThanTen() {
        return counter.updateAndGet(i -> {
            LOG.info(Thread.currentThread().getName() + " in increment, before update: " + i);
            return i < 10 ? ++i : i;
        } );
    }
}
