package com.epam.concurrency.run;

import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Andrey Yun on 13.02.2016.
 */
public class CommonResource {

    private static final Logger LOG = Logger.getLogger(CommonResource.class);

    private AtomicInteger counter = new AtomicInteger(10);
    private ReentrantLock lock = new ReentrantLock();

    public int getCounter() {
        try {
            lock.tryLock(2, TimeUnit.SECONDS);
            return counter.get();
        } catch (InterruptedException e) {
            LOG.error(e.getMessage(), e);
        }
        return -777;
    }
/*
    public void setCounter(AtomicInteger counter) {
        try {
            lock.tryLock(2, TimeUnit.SECONDS);
            this.counter = counter;
        } catch (InterruptedException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            lock.unlock();
        }
    }*/

    public int decreaseCounter() {
        try {
            lock.tryLock(2, TimeUnit.SECONDS);
            return counter.decrementAndGet();
        } catch (InterruptedException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            lock.unlock();
        }
        return -888;
    }

    public int increaseCounter() {
        try {
            lock.tryLock(2, TimeUnit.SECONDS);
        return counter.incrementAndGet();
        } catch (InterruptedException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            lock.unlock();
        }
        return -999;
    }
}
