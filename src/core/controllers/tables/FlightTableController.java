/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.tables;

import core.models.Flight;
import core.models.Passenger;
import core.models.single.FlightCalArrivalDate;
import core.models.storage.StorageFlight;
import core.models.storage.StoragePassenger;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FlightTableController {
    public void showFlights(JTable table) {
        List<Flight> flights = StorageFlight.getInstance().getFlights();

        DefaultTableModel tm = new DefaultTableModel(
                new Object[]{"ID", "Departure Airport ID", "Arrival Airport ID", "Scale Airport ID", "Departure Date", "Arrival Date", "Plane ID", "Number Passengers"}, 0);

        for (Flight flight : flights) {
            tm.addRow(new Object[]{
                flight.getId(), flight.getDepartureLocation().getAirportId(), 
                flight.getArrivalLocation().getAirportId(),
                (flight.getScaleLocation() == null ? "-" : flight.getScaleLocation().getAirportId()),
                flight.getDepartureDate(), 
                FlightCalArrivalDate.calculateArrivalDate(flight), 
                flight.getPlane().getId(), 
                flight.getNumPassengers()});
        }
        
        table.setModel(tm);
    }
    
}
