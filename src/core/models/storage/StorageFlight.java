
package core.models.storage;

import core.models.Flight;
import java.util.ArrayList;

public class StorageFlight {
    private static StorageFlight instance;
    ArrayList<Flight> flights;

    private StorageFlight() {
        this.flights = new ArrayList<>();
    }
    
    public static StorageFlight getInstance(){
        if(instance == null){
            instance = new StorageFlight();
        }
        return instance;
    }
    
    public boolean addFlight(Flight flight){
        for (Flight f : this.flights) {
            if(f.getId().equals(flight.getId())){
                return false;
            }
        }
        this.flights.add(flight);
        return true;
    }
    public Flight getFlight(String id){
        for (Flight flight : this.flights) {
            if(flight.getId().equals(id)){
                return flight;
            }
        }
        return null;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }
    
    
}
