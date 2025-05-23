/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.tables;

import core.models.Location;
import core.models.storage.StorageLocation;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CRISTIAN
 */
public class LocationTableController {
    public void showLocations(JTable table) {
        List<Location> locs = StorageLocation.getInstance().getLocations();

        DefaultTableModel tm = new DefaultTableModel(
                new Object[]{"airportId", "airportName", "airportCity", "airportCountry"}, 0);

        for (Location loc : locs) {
            tm.addRow(new Object[]{
                loc.getAirportId(),
                loc.getAirportName(),
                loc.getAirportCity(),
                loc.getAirportCountry()
            });
        }
        
        table.setModel(tm);
    }
}
