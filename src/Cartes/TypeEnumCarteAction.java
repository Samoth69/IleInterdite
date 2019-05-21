/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartes;

/**
 *
 * @author paul m
 */
public enum TypeEnumCarteAction {
    HELICOPTERE("HELICOPTERE"),
    SAC_DE_SABLE("SAC_DE_SABLE");
    
    private String nom = "";
    
    TypeEnumCarteAction(String nom){
        this.nom = nom;
    }
    
    @Override
    public String toString(){
        return nom;
    }
}
