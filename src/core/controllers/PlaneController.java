package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Plane;
import core.models.storage.StoragePlane;
import javax.swing.JComboBox;
import pattern.observer.EventListener;
import pattern.observer.GenericPublisher;


public class PlaneController {

    private static final GenericPublisher publisher = new GenericPublisher();
    
    public static void subscribe(EventListener s) {
        publisher.Subscribe(s);
    }

    public static void notifyChanges() {
         
        publisher.NotifySubscribers();
    }
    
    public static void unsubscribe(EventListener s){
        publisher.Unsubscribe(s);
    }
    
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
            notifyChanges();
            return new Response("Airplane registered succesfully", Status.CREATED);
            
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.BAD_REQUEST);
        }
       
    }

    private static boolean isValidPlaneIdFormat(String id) {
        return id.matches("^[A-Z]{2}\\d{5}$");
    }
    
    public static void loadPlanesIdIntoComboBox(JComboBox<String> comboBox) {
        StoragePlane storage = StoragePlane.getInstance();
        for (Plane plane : storage.orderPlanes()) {
            comboBox.addItem(String.valueOf(plane.getId()));
        }
    }
}
