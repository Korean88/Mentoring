package com.epam.concurrency.run;

import org.apache.log4j.Logger;

/**
 * Created by Andrey Yun on 13.02.2016.
 */
public class Producer implements Runnable {

    private static final Logger LOG = Logger.getLogger(Producer.class);

    private CommonResource resource;
    private static int counter;
    private final int ID = ++counter;

    public CommonResource getResource() {
        return resource;
    }

    public void setResource(CommonResource resource) {
        this.resource = resource;
    }

    public void run() {
        while (true) {
            LOG.info("Producer " + Thread.currentThread().getName() + " after update: " +
                    resource.incrementCounterIfLessThanTen());
        }
    }
}
