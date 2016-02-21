package com.epam.mentoring.concurrency.run;

import com.epam.mentoring.concurrency.action.QuickSortAction;
import com.epam.mentoring.concurrency.model.MyInteger;
import com.epam.mentoring.concurrency.model.MyIntegerListUtil;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

/**
 * Created by Andrey Yun on 20.02.2016.
 */
public class IntegerSorter {

    List<Integer> sortArray(Integer[] integers) {
        List<MyInteger> source = MyIntegerListUtil.createListOfMyIntegers(integers);
        List<MyInteger> destination = MyIntegerListUtil.createListOfMyIntegers(integers);
        QuickSortAction action = new QuickSortAction(source, destination, 0, source.size() - 1);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(action);
        List<MyInteger> myIntegersSorted = action.getDestination();
        List<Integer> result = myIntegersSorted.stream().map(i -> i.getValue()).collect(Collectors.toList());
        return result;
    }
}
