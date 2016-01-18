package com.epam.mentoring.outtamemory.heap;

/**
 * Created by Andrey on 16.01.2016.
 */
public class NoArrayOuttaMemoryHeap {

    public static void main(String[] args) throws InterruptedException {
        ObjectWithReference forever = new ObjectWithReference();
        ObjectWithReference pointer = forever;
        for (int i = 1;; i++) {
            System.out.println("iteration " + i);
            ObjectWithReference next = new ObjectWithReference();
            pointer.setReference(next);
            pointer = next;
            Thread.sleep(50L);
        }

    }
}
