package com.epam.concurrency.run;

import org.apache.log4j.Logger;

/**
 * Created by Andrey Yun on 13.02.2016.
 */
public class Consumer implements Runnable {

    private static final Logger LOG = Logger.getLogger(Consumer.class);

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
            int initialCounter = resource.getCounter();
            if (initialCounter >= 5) {
                LOG.info("Consumer " + ID + ": resource counter before dec: " + initialCounter);
                int eventualCounter = resource.decreaseCounter();
                LOG.info("Consumer " + ID + ": resource counter after dec: " + eventualCounter);
                if (initialCounter != eventualCounter + 1) {
                    LOG.warn("Consumer " + ID + " initialCounter=" + initialCounter + " BUT eventualCounter=" + eventualCounter);
                    System.exit(1);
                }
            } else {
                int intermediateCounter = resource.getCounter();
                LOG.info("Consumer " + ID + ": resource counter < 5: " + intermediateCounter);
                if (intermediateCounter != initialCounter) {
                    LOG.warn("Consumer " + ID + " initialCounter=" + initialCounter + " BUT intermediateCounter=" + intermediateCounter);
                    System.exit(1);
                }
            }
        }
    }
}
