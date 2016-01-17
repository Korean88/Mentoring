package com.epam.mentoring.outtamemory.heap;

/**
 * Created by Andrey on 16.01.2016.
 */
public class NoArrayOuttaMemoryHeap {

    public static void main(String[] args) throws InterruptedException {
        ObjectWithReference o1 = new ObjectWithReference();
        createRef(o1);

    }

    private static void createRef(ObjectWithReference o1) throws InterruptedException {
        ObjectWithReference o2 = new ObjectWithReference();
        o1.setReference(o2);
        ObjectWithReference.counter++;
        if (ObjectWithReference.counter < 999_999) {
            if (ObjectWithReference.counter % 100 == 0) {
                Thread.sleep(1L);
            }
            System.out.println("iteration " + ObjectWithReference.counter);
            createRef(o2);
        }
    }
}
