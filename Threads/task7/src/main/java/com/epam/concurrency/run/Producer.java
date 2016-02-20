package com.epam.concurrency.run;

import org.apache.log4j.Logger;

/**
 * Created by Andrey Yun on 13.02.2016.
 */
public class Producer extends ResourceUser {

    private static final Logger LOG = Logger.getLogger(Producer.class);

    public Producer(CommonResource resource) {
        super(resource);
    }

    @Override
    protected void doAction() {
        LOG.info("Producer " + Thread.currentThread().getName() + " after update: " +
                getResource().incrementCounterIfLessThanTen());
    }
}
