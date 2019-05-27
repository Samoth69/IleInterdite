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
    BLEU("Bleu"),
    JAUNE("Jaune"),
    ORANGE("Orange"),
    ROUGE("Rouge"),
    VIOLET("Violet"),
    VERT("Vert");
    
    private String nom = "";
    
    TypeEnumCouleurPion(String nom){
        this.nom = nom;
    }
    
    @Override
    public String toString(){
        return nom;
    }
    
}
