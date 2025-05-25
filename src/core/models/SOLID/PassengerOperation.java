
package core.models.SOLID;

import core.models.Passenger;

public interface PassengerOperation {
    abstract int calculateAge(Passenger passenger);
    
    abstract String generateFullPhone(Passenger passenger);
}
