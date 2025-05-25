
package core.controllers.tables;

import core.controllers.FlightController;
import core.controllers.PassengerController;
import core.models.Flight;
import core.models.Passenger;
import core.models.single.FlightCalArrivalDate;
import core.models.single.PassengerCalAge;
import core.models.single.PassengerFullPhone;
import core.models.storage.StoragePassenger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PassengerTableController {
    
    public void showPassengers(JTable table) {
        List<Passenger> passengers = StoragePassenger.getInstance().getPassengers();

        DefaultTableModel tm = new DefaultTableModel(
                new Object[]{"id", "Name", "birthDate", "Age", "Phone", "Country", "Num Flight"}, 0);

        for (Passenger passenger : passengers) {
            int agePass = PassengerController.ageCalculated(passenger);
            String fullPhone = PassengerController.fullPhoneFormat(passenger);
            tm.addRow(new Object[]{
                passenger.getId(), 
                passenger.getFullname(), 
                passenger.getBirthDate(), 
                agePass, 
                fullPhone, 
                passenger.getCountry(), 
                passenger.getNumFlights()
            });
        }
        
        table.setModel(tm);
    }
    
    public void ShowUserFlights(String passengerId, JTable table) {
        long idLong;
            idLong = Long.parseLong(passengerId);
  
        StoragePassenger storagePassanger = StoragePassenger.getInstance();
        Passenger passenger = storagePassanger.getPassenger(idLong);
        
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "Departure Date", "Arrival Date"}, 0);
        
        ArrayList<Flight> flights = passenger.getFlights();
        for (Flight flight : flights) {
            LocalDateTime arrivalCal = FlightController.getCalculatedArrivalDateForFlight(flight);
            model.addRow(new Object[]{flight.getId(), 
                flight.getDepartureDate(),
                arrivalCal});
        }

        table.setModel(model);
    }
    
}

