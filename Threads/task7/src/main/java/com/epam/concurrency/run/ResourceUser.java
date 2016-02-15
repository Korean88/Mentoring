package com.epam.concurrency.run;

/**
 * Created by Andrey Yun on 15.02.2016.
 */
public abstract class ResourceUser implements Runnable {

    private CommonResource resource;

    public CommonResource getResource() {
        return resource;
    }

    public void setResource(CommonResource resource) {
        this.resource = resource;
    }

    public void run() {
        while (true) {
            doAction();
        }
    }

    protected abstract void doAction();

}
