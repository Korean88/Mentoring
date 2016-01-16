package com.epam.mentoring.outtamemory.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey Yun on 16.01.16.
 */
public class ArrayOuttaMemoryHeap {

    public static void main(String[] args) {
        List<AnObject> objects = new ArrayList<>();
        for (int i = 0;; i++) {
            AnObject anObject = new AnObject();
            objects.add(anObject);
            System.out.println("A new Object was added to an array, iteration " + i + 1);
        }
    }
}
