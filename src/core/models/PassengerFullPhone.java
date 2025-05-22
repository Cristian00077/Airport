
package core.models;

public class PassengerFullPhone {
    private Passenger passenger;

    public PassengerFullPhone(Passenger passenger) {
        this.passenger = passenger;
    }
    
    public String generateFullPhone(int countryCode, long phone){
        String fullPhone = "+" + countryCode + " " + phone;
        return fullPhone;
    }
}
