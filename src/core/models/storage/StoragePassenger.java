package core.models.storage;

import core.models.Passenger;
import java.util.ArrayList;

public class StoragePassenger {

    private static StoragePassenger instance;
    ArrayList<Passenger> passengers;

    private StoragePassenger() {
        this.passengers = new ArrayList<>();
    }

    public static StoragePassenger getInstance() {
        if (instance == null) {
            instance = new StoragePassenger();
        }
        return instance;
    }

    public boolean addPassenger(Passenger passenger) {
        for (Passenger pass : this.passengers) {
            if (pass.getId() == passenger.getId()) {
                return false;
            }
        }
        this.passengers.add(passenger);
        return true;
    }

    public Passenger getPassenger(long id) {
        for (Passenger passenger : this.passengers) {
            if (passenger.getId() == id) {
                return passenger;
            }
        }
        return null;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }   
}
