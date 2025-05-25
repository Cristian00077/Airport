
package core.models.SOLID;

import core.models.Passenger;

public class PassengerFullPhone implements PassengerPhoneFormat{
    
    /*public static String generateFullPhone(Passenger pass){
        return "+" + pass.getCountryPhoneCode() + " " + pass.getPhone();
        
    }*/
    
    @Override
    public String generateFullPhone(Passenger passenger) {
        return "+" + passenger.getCountryPhoneCode() + " " + passenger.getPhone();
    }
}
