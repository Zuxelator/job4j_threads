package com.gridnine.testing;

import java.util.List;

public class ArrivesBeforeDeparture implements Filter {
    @Override
    public boolean check(Flight flight) {
        boolean rsl = true;
        List<Segment> segmentList = flight.getSegments();
        for (Segment segment: segmentList) {
            if (segment.getArrivalDate().isBefore(segment.getDepartureDate())) {
                rsl = false;
                break;
            }
        }
        return rsl;
    }
}
