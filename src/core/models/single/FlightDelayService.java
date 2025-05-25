
package core.models.single;

import core.models.Flight;
import java.time.LocalDateTime;

public interface FlightDelayService {
    void delay(Flight flight, int hours, int minutes);
}
