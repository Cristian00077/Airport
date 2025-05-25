package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Location;
import core.models.Plane;
import core.models.storage.StorageLocation;
import core.models.storage.StoragePlane;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JComboBox;

public class PlaneController {

    public static Response createPlane(String id, String brand, String model, String maxCapacity, String airline) {
        try {
            int maxCapacityInt;
            if (!isValidPlaneIdFormat(id)) {
                return new Response("Format is invalid", Status.BAD_REQUEST);
            }
            if (id.equals("")) {
                return new Response("Id must be not empty", Status.BAD_REQUEST);
            }

            if (brand.equals("")) {
                return new Response("Brand must be not empty", Status.BAD_REQUEST);
            }

            if (model.equals("")) {
                return new Response("Model must be not empty", Status.BAD_REQUEST);
            }

            try {
                maxCapacityInt = Integer.parseInt(maxCapacity);
                if (maxCapacityInt < 0) {
                    return new Response("Max capacity must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                if (maxCapacity.equals("")) {
                    return new Response("Max Capacity must be not empty", Status.BAD_REQUEST);
                }
                return new Response("Max capacity must be just numeric", Status.BAD_REQUEST);
            }

            if (airline.equals("")) {
                return new Response("Airline must be not empty", Status.BAD_REQUEST);
            }
            
            StoragePlane storagePlane = StoragePlane.getInstance();
            if(!storagePlane.addPlane(new Plane(id, brand, model, maxCapacityInt, airline))){
                return new Response("Airplane with that id already exits", Status.BAD_REQUEST);
            }
            return new Response("Airplane registered succesfully", Status.CREATED);
            
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.BAD_REQUEST);
        }
       
    }

    private static boolean isValidPlaneIdFormat(String id) {
        return id.matches("^[A-Z]{2}\\d{5}$");
    }
    
    /*public static void setPlaneIdComboBox(JComboBox<String> comboBox) {
        StoragePlane storage = StoragePlane.getInstance();
        for (Plane plane : storage.getPlanes()) {
            comboBox.addItem(String.valueOf(plane.getId()));
        }
    }*/
    
    public static void loadPlanesIdIntoComboBox(JComboBox<String> comboBox) {
        StoragePlane storage = StoragePlane.getInstance();
        for (Plane plane : storage.orderPlanes()) {
            comboBox.addItem(String.valueOf(plane.getId()));
        }
    }
}
