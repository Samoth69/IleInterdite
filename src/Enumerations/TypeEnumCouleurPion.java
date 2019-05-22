/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enumerations;

/**
 *
 * @author Thomas
 */
public enum TypeEnumCouleurPion {
    AUCUN("AUCUN"),
    BLEU("BLEU"),
    JAUNE("JAUNE"),
    ORANGE("ORANGE"),
    ROUGE("ROUGE"),
    VIOLET("VIOLET"),
    VERT("VERT");
    
    private String nom = "";
    
    TypeEnumCouleurPion(String nom){
        this.nom = nom;
    }
    
    @Override
    public String toString(){
        return nom;
    }
    
}
