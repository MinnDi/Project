package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        LocalDateTime now = LocalDateTime.now();

        System.out.println(new FlightsFilter(flights)
                .getFilteredFlights());

        System.out.println(new FlightsFilter(flights)
                .filterArrivalAfterDeparture()
                .getFilteredFlights());

        System.out.println(new FlightsFilter(flights)
                .filterByDepartureDate(now)
                .getFilteredFlights());

        System.out.println(new FlightsFilter(flights)
                .filterHoursOnEarth(2)
                .getFilteredFlights());

        System.out.println(new FlightsFilter(flights)
                .filterArrivalAfterDeparture()
                .filterByDepartureDate(now)
                .filterHoursOnEarth(2)
                .getFilteredFlights());
    }
}
