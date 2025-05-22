
package core.models;

import java.time.LocalDateTime;

public class FlightCalArrivalDate {
    private Flight flight;

    public FlightCalArrivalDate(Flight flight) {
        this.flight = flight;
    }
    
    public LocalDateTime calculateArrivalDate(Flight flight){
        return flight.getDepartureDate()
                .plusHours(flight.getHoursDurationScale() + flight.getHoursDurationArrival())
                .plusMinutes(flight.getMinutesDurationScale() + flight.getMinutesDurationArrival());
    }
}
