
package core.models.single;

import core.models.Flight;
import java.time.LocalDateTime;

public class FlightCalArrivalDate implements FlightArrivalCalculator{
    private Flight flight;

    public FlightCalArrivalDate(Flight flight) {
        this.flight = flight;
    }
    
    /*public static LocalDateTime calculateArrivalDate(Flight flight){
        return flight.getDepartureDate()
                .plusHours(flight.getHoursDurationScale() + flight.getHoursDurationArrival())
                .plusMinutes(flight.getMinutesDurationScale() + flight.getMinutesDurationArrival());
    }*/
    
    @Override
    public LocalDateTime calculateArrivalDate(Flight flight) {
        return flight.getDepartureDate()
            .plusHours(flight.getHoursDurationArrival() + flight.getHoursDurationScale())
            .plusMinutes(flight.getMinutesDurationArrival() + flight.getMinutesDurationScale());
    }
}
