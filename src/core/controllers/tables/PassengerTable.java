/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.tables;

import core.controllers.PassengerController;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Passenger;
import core.models.single.PassengerCalAge;
import core.models.single.PassengerFullPhone;
import core.models.storage.StoragePassenger;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CRISTIAN
 */
public class PassengerTable {
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
}
