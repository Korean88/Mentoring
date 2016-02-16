package com.epam.concurrency.run;

/**
 * Created by Andrey Yun on 15.02.2016.
 */
public abstract class ResourceUser implements Runnable {

    public ResourceUser(CommonResource resource) {
        this.resource = resource;
    }

    private final CommonResource resource;

    public synchronized CommonResource getResource() {
        return resource;
    }

    public void run() {
        while (true) {
            doAction();
        }
    }

    protected abstract void doAction();

}
