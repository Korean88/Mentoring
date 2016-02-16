package com.epam.mentoring.concurrency.run;

import com.epam.mentoring.concurrency.action.QuickSortAction;
import com.epam.mentoring.concurrency.model.MyInteger;
import com.epam.mentoring.concurrency.model.MyIntegerListUtil;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by Andrey Yun on 14.02.2016.
 */
public class Runner {

    public static void main(String[] args) {
        List<MyInteger> source = MyIntegerListUtil.createListOfMyIntegers(
                9, 7, 5, 11, 12, 2, 14, 3, 10, 6, 4, 22, 44, 56, 66, 6, 1, 0);
        List<MyInteger> destination = MyIntegerListUtil.createListOfMyIntegers(
                9, 7, 5, 11, 12, 2, 14, 3, 10, 6, 4, 22, 44, 56, 66, 6, 1, 0);
        QuickSortAction action = new QuickSortAction(source, destination, 0, source.size() - 1);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(action);
        System.out.println(action.getDestination());
    }
}
