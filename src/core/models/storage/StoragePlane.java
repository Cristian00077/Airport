
package core.models.storage;

import core.models.Plane;
import java.util.ArrayList;

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
    
}
