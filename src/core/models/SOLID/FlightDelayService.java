
package core.models.SOLID;

import core.models.Flight;

public interface FlightDelayService {
    void delay(Flight flight, int hours, int minutes);
}
