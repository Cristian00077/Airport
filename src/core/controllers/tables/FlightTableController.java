
package core.controllers.tables;

import core.controllers.FlightController;
import core.models.Flight;
import core.models.storage.StorageFlight;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FlightTableController {
    public void showFlights(JTable table) {
        try {
        List<Flight> flights = StorageFlight.getInstance().getFlights();

        DefaultTableModel tm = new DefaultTableModel(
                new Object[]{"ID", "Departure Airport ID", "Arrival Airport ID", "Scale Airport ID", "Departure Date", "Arrival Date", "Plane ID", "Number Passengers"}, 0);

        for (Flight flight : flights) {
            LocalDateTime arrivalDate = FlightController.getCalculatedArrivalDateForFlight(flight);
            tm.addRow(new Object[]{
                flight.getId(), 
                flight.getDepartureLocation().getAirportId(), 
                flight.getArrivalLocation().getAirportId(),
                (flight.getScaleLocation() == null ? "-" : flight.getScaleLocation().getAirportId()),
                flight.getDepartureDate(), 
                arrivalDate, 
                flight.getPlane().getId(), 
                flight.getNumPassengers()});
        }
        
        table.setModel(tm);
        } catch (Exception e) {
            System.out.println("ERRORRRR");
        }
    }
    
}
