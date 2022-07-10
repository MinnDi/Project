package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class FlightsFilter {
    private List<Flight> flights;

    public FlightsFilter(List<Flight> flights) {
        this.flights = flights;
    }

    public FlightsFilter filterByDepartureDate(LocalDateTime now) {
        this.flights = this.flights.stream()
                .filter(flight -> flight.getSegments()
                        .stream()
                        .allMatch(segment -> segment.getDepartureDate().isAfter(now)))
                .collect(Collectors.toList());
        return this;
    }

    public FlightsFilter filterArrivalAfterDeparture() {
        this.flights = this.flights.stream()
                .filter(flight -> flight.getSegments()
                        .stream()
                        .allMatch(segment -> segment.getDepartureDate().isBefore(segment.getArrivalDate())))
                .collect(Collectors.toList());
        return this;
    }

    public FlightsFilter filterHoursOnEarth(int hours) {
        this.flights = this.flights.stream()
                .filter(flight -> this.getHoursOnEarth(flight.getSegments()) <= hours).collect(Collectors.toList());
        return this;
    }

    public List<Flight> getFilteredFlights() {
        return this.flights;
    }

    private int getHoursOnEarth(List<Segment> segments) {
        int hours = 0;
        for (int i = 0; i < segments.size() - 1; i++) {
            hours += ChronoUnit.HOURS.between(segments.get(i).getArrivalDate(), segments.get(i+1).getDepartureDate());
        }
        return hours;
    }
}
