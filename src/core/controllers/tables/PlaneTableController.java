
package core.controllers.tables;

import core.models.Plane;
import core.models.storage.StoragePlane;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PlaneTableController{
    public void showplanes(JTable table) {
        List<Plane> planes = StoragePlane.getInstance().getPlanes();

        DefaultTableModel tm = new DefaultTableModel(
                new Object[]{"ID", "Brand", "Model", "Max Capacity", "Airline", "Number Flights"}, 0);

        for (Plane plane : planes) {
            tm.addRow(new Object[]{
                plane.getId(), 
                plane.getBrand(), 
                plane.getModel(), plane.getMaxCapacity(),
                plane.getAirline(), 
                plane.getNumFlights()});
        }
        
        table.setModel(tm);
    }
}
