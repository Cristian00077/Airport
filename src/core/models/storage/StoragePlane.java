
package core.models.storage;

import core.models.Location;
import core.models.Plane;
import java.util.ArrayList;
import java.util.List;

public class StoragePlane {
    
    private static StoragePlane instance;
    private ArrayList<Plane> planes;

    public StoragePlane() {
        this.planes = new ArrayList<>();
    }
    
    public static StoragePlane getInstance(){
        if(instance == null){
            instance = new StoragePlane();
        }
        return instance;
    }
    
    public boolean addPlane(Plane plane){
        for (Plane p : this.planes) {
            if(p.getId().equals(plane.getId())){
                return false;
            }
        }
        this.planes.add(plane);
        return true;
    }
    
    public Plane getPlane(String id){
        for (Plane plane : this.planes) {
            if(plane.getId().equals(id)){
                return plane;
            }
        }
        return null;
    }

    public ArrayList<Plane> getPlanes() {
        return planes;
    }
    
    public List<Plane> orderPlanes(){
                for (int i = 0; i < planes.size(); i++) {
            for (int j = 0; j < planes.size() - i - 1; j++) {
              Plane currentId = planes.get(j);
              Plane nextId = planes.get(j+1);
              if(currentId.getId().compareTo(nextId.getId()) > 0) {
                planes.set(j, nextId);
                planes.set(j+1, currentId);
              }
            }
        }
        return planes;
    }
   
}
