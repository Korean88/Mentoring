package com.epam.mentoring.outtamemory.stackoverflow;

import java.util.List;

/**
 * Created by Andrey on 16.01.2016.
 */
public class Shipper {

    private Shipper parent;
    private List<Shipper> children;

    public Shipper getParent() {
        return parent;
    }

    public void setParent(Shipper parent) {
        this.parent = parent;
    }

    public List<Shipper> getChildren() {
        return children;
    }

    public void setChildren(List<Shipper> children) {
        this.children = children;
    }
}
