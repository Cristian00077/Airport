
package core.models.persistance;

import core.models.Plane;
import core.models.storage.StoragePlane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonPlane {
    public void readJsonPlanes(String path){
        try {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            JSONArray arrayJson = new JSONArray(content);
            
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
