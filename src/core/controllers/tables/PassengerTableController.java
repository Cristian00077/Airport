/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.tables;

import core.controllers.PassengerController;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Location;
import core.models.Passenger;
import core.models.single.PassengerCalAge;
import core.models.single.PassengerFullPhone;
import core.models.storage.StorageLocation;
import core.models.storage.StoragePassenger;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CRISTIAN
 */
public class PassengerTableController {
    public static Response updatePassengersTable(DefaultTableModel model) {
        try {
            model.setRowCount(0);
            ArrayList<Passenger> passengers = StoragePassenger.getInstance().getPassengers();

            if (passengers == null || passengers.isEmpty()) {
                return new Response("Passenger must be not empty", Status.NO_CONTENT);
            }

            for (Passenger p : passengers) {
                int age = PassengerCalAge.calculateAge(p);
                String fullPhone = PassengerFullPhone.generateFullPhone(p);
                model.addRow(new Object[]{
                    p.getId(),
                    p.getFullname(), 
                    p.getBirthDate(),
                    age,
                    fullPhone,
                    p.getCountry(),
                    p.getNumFlights() 
                });
            }

            return new Response("Passenger list updated successfully", Status.OK);
        } catch (Exception e) {
            return new Response("Unexpected error ", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public void showPassengers(JTable table) {
        List<Passenger> passengers = StoragePassenger.getInstance().getPassengers();

        DefaultTableModel tm = new DefaultTableModel(
                new Object[]{"id", "Name", "birthDate", "Age", "Phone", "Country", "Num Flight"}, 0);

        for (Passenger passenger : passengers) {
            tm.addRow(new Object[]{
                passenger.getId(), 
                passenger.getFullname(), 
                passenger.getBirthDate(), 
                PassengerCalAge.calculateAge(passenger), 
                PassengerFullPhone.generateFullPhone(passenger), 
                passenger.getCountry(), 
                passenger.getNumFlights()
            });
        }
        
        table.setModel(tm);
    }
}

