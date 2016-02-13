package com.epam.concurrency.run;

import config.SpringConfig;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.*;

/**
 * Created by Andrey Yun on 13.02.2016.
 */
public class Runner {

    private static final Logger LOG = Logger.getLogger(Runner.class);

    private static final int NUMBER_OF_CONSUMERS_PRODUCERS = 9;

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        ExecutorService exex = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < NUMBER_OF_CONSUMERS_PRODUCERS; i++) {
                Consumer consumer = (Consumer) ctx.getBean(SpringConfig.CONSUMER_BEAN_NAME);
                Producer producer = (Producer) ctx.getBean(SpringConfig.PRODUCER_BEAN_NAME);
                exex.execute(consumer);
                exex.execute(producer);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            exex.shutdown();
        }
    }
}
