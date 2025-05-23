
package core.models.persistance;

import core.models.Plane;
import core.models.storage.StoragePlane;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonPlane {
    public static void readJsonPlanes(){
        try {
            JSONArray arrayJson = JsonReader.load("core/json/planes.json");
            for (int i = 0; i < arrayJson.length(); i++) {
                JSONObject object = arrayJson.getJSONObject(i);
                
                Plane plane = new Plane(
                        object.getString("id"),
                        object.getString("brand"),
                        object.getString("model"),
                        object.getInt("maxCapacity"),
                        object.getString("airline")
                );
                StoragePlane.getInstance().addPlane(plane);
            }
            
        } catch (Exception ex) {
            System.out.println("Error reading");
        }
    }

}
