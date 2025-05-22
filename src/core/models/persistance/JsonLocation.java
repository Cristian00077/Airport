
package core.models.persistance;

import core.models.Location;
import core.models.storage.StorageLocation;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonLocation implements Json<Location>{
    public void readJsonLocations(String path) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            JSONArray arrayJson = new JSONArray(content);

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
