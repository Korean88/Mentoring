package com.epam.mentoring.outtamemory.heap;

/**
 * Created by Andrey on 16.01.2016.
 */
public class ObjectWithReference {

    private ObjectWithReference reference;
    static int counter;

    public ObjectWithReference getReference() {
        return reference;
    }

    public void setReference(ObjectWithReference reference) {
        this.reference = reference;
    }

}
