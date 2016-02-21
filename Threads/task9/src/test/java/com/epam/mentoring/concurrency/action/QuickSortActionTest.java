package com.epam.mentoring.concurrency.action;

import com.epam.mentoring.concurrency.model.MyInteger;
import com.epam.mentoring.concurrency.model.MyIntegerListUtil;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by Andrey Yun on 14.02.2016.
 */
public class QuickSortActionTest {

    @Test
    public void testSortingFirstIteration() {
        //GIVEN
        List<MyInteger> source = MyIntegerListUtil.createListOfMyIntegers(9, 7, 5, 11, 12, 2, 14, 3, 10, 6);
        List<MyInteger> destination = MyIntegerListUtil.createListOfMyIntegers(9, 7, 5, 11, 12, 2, 14, 3, 10, 6);
        MyInteger pivot = source.get(source.size() - 1);
        System.out.println("source: " + source);
        int start = 0;
        int end = source.size() - 1;
        QuickSortAction quickSortAction = new QuickSortAction(source, destination, start, end);
        //WHEN
        quickSortAction.rebasePivot(pivot);
        //THEN
        List<MyInteger> dest = quickSortAction.getDestination();
        assertThat(dest.size(), equalTo(source.size()));
        System.out.println("destination: " + dest);
        int pivotIndex = dest.indexOf(pivot);
        List<MyInteger> leftElements = Lists.newArrayList(dest.subList(start, pivotIndex - 1));
        List<MyInteger> rightElements = Lists.newArrayList(dest.subList(pivotIndex + 1, end));
        boolean leftElementsLessThanPivot = leftElements.stream().allMatch(i -> i.getValue() < pivot.getValue());
        assertThat(leftElementsLessThanPivot, is(true));
        boolean rightElementsGreaterThanPivot = rightElements.stream().allMatch(i -> i.getValue() > pivot.getValue());
        assertThat(rightElementsGreaterThanPivot, is(true));
    }

    @Test
    public void testSortingSecondIteration() {
        //GIVEN
        List<MyInteger> source = MyIntegerListUtil.createListOfMyIntegers(3, 2, 5);
        List<MyInteger> destination = MyIntegerListUtil.createListOfMyIntegers(3, 2, 5, 6, 9, 7, 11, 12, 14, 10);
        int origSize = destination.size();
        MyInteger pivot = source.get(2);
        System.out.println("source: " + source);
        int start = 0;
        int end = 2;
        QuickSortAction quickSortAction = new QuickSortAction(source, destination, start, end);
        //WHEN
        quickSortAction.rebasePivot(pivot);
        //THEN
        List<MyInteger> dest = quickSortAction.getDestination();
        assertThat(dest.size(), is(origSize));
        System.out.println("destination: " + dest);
        int pivotIndex = dest.indexOf(pivot);
        assertThat(pivotIndex, is(2));
        List<MyInteger> leftElements = Lists.newArrayList(dest.subList(start, pivotIndex));
        assertThat(leftElements.size(), is(2));
        boolean leftElementsLessThanPivot = leftElements.stream().allMatch(i -> i.getValue() < pivot.getValue());
        assertThat(leftElementsLessThanPivot, is(true));
    }

    @Test
    public void testSortingFirstIteration1() {
        //GIVEN
        List<MyInteger> source = MyIntegerListUtil.createListOfMyIntegers(12, 2, 14, 1, 0);
        List<MyInteger> destination = Lists.newArrayList(source);
        MyInteger pivot = source.get(source.size() - 1);
        System.out.println("source: " + source);
        int start = 0;
        int end = source.size() - 1;
        QuickSortAction quickSortAction = new QuickSortAction(source, destination, start, end);
        //WHEN
        quickSortAction.rebasePivot(pivot);
        //THEN
        List<MyInteger> dest = quickSortAction.getDestination();
        assertThat(dest.size(), equalTo(source.size()));
        System.out.println("destination: " + dest);
        assertThat(dest.toString(), equalTo("[0, 12, 2, 14, 1]"));
    }

    @Test
    public void testSortingSecondIteration1() {
        //GIVEN
        List<MyInteger> source = MyIntegerListUtil.createListOfMyIntegers(12, 2, 14, 1);
        List<MyInteger> destination = Lists.newArrayList(source);
        MyInteger pivot = source.get(source.size() - 1);
        System.out.println("source: " + source);
        int start = 0;
        int end = source.size() - 1;
        QuickSortAction quickSortAction = new QuickSortAction(source, destination, start, end);
        //WHEN
        quickSortAction.rebasePivot(pivot);
        //THEN
        List<MyInteger> dest = quickSortAction.getDestination();
        assertThat(dest.size(), equalTo(source.size()));
        System.out.println("destination: " + dest);
        assertThat(dest.toString(), equalTo("[1, 12, 2, 14]"));
    }

    @Test
    public void testSortingThirdIteration1() {
        //GIVEN
        List<MyInteger> source = MyIntegerListUtil.createListOfMyIntegers(1, 12, 2);
        List<MyInteger> destination = Lists.newArrayList(source);
        MyInteger pivot = source.get(source.size() - 1);
        System.out.println("source: " + source);
        int start = 0;
        int end = source.size() - 1;
        QuickSortAction quickSortAction = new QuickSortAction(source, destination, start, end);
        //WHEN
        quickSortAction.rebasePivot(pivot);
        //THEN
        List<MyInteger> dest = quickSortAction.getDestination();
        assertThat(dest.size(), equalTo(source.size()));
        System.out.println("destination: " + dest);
        assertThat(dest.toString(), equalTo("[1, 2, 12]"));
    }

    @Test
    public void testSortingForthIteration1() {
        //GIVEN
        List<MyInteger> source = MyIntegerListUtil.createListOfMyIntegers(1, 2);
        List<MyInteger> destination = Lists.newArrayList(source);
        MyInteger pivot = source.get(source.size() - 1);
        System.out.println("source: " + source);
        int start = 0;
        int end = source.size() - 1;
        QuickSortAction quickSortAction = new QuickSortAction(source, destination, start, end);
        //WHEN
        quickSortAction.rebasePivot(pivot);
        //THEN
        List<MyInteger> dest = quickSortAction.getDestination();
        assertThat(dest.size(), equalTo(source.size()));
        System.out.println("destination: " + dest);
        assertThat(dest.toString(), equalTo("[1, 2]"));
    }

}
