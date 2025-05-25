
package core.models.single;

import core.models.Flight;

public class FlightDelay implements FlightDelayService{
    private Flight flight;

    public FlightDelay(Flight flight) {
        this.flight = flight;
    }

 
    @Override
    public void delay(Flight flight, int hours, int minutes) {
        flight.setDepartureDate(flight.getDepartureDate().plusHours(hours).plusMinutes(minutes));
    }
}
