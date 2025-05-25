
package core.models.single;

import core.models.Passenger;

public interface PassengerOperation {
    abstract int calculateAge(Passenger passenger);
    
    abstract String generateFullPhone(Passenger passenger);
}
