package com.epam.mentoring.concurrency.action;

import com.epam.mentoring.concurrency.model.MyInteger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.stream.IntStream;

/**
 * Created by Andrey Yun on 14.02.2016.
 */
public class QuickSortAction extends RecursiveAction {

    private final List<MyInteger> source;
    private final List<MyInteger> destination;
    private final int start;
    private final int end;

    public QuickSortAction(List<MyInteger> source, List<MyInteger> destination, int start, int end) {
        this.source = source;
        this.destination = destination;
        this.start = start;
        this.end = end;
    }

    public List<MyInteger> getDestination() {
        return destination;
    }

    @Override
    protected void compute() {
        MyInteger pivot = source.get(source.size() - 1);
        rebasePivot(pivot);
        int newPivotIdx = destination.indexOf(pivot);
        List<MyInteger> leftList = new ArrayList<>();
        List<MyInteger> rightList = new ArrayList<>();
        if (newPivotIdx > 0) {
            leftList = destination.subList(start, newPivotIdx);
        }
        if (newPivotIdx < end) {
            rightList = destination.subList(newPivotIdx + 1, end + 1);
        }
        if (leftList.size() > 1 && rightList.size() > 1) {
            invokeAll(new QuickSortAction(leftList, destination, start, newPivotIdx - 1),
                    new QuickSortAction(rightList, destination, newPivotIdx + 1, end));
        } else if (leftList.size() > 1) {
            invokeAll(new QuickSortAction(leftList, destination, start, newPivotIdx - 1));
        } else if (rightList.size() > 1) {
            invokeAll(new QuickSortAction(rightList, destination, newPivotIdx + 1, end));
        }
    }

    void rebasePivot(MyInteger pivot) {
        LinkedList<MyInteger> tempList = new LinkedList<>();
        tempList.add(pivot);
        source.stream().forEach(i -> {
            if (i.getValue().compareTo(pivot.getValue()) <= 0 && i != pivot) {
                tempList.addFirst(i);
            } else if (i.getValue().compareTo(pivot.getValue()) > 0) {
                tempList.addLast(i);
            }
        });
        Iterator<MyInteger> iterator = tempList.iterator();
        IntStream.range(start, end + 1).forEach(i -> {
            MyInteger integer = iterator.next();
            destination.set(i, integer);
        });
    }
}
