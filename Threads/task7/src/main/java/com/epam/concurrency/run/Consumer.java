package com.epam.concurrency.run;

import org.apache.log4j.Logger;

/**
 * Created by Andrey Yun on 13.02.2016.
 */
public class Consumer extends ResourceUser {

    private static final Logger LOG = Logger.getLogger(Consumer.class);

    @Override
    protected void doAction() {
        LOG.info("Consumer " + Thread.currentThread().getName() + " after update:" +
                getResource().decrementCounterIfGreaterThanFive());
    }
}
