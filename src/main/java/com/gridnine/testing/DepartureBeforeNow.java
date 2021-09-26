package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;

public class DepartureBeforeNow implements Filter {
    @Override
    public boolean check(Flight flight) {
        List<Segment> segmentList = flight.getSegments();
        return !segmentList.get(0).getDepartureDate().isBefore(LocalDateTime.now());
    }
}
