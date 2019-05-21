/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartes;

/**
 *
 * @author violentt
 */
public class CarteInondation {
    private String nom, image;
    private TypeEnumTresors tresor;
    
    public CarteInondation(String nom){
        setNom(nom);
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
    
    public void setTresor(TypeEnumTresors tresor){
        this.tresor = tresor;
    }
    
    public void setImage(String image){
        
    }
}
