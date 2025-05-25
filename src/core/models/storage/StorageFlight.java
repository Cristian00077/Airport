
package core.models.storage;

import core.models.Flight;
import core.models.Location;
import core.models.Plane;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
    
    public List<Location> getAllDepartureLocations() {
        List<Location> departureLocations = new ArrayList<>();
        for (Flight flight : this.flights) {
            departureLocations.add(flight.getDepartureLocation());
        }
        return departureLocations;
    }
    
    public List<Location> getAllArrivalLocations() {
        List<Location> arrivalLocations = new ArrayList<>();
        for (Flight flight : this.flights) {
            arrivalLocations.add(flight.getArrivalLocation());
        }
        return arrivalLocations;
    }

    public List<Location> getAllScaleLocations() {
        List<Location> scaleLocation = new ArrayList<>();
        for (Flight flight : this.flights) {
            scaleLocation.add(flight.getScaleLocation());
        }
        return scaleLocation;
    }
    
    public List<Flight> orderFlights(){
        flights.sort(Comparator.comparing(Flight::getDepartureDate));
        return flights;
    }
}
