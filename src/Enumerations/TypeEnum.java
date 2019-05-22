/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enumerations;

/**
 *
 * @author mariottp
 */
public enum TypeEnum {      //Enumeration pour les etats des tuiles
    SEC("SEC"),
    MOUILLE("MOUILLE"),
    INONDE("INONDE");
    
    private String etat = "";
    
    TypeEnum(String etat){      //constructeur
        this.etat = etat;
    }
    
    @Override
    public String toString(){
        return etat;
    }
}
