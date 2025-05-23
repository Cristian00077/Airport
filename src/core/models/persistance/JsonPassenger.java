
package core.models.persistance;

import core.models.Passenger;
import core.models.storage.StoragePassenger;
import java.time.LocalDate;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonPassenger {
    public static void readJsonPassengers(){
        try {
            JSONArray arrayJson = JsonReader.load("core/json/passengers.json");
            for (int i = 0; i < arrayJson.length(); i++) {
                JSONObject object = arrayJson.getJSONObject(i);
                
                Passenger passenger = new Passenger(
                        object.getLong("id"),
                        object.getString("firstname"),
                        object.getString("lastname"),
                        LocalDate.parse(object.getString("birthDate")),
                        object.getInt("countryPhoneCode"),
                        object.getLong("phone"),
                        object.getString("country")
                );
                StoragePassenger.getInstance().addPassenger(passenger);
            }
        } catch (Exception e) {
            System.out.println("Error reading passengers");
        }
            
    }
}
