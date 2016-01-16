package com.epam.mentoring.outtamemory.heap;

import java.util.Date;

/**
 * Created by Andrey Yun on 16.01.16.
 */
public class AnObject {

    private Date[] dates = {new Date(), new Date(), new Date(), new Date()};

    public Date[] getDates() {
        return dates;
    }

    public void setDates(Date[] dates) {
        this.dates = dates;
    }

}
