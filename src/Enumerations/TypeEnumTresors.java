/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enumerations;

/**
 *
 * @author paul m
 */
public enum TypeEnumTresors {
    
    LUNE("Lune"),
    LION("Lion"),
    TROPHEE("Trophée"),
    FEU("Feu"),
    AUCUN("AUCUN");
    
    private String nom = "";
    
    TypeEnumTresors(String nom){
        this.nom = nom;
    }
    
   
    @Override
    public String toString(){
        return nom;
    }
}
