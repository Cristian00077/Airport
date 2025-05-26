
package core.models.SOLID;

import core.models.Flight;
import java.time.LocalDateTime;

public interface FlightArrivalCalculator {
      LocalDateTime calculateArrivalDate(Flight flight);
}
