
package core.models;

import java.time.LocalDate;
import java.time.Period;

public class PassengerCalAge {
    private Passenger passenger;

    public PassengerCalAge(Passenger passenger) {
        this.passenger = passenger;
    }
    
    public static int calculateAge(LocalDate birthDate) {
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        return age;
    }
}
