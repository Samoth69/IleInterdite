/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import Enumerations.TypeEnumMessage;

/**
 *
 * @author violentt
 */
public class Message {
    TypeEnumMessage type;
    boolean pressed;
    
    public Message(TypeEnumMessage type) {
        this.type = type;
        pressed = false;
    }
    
    public void setPressed(boolean p) {
        this.pressed = p;
    }
    
    public boolean getPressed() {
        return this.pressed;
    }   
}
