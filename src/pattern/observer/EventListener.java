/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pattern.observer;

/**
 *
 * @author Maldonado
 */
public interface EventListener {

    /**
     *Es el metodo de update de un evento
     * @param publisher
     */
    public void update(Publisher publisher);
}
