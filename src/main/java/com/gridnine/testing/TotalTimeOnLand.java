package com.gridnine.testing;

import java.time.temporal.ChronoUnit;
import java.util.List;

public class TotalTimeOnLand implements Filter {
    @Override
    public boolean check(Flight flight) {
        List<Segment> segmentList = flight.getSegments();
        int minutes = 0;
        for (int i = 0; i < segmentList.size() - 1; i++) {
            minutes += ChronoUnit.MINUTES.between(
                    segmentList.get(i).getArrivalDate(),
                    segmentList.get(i + 1).getDepartureDate()
            );
        }
        return minutes <= 120;
    }
}
