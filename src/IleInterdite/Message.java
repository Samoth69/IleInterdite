/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import Cartes.CarteInondation;
import Enumerations.TypeEnumAction;
import Enumerations.TypeEnumMenuPrincipal;
import Enumerations.TypeEnumMessage;
import java.util.ArrayList;

/**
 *
 * @author violentt
 * @param <T>
 */
public class Message<T> {
    TypeEnumMessage type = TypeEnumMessage.NA;
    TypeEnumMenuPrincipal type2 = TypeEnumMenuPrincipal.NA;
    TypeEnumAction action = TypeEnumAction.NA;
    ArrayList<T> additionnal; //donnée additionnel au message (aucune règle de formatage, à traiter au cas par cas)
    String message;
    
    public Message(TypeEnumMessage type) {
        this.type = type;
        additionnal = null;
    }
    
    public Message(TypeEnumMessage type, String message){
        this.type = type;
        this.message = message;
    }
    
    public Message(TypeEnumMessage type, ArrayList<T> ad) {
        this.type = type;
        additionnal = ad;
    }
    
    public Message(TypeEnumMessage type, TypeEnumAction ac) {
        this.type = type;
        action = ac;
        additionnal = null;
    }   
    
    public Message(TypeEnumMessage type, TypeEnumAction ac, ArrayList<T> ad) {
        this.type = type;
        action = ac;
        additionnal = ad;
    } 
    
    public Message(TypeEnumMenuPrincipal type) {
        type2 = type;
    }
    
    public TypeEnumMessage getMessageType() {
        return type;
    }
    
    public TypeEnumMenuPrincipal getMessageType2() {
        return type2;
    }
    
    public TypeEnumAction getActionType() {
        return action;
    }
    
    public ArrayList<T> getAdditionnal() {
        return additionnal;
    }
    
    public void setAdditionnal(ArrayList<T> ad) {
        additionnal = ad;
    }
    
    public String getMessage(){
        return message;
    }
}
