/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartes;

import Enumerations.TypeEnumAction;
import Enumerations.TypeEnumCarteAction;
import Enumerations.TypeEnumTresors;

/**
 *
 * @author violentt
 */
public abstract class CarteRouge {
   private String nom; //Montée des eaux ou carte tresor ou carte action
   private String description; //Description de la carte, si nécessaire
   private final String image; //nom fichier image
   
   public CarteRouge(String nom, String image){     // instancation CarteRouge
        this.nom=nom;
        this.image = image;
    }
   
   
    public CarteRouge(String nom, String description, String image){     //instancation CarteRoug
        this.nom=nom;
        this.description=description;
        this.image = image;
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
    
    public String getImage() {
        return image;
    }
    
    public abstract TypeEnumCarteAction getTypeCarteAction();
    
    public abstract TypeEnumTresors getTypeTresor();
    
}
