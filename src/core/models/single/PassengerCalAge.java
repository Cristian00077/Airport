
package core.models.single;
import core.models.Passenger;
import java.time.LocalDate;
import java.time.Period;

public class PassengerCalAge implements PassengerAgeCalculator{
    private Passenger passenger;

    public PassengerCalAge(Passenger passenger) {
        this.passenger = passenger;
    }
    
    /*public static int calculateAge(Passenger passenger) {
        return Period.between(passenger.getBirthDate(), LocalDate.now()).getYears();
       
    }*/
    
    @Override
    public int calculateAge(Passenger passenger) {
        return Period.between(passenger.getBirthDate(), LocalDate.now()).getYears();
    }
}
