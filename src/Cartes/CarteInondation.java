/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartes;

import Enumerations.TypeEnumCouleurPion;
import Enumerations.TypeEnumTresors;

/**
 *
 * @author violentt
 */
public class CarteInondation {
    private String nom, image;
    private TypeEnumTresors tresor;
    private TypeEnumCouleurPion couleurPion = TypeEnumCouleurPion.AUCUN;
    
    public CarteInondation(String nom){
        setNom(nom);
    }
    
    public CarteInondation(String nom, TypeEnumCouleurPion couleurPion){
        setNom(nom);
        this.couleurPion = couleurPion;
    }
    
    public CarteInondation(String nom, TypeEnumTresors tresor){
        setNom(nom);
        setTresor(tresor);
    }
    
    public CarteInondation(String nom, TypeEnumTresors tresor, String image){
        setNom(nom);
        setTresor(tresor);
    }
    
    public void setNom(String nom){
        this.nom = nom;
    }
    
    public String getNom(){
        return nom;
    }
    
    public TypeEnumCouleurPion getCouleurPion() {
        return couleurPion;
    }
    
    public void setTresor(TypeEnumTresors tresor){
        this.tresor = tresor;
    }
    
    public void setImage(String image){
        
    }
}
