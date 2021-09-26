package com.gridnine.testing;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println(flights);
        FilterManager manager = new FilterManager(new DepartureBeforeNow());
        flights.stream().filter(manager::filter).forEach(System.out::println);
        manager.setFilter(new ArrivesBeforeDeparture());
        flights.stream().filter(manager::filter).forEach(System.out::println);
        manager.setFilter(new TotalTimeOnLand());
        flights.stream().filter(manager::filter).forEach(System.out::println);
    }
}
