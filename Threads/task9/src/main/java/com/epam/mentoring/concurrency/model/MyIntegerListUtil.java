package com.epam.mentoring.concurrency.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey Yun on 16.02.2016.
 */
public class MyIntegerListUtil {

    public static List<MyInteger> createListOfMyIntegers(Integer...ints) {
        List<MyInteger> result = new ArrayList<>();
        for (Integer i : ints) {
            MyInteger myInteger = new MyInteger(i);
            result.add(myInteger);
        }
        return result;
    }
}
