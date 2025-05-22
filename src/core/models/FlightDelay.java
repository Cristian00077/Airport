
package core.models;

import java.time.LocalDateTime;

public class FlightDelay {
    private Flight flight;

    public FlightDelay(Flight flight) {
        this.flight = flight;
    }
    
    public void delay(Flight flight, int hours, int minutes) {
        flight.setDepartureDate(flight.getDepartureDate().plusHours(hours).plusMinutes(minutes));
    }
 
}
