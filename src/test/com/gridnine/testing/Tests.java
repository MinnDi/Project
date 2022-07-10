package com.gridnine.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

// Downloaded JUnit locally, now through Maven or Gradle, didn't know if I have to do it
public class Tests {
    List<Flight> flights;
    FlightsFilter flightsFilter;
    LocalDateTime now = LocalDateTime.now();

    @Test
    public void testFilterByDepartureDate() {
        Flight oneSegDepDateBeforeNow = FlightBuilder.createFlight(now.minusDays(1).minusHours(3),
                now.minusDays(1).minusHours(2));
        Flight oneSegDepDateAfterNow = FlightBuilder.createFlight(now.plusHours(1), now.plusHours(4));
        Flight multiSegAllDepDateBeforeNow = FlightBuilder.createFlight(now.minusDays(1).minusHours(3), now.minusDays(1).minusHours(2),
                now.minusHours(16), now.minusHours(10),
                now.minusMinutes(30), now.plusHours(3));
        Flight multiSegNotAllDepDateBeforeNow = FlightBuilder.createFlight(now.minusHours(3), now.minusHours(2),
                now.minusHours(1), now.plusHours(4),
                now.plusHours(6), now.plusHours(10));
        Flight multiSegAllDepDateAfterNow = FlightBuilder.createFlight(now.plusHours(1), now.plusHours(2),
                now.plusHours(5), now.plusHours(10),
                now.plusDays(1), now.plusDays(1).plusHours(4));
        flights = Arrays.asList(
                oneSegDepDateBeforeNow,
                oneSegDepDateAfterNow,
                multiSegAllDepDateBeforeNow,
                multiSegNotAllDepDateBeforeNow,
                multiSegAllDepDateAfterNow
        );
        List<Flight> expectedFlights = Arrays.asList(oneSegDepDateAfterNow, multiSegAllDepDateAfterNow);
        flightsFilter = new FlightsFilter(flights);
        flights = flightsFilter.filterByDepartureDate(now).getFilteredFlights();
        Assertions.assertEquals(2, flights.size());
        Assertions.assertEquals(expectedFlights, flights);
    }

    @Test
    public void testFilterArrivalAfterDeparture(){
        Flight oneSegDepBeforeArr = FlightBuilder.createFlight(now.minusDays(1).minusHours(3),
                now.minusDays(1));
        Flight oneSegDepAfterArr = FlightBuilder.createFlight(now.plusHours(1), now.minusHours(4));
        Flight multiSegAllDepDateBeforeArr = FlightBuilder.createFlight(now.minusDays(1).minusHours(3), now.minusDays(1).minusHours(2),
                now.minusHours(16), now.minusHours(10),
                now.minusMinutes(30), now.plusHours(3));
        Flight multiSegNotAllDepDateBeforeArr = FlightBuilder.createFlight(now.minusHours(3), now.minusHours(2),
                now.minusHours(1), now.plusHours(4),
                now.plusHours(16), now.plusHours(10));
        Flight multiSegAllDepDateAfterArr = FlightBuilder.createFlight(now.plusHours(4), now.plusHours(2),
                now.plusHours(5), now.plusHours(1),
                now.plusDays(1), now.plusHours(4));
        flights = Arrays.asList(
                oneSegDepBeforeArr,
                oneSegDepAfterArr,
                multiSegAllDepDateBeforeArr,
                multiSegNotAllDepDateBeforeArr,
                multiSegAllDepDateAfterArr
        );
        List<Flight> expectedFlights = Arrays.asList(oneSegDepBeforeArr, multiSegAllDepDateBeforeArr);
        flightsFilter = new FlightsFilter(flights);
        flights = flightsFilter.filterArrivalAfterDeparture().getFilteredFlights();
        Assertions.assertEquals(2, flights.size());
        Assertions.assertEquals(expectedFlights, flights);
    }

    @Test
    public void testFilterHoursOnEarth(){
        Flight oneSegZeroHoursOnEarth = FlightBuilder.createFlight(now.minusDays(1).minusHours(3),
                now.minusDays(1));
        Flight multiSegZeroHoursOnEarth = FlightBuilder.createFlight(now.minusDays(1).minusHours(3), now.minusDays(1).minusHours(2),
                now.minusDays(1).minusHours(2), now.minusHours(10),
                now.minusHours(10), now.plusHours(13));
        Flight multiSegTwoHoursOnEarth = FlightBuilder.createFlight(now.minusHours(3), now.minusHours(2),
                now.minusHours(1), now.plusHours(4),
                now.plusHours(5), now.plusHours(10));
        Flight multiSegFourHoursOnEarth = FlightBuilder.createFlight(now.plusHours(4), now.plusHours(6),
                now.plusHours(7), now.plusHours(12),
                now.plusHours(15), now.plusHours(19));
        Flight multiSegEightHoursOnEarth = FlightBuilder.createFlight(now.plusHours(4), now.plusHours(6),
                now.plusHours(11), now.plusHours(12),
                now.plusHours(15), now.plusHours(19));
        flights = Arrays.asList(
                oneSegZeroHoursOnEarth,
                multiSegZeroHoursOnEarth,
                multiSegTwoHoursOnEarth,
                multiSegFourHoursOnEarth,
                multiSegEightHoursOnEarth
        );
        List<Flight> expectedFlights = Arrays.asList(oneSegZeroHoursOnEarth, multiSegZeroHoursOnEarth, multiSegTwoHoursOnEarth, multiSegFourHoursOnEarth);
        flightsFilter = new FlightsFilter(flights);
        flights = flightsFilter.filterHoursOnEarth(4).getFilteredFlights();
        Assertions.assertEquals(4, flights.size());
        Assertions.assertEquals(expectedFlights, flights);
    }

}
