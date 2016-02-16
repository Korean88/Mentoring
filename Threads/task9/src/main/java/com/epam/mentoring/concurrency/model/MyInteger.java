package com.epam.mentoring.concurrency.model;

import java.util.UUID;

/**
 * Created by Andrey Yun on 16.02.2016.
 */
public class MyInteger {

    private Integer value;
    private final String ID = UUID.randomUUID().toString();

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public MyInteger(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyInteger)) return false;

        MyInteger myInteger = (MyInteger) o;

        if (value != null ? !value.equals(myInteger.value) : myInteger.value != null) return false;
        return ID != null ? ID.equals(myInteger.ID) : myInteger.ID == null;

    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (ID != null ? ID.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
