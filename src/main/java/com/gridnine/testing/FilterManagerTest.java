package com.gridnine.testing;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class FilterManagerTest {

    @Test
    public void departureBeforeNowTest() {
        List<Flight> flights = FlightBuilder.createFlights();
        Flight flight = flights.get(0);
        assertTrue(new DepartureBeforeNow().check(flight));
    }

    @Test
    public void arrivesBeforeDepartureTest() {
        List<Flight> flights = FlightBuilder.createFlights();
        Flight flight = flights.get(3);
        assertFalse(new ArrivesBeforeDeparture().check(flight));
    }

    @Test
    public void totalTimeOnLandTest() {
        List<Flight> flights = FlightBuilder.createFlights();
        Flight flight = flights.get(5);
        assertFalse(new TotalTimeOnLand().check(flight));
    }
}