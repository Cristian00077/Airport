
package core.models.persistance;

import core.models.Location;
import core.models.storage.StorageLocation;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonLocation {
    public static void readJsonLocations() {
        try {
            JSONArray arrayJson = JsonReader.load("core/json/locations.json");
            for (int i = 0; i < arrayJson.length(); i++) {
                JSONObject object = arrayJson.getJSONObject(i);

                Location location = new Location(
                        object.getString("airportId"),
                        object.getString("airportName"),
                        object.getString("airportCity"),
                        object.getString("airportCountry"),
                        object.getDouble("airportLatitude"),
                        object.getDouble("airportLongitude")
                );
                StorageLocation.getInstance().addLocation(location);
            }

        } catch (Exception ex) {
            System.err.println("Error reading ");
        }
    } 

}
