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
public abstract class CarteRouge {
   private String nom; //Montée des eaux ou carte tresor ou carte action
   private String description; //Description de la carte, si nécessaire
   
   public CarteRouge(String nom){     // instancation CarteRouge
        this.nom=nom;
    }
   
   
    public CarteRouge(String nom, String description){     //instancation CarteRoug
        this.nom=nom;
        this.description=description;
    }

    
     // return the nom   
    public String getNom() {
        return nom;
    }

    // set nom
    public void setNom(String nom) {
        this.nom = nom;
    }

    // return description
    public String getDescription() {
        return description;
    }

    // set description
    public void setDescription(String description) {
        this.description = description;
    }
    
    public abstract String getTypeTresor();
    
}
