package com.epam.mentoring.concurrency.run;

import org.junit.Test;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Andrey Yun on 20.02.2016.
 */
public class IntegerSorterTest {

    @Test
    public void shouldSortBigArray() {
        //GIVEN
        int size = 1000;
        Integer[] inputArray = new Integer[size];
        Random random = new Random();
        IntStream.range(0, size).forEach(i -> inputArray[i] = random.nextInt(500));
        IntegerSorter integerSorter = new IntegerSorter();
        //WHEN
        List<Integer> sorted = integerSorter.sortArray(inputArray);
        //THEN
        IntStream.range(1, size).forEach(i -> {
            Integer preceding = sorted.get(i - 1);
            assertThat(preceding <= sorted.get(i), is(Boolean.TRUE));
        });
    }
}
