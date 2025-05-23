
package core.models.persistance;

import core.models.*;
import core.models.storage.StorageFlight;
import core.models.storage.StorageLocation;
import core.models.storage.StoragePlane;
import java.time.LocalDateTime;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonFlight {
    public static void readJsonFlights(){
        try {
            JSONArray arrayJson = JsonReader.load("core/json/flights.json");
            
            for (int i = 0; i < arrayJson.length(); i++) {
                JSONObject object = arrayJson.getJSONObject(i);
                
                String plane = object.getString("plane");
                String departureLoc = object.getString("departureLocation");
                String arriveLoc = object.getString("arrivalLocation");
                
                Plane planeAux = StoragePlane.getInstance().getPlane(plane);
                Location departureLocation = StorageLocation.getInstance().getLocation(departureLoc);
                Location arrivalLocation = StorageLocation.getInstance().getLocation(arriveLoc);
                
                Location scaleLocation = null;
                if (!object.isNull("scaleLocation")) { 
                    String scaleLoc = object.getString("scaleLocation");
                    scaleLocation = StorageLocation.getInstance().getLocation(scaleLoc);
                }
                Flight flight;
                if(scaleLocation == null){
                    flight = new Flight(
                            object.getString("id"),
                            planeAux,
                            departureLocation,
                            arrivalLocation,
                            LocalDateTime.parse(object.getString("departureDate")),
                            object.getInt("hoursDurationArrival"),
                            object.getInt("minutesDurationArrival")
                    );
                }else{
                    flight = new Flight(
                            object.getString("id"),
                            planeAux,
                            departureLocation,
                            scaleLocation,
                            arrivalLocation,
                            LocalDateTime.parse(object.getString("departureDate")),
                            object.getInt("hoursDurationArrival"),
                            object.getInt("minutesDurationArrival"),
                            object.getInt("hoursDurationScale"),
                            object.getInt("minutesDurationScale")
                    );
                }
                StorageFlight.getInstance().addFlight(flight);
            }
        } catch (Exception e) {
            System.out.println("Error reading flights");
        }
            
    }
}
