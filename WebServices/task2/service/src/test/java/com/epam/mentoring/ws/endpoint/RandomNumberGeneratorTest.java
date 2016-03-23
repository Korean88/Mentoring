package com.epam.mentoring.ws.endpoint;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Andrey on 23.03.2016.
 */
public class RandomNumberGeneratorTest {

    @Test
    public void shouldGenerateRandomNumber() {
        RandomNumberGenerator generator = new RandomNumberGeneratorImpl();
        int lowerBound = 1;
        int upperBound = 100;
        int res = generator.generate(lowerBound, upperBound);
        System.out.println(res);
        Assert.assertTrue(res <= upperBound);
        Assert.assertTrue(res >= lowerBound);
    }
}
