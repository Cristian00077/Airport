
package core.models.SOLID;

import core.models.Flight;

public class FlightDelay implements FlightDelayService{
 
    @Override
    public void delay(Flight flight, int hours, int minutes) {
        flight.setDepartureDate(flight.getDepartureDate().plusHours(hours).plusMinutes(minutes));
    }
}
