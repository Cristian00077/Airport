
package core.models.single;

import core.models.Flight;
import java.time.LocalDateTime;

public interface FlightArrivalCalculator {
      LocalDateTime calculateArrivalDate(Flight flight);

}
