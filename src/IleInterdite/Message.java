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
import Enumerations.TypeEnumTresors;
import java.util.ArrayList;

/**
 *
 * @author violentt
 * @param <T>
 */
public class Message<T> {

    //ATTRIBUTS
    TypeEnumMessage type = TypeEnumMessage.NA;
    TypeEnumMenuPrincipal type2 = TypeEnumMenuPrincipal.NA;
    TypeEnumAction action = TypeEnumAction.NA;
    ArrayList<T> additionnal; //donnée additionnel au message (aucune règle de formatage, à traiter au cas par cas)
    String message;
    Tuile emplacementJoueur;
    
    //CONSTRUCTEUR1
    public Message(TypeEnumMessage type) {
        this.type = type;
        additionnal = null;
    }
    
    //CONSTRUCTEUR2
    public Message(TypeEnumMessage type, String message) {
        this.type = type;
        this.message = message;
    }
    
    //CONSTRUCTEUR3
    public Message(TypeEnumMessage type, ArrayList<T> ad) {
        this.type = type;
        additionnal = ad;
    }
    
    //CONSTRUCTEUR4
    public Message(TypeEnumMessage type, TypeEnumAction ac) {
        this.type = type;
        action = ac;
        additionnal = null;
    }
    
    //CONSTRUCTEUR5
    public Message(TypeEnumMessage type, TypeEnumAction ac, ArrayList<T> ad) {
        this.type = type;
        action = ac;
        additionnal = ad;
    }
    
    //CONSTRUCTEUR6
    public Message(TypeEnumMessage type, Tuile emplacementJoueur) {
        this.type = type;
        this.emplacementJoueur = emplacementJoueur;
    }
    
    //CONSTRUCTEUR7
    public Message(TypeEnumMenuPrincipal type) {
        type2 = type;
    }
    
    //METHODES

    //retourne le type du message
    public TypeEnumMessage getMessageType() {
        return type;
    }
    
    //retourne le typeenum pour le menu principal
    public TypeEnumMenuPrincipal getMessageType2() {
        return type2;
    }
    
//retourne le type action
    public TypeEnumAction getActionType() {
        return action;
    }
    
    //retourne ce qui est additionnel(voir attribut)
    public ArrayList<T> getAdditionnal() {
        return additionnal;
    }

    //setter de l'additionnel (voir attribut)
    public void setAdditionnal(ArrayList<T> ad) {
        additionnal = ad;
    }
    
    //retourne le message
    public String getMessage() {
        return message;
    }
    
    //retourne le tuile de l'emplacement du joueur (tuile utilisé dans un des contructeurs)
    public Tuile getEmplacementJoueur() {
        return emplacementJoueur;
    }
}
