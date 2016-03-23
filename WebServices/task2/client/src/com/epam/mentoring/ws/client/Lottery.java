package com.epam.mentoring.ws.client;

import java.util.stream.IntStream;

/**
 * Created by Andrey on 23.03.2016.
 */
public class Lottery {

    public static void main(String[] args) {
        RandomNumberGeneratorImplService service = new RandomNumberGeneratorImplService();
        RandomNumberGenerator generator = service.getRandomNumberGeneratorImplPort();
        IntStream.range(1, 7).forEach(i -> System.out.println(i + " number: " + generator.generate(1, 49)));
    }
}
