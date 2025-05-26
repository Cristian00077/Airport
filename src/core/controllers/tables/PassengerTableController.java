
package core.controllers.tables;

import core.controllers.FlightController;
import core.controllers.PassengerController;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.Passenger;
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
    
    public static Response ShowUserFlights(String passengerId, JTable table) {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "Departure Date", "Arrival Date"}, 0);
        try {
            
            long idLong;
                
                try {
                    idLong = Long.parseLong(passengerId);
            } catch (NumberFormatException e) {
                return new Response("Id must be selected in the combobox", Status.BAD_REQUEST);
            }
  
            StoragePassenger storagePassanger = StoragePassenger.getInstance();
            Passenger passenger = storagePassanger.getPassenger(idLong);
            
            List<Passenger> passengers = StoragePassenger.getInstance().getPassengers();
            Passenger pass = null;
            for (Passenger p : passengers) {
                if (p.getId() == idLong) {
                    pass = p;
                    break;
                }
            }
        
            ArrayList<Flight> flights = passenger.getFlights();
            for (Flight flight : flights) {
                LocalDateTime arrivalCal = FlightController.getCalculatedArrivalDateForFlight(flight);
                model.addRow(new Object[]{
                    flight.getId(), 
                    flight.getDepartureDate(),
                    arrivalCal});
            }
            table.setModel(model);
            return new Response("User flights shown successfully",Status.OK);
            } catch (Exception e) {
                if (passengerId == null || passengerId.isEmpty()) {
                return new Response("You must select a user in the combobox", Status.BAD_REQUEST);
                }
                return new Response("Unexpected error",Status.INTERNAL_SERVER_ERROR);
            }
    }
        
    
}

