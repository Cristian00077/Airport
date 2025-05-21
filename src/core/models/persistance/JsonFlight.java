
package core.models.persistance;

import core.models.Flight;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonFlight {
    public void readJsonFlights(String path){
        try {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            JSONArray arrayJson = new JSONArray(content);
            
            for (int i = 0; i < arrayJson.length(); i++) {
                JSONObject object = arrayJson.getJSONObject(i);
                
            }
            
        } catch (Exception ex) {
            System.out.println("Error reading");
        }
    }
}
