
package core.models.SOLID;

import core.models.Passenger;

public class PassengerFullPhone implements PassengerPhoneFormat{

    @Override
    public String generateFullPhone(Passenger passenger) {
        return "+" + passenger.getCountryPhoneCode() + " " + passenger.getPhone();
    }
}
