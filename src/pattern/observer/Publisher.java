/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pattern.observer;

import java.util.ArrayList;

/**
 *
 * @author Maldonado
 */
public abstract class Publisher {
    ArrayList<Subscriber> subs;
    
    public Publisher(){
        subs = new ArrayList<>();
    }
    
    public void Subscribe(Subscriber sub){
        if(sub == null || this.subs.contains(sub)){
            return;
        }
        this.subs.add(sub);
    }
    public void Unsubscribe(Subscriber sub){
        this.subs.remove(sub);
    }
    
    protected void NotifySubscribers(){
        for (Subscriber sub : subs) {
            sub.update(this);
        }
    } 
}
