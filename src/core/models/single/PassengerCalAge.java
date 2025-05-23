
package core.models.single;

import core.models.Passenger;
import java.time.LocalDate;
import java.time.Period;

public class PassengerCalAge {
    private Passenger passenger;

    public PassengerCalAge(Passenger passenger) {
        this.passenger = passenger;
    }
    
    public static int calculateAge(Passenger passenger) {
        int age = Period.between(passenger.getBirthDate(), LocalDate.now()).getYears();
        return age;
    }
}
