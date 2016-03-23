package com.epam.mentoring.ws.endpoint;

import org.apache.log4j.Logger;

import javax.jws.WebService;
import java.util.Random;

/**
 * Created by Andrey on 23.03.2016.
 */

@WebService(endpointInterface = "com.epam.mentoring.ws.endpoint.RandomNumberGenerator")
public class RandomNumberGeneratorImpl implements RandomNumberGenerator {

    private static final Logger LOG = Logger.getLogger(RandomNumberGeneratorImpl.class);

    @Override
    public int generate(int lowerBound, int upperBound) {
        Random random = new Random();
        int[] resArray = random.ints(lowerBound, upperBound)
                .limit(1)
                .toArray();
        int res = resArray[0];
        LOG.info("Number generated: " + res);
        return res;
    }
}
