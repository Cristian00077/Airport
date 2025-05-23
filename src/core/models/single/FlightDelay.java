
package core.models.single;

import core.models.Flight;

public class FlightDelay {
    private Flight flight;

    public FlightDelay(Flight flight) {
        this.flight = flight;
    }
   
    public static void delay(Flight flight, int hours, int minutes) {
        flight.setDepartureDate(flight.getDepartureDate().plusHours(hours).plusMinutes(minutes));
    }
 
}
