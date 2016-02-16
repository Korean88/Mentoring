package config;

import com.epam.concurrency.run.CommonResource;
import com.epam.concurrency.run.Consumer;
import com.epam.concurrency.run.Producer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by Andrey Yun on 13.02.2016.
 */

@Configuration
public class SpringConfig {

    public static final String CONSUMER_BEAN_NAME = "consumer";
    public static final String PRODUCER_BEAN_NAME = "producer";
    public static final String RESOURCE_BEAN_NAME = "resource";

    @Bean(name = RESOURCE_BEAN_NAME)
    public CommonResource createResource() {
        return new CommonResource();
    }

    @Bean(name = SpringConfig.CONSUMER_BEAN_NAME)
    @Scope(value = "prototype")
    public Consumer createConsumer() {
        Consumer consumer = new Consumer(createResource());
        return consumer;
    }

    @Bean(name = SpringConfig.PRODUCER_BEAN_NAME)
    @Scope(value = "prototype")
    public Producer createProducer() {
        Producer producer = new Producer(createResource());
        return producer;
    }
}
