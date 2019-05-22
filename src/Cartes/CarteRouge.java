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
   private String nom; //Montée des eaux, carte 
   private String description; //Dscription de la carte, si nécessaire
   private boolean estTiree;
   
   public CarteRouge(String nom, boolean estTiree ){     // instancation CarteRouge
        this.nom=nom;
        this.estTiree=estTiree;
    }
   
   
    public CarteRouge(String nom, boolean estTiree ,String description){     //instancation CarteRoug
        this.nom=nom;
        this.description=description;
        this.estTiree=estTiree;
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

    // return estTiree
    public boolean isEstTiree() {
        return estTiree;
    }

    // set estTiree
    public void setEstTiree(boolean estTiree) {
        this.estTiree = estTiree;
    }
    
    
}
