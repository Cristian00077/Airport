
package core.models.persistance;

import java.io.InputStream;
import org.json.JSONArray;
import org.json.JSONTokener;

public class JsonReader {
     public static JSONArray load(String path) {
       InputStream is = JsonReader.class.getClassLoader().getResourceAsStream(path);
       JSONTokener tokener = new JSONTokener(is);
     return new JSONArray(tokener);
    }
}
