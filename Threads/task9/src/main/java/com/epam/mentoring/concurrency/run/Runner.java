package com.epam.mentoring.concurrency.run;

import java.util.List;

/**
 * Created by Andrey Yun on 14.02.2016.
 */
public class Runner {

    public static void main(String[] args) {
        Integer[] initialIntegers = new Integer[]{9, 7, 5, 11, 12, 2, 14, 3, 10, 6, 4, 22, 44, 56, 66, 6, 1, 0};
        IntegerSorter integerSorter = new IntegerSorter();
        List<Integer> sorted = integerSorter.sortArray(initialIntegers);
        System.out.println(sorted);
    }
}
