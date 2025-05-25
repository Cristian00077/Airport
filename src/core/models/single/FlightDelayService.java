
package core.models.single;

import core.models.Flight;

public interface FlightDelayService {
    void delay(Flight flight, int hours, int minutes);
}
