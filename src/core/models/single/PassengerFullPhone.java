
package core.models.single;

import core.models.Passenger;

public class PassengerFullPhone implements PassengerPhoneFormat{
    private Passenger passenger;

    public PassengerFullPhone(Passenger passenger) {
        this.passenger = passenger;
    }
    
    /*public static String generateFullPhone(Passenger pass){
        return "+" + pass.getCountryPhoneCode() + " " + pass.getPhone();
        
    }*/
    
    @Override
    public String generateFullPhone(Passenger passenger) {
        return "+" + passenger.getCountryPhoneCode() + " " + passenger.getPhone();
    }
}
