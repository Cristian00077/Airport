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
    ArrayList<EventListener> eventListeners;
    
    public Publisher(){
        eventListeners = new ArrayList<>();
    }
    
    public void Subscribe(EventListener sub){
        if(sub == null || this.eventListeners.contains(sub)){
            return;
        }
        this.eventListeners.add(sub);
    }
    public void Unsubscribe(EventListener sub){
        this.eventListeners.remove(sub);
    }
    
    public void NotifySubscribers(){
        for (EventListener sub : eventListeners) {
            sub.update(this);
        }
    } 
}