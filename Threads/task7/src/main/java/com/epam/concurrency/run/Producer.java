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
            int initialCounter = resource.getCounter();
            if (initialCounter < 5) {
                LOG.info("Producer " + ID + ": resource counter before inc: " + initialCounter);
                int eventualCounter = resource.increaseCounter();
                LOG.info("Producer " + ID + ": resource counter after inc: " + eventualCounter);
            } else {
                int intermediateCounter = resource.getCounter();
                LOG.info("Producer " + ID + ": resource counter >= 5: " + intermediateCounter);
                if (intermediateCounter != initialCounter) {
                    LOG.warn("Producer " + ID + " initialCounter=" + initialCounter + " BUT intermediateCounter=" + intermediateCounter);
                    System.exit(1);
                }
            }
        }
    }
}
