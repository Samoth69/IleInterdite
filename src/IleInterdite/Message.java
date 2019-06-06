/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import Enumerations.TypeEnumAction;
import Enumerations.TypeEnumMenuPrincipal;
import Enumerations.TypeEnumMessage;
import java.util.ArrayList;

/**
 *
 * @author violentt
 */
public class Message {
    TypeEnumMessage type = TypeEnumMessage.NA;
    TypeEnumMenuPrincipal type2 = TypeEnumMenuPrincipal.NA;
    TypeEnumAction action = TypeEnumAction.NA;
    ArrayList<String> additionnal; //donnée additionnel au message (aucune règle de formatage, à traiter au cas par cas)
    
    public Message(TypeEnumMessage type) {
        this.type = type;
        additionnal = null;
    }
    
    public Message(TypeEnumMessage type, ArrayList<String> ad) {
        this.type = type;
        additionnal = ad;
    }
    
    public Message(TypeEnumMessage type, TypeEnumAction ac) {
        this.type = type;
        action = ac;
        additionnal = null;
    }   
    
    public Message(TypeEnumMessage type, TypeEnumAction ac, ArrayList<String> ad) {
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
    
    public ArrayList<String> getAdditionnal() {
        return additionnal;
    }
    
    public void setAdditionnal(ArrayList<String> ad) {
        additionnal = ad;
    }
}
