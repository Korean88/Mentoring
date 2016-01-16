package com.epam.mentoring.outtamemory.heap;

import java.util.Date;

/**
 * Created by Andrey Yun on 16.01.16.
 */
public class AnObject {

    private final String BIG_TEXT = "Section 5 tells what the default initial and maximum heap sizes are. Those sizes may be fine for many\n" +
            "applications, but if your analysis of a performance problem (see Section 7) or of an \n" +
            "OutOfMemoryError\n" +
            "(discussed la\n" +
            "ter in this section) indica\n" +
            "tes a problem with the size of a particular generation or of the entire heap,\n" +
            "you can modify the sizes via command line options specified in Section 8. For example, the default maximum\n" +
            "heap size of 64MB on non–server–class machines is often too small, so you can specify a larger size via the \n" +
            "–Xmx\n" +
            "option. Unl\n" +
            "ess you ha\n" +
            "ve problems with long pause times, try granting as much memory as possible to the heap.\n" +
            "Throughput is proportional to the amount of memory available.";
    private Date[] dates = {new Date(), new Date(), new Date(), new Date()};

    public Date[] getDates() {
        return dates;
    }

    public void setDates(Date[] dates) {
        this.dates = dates;
    }

}
