/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import Personnages.Personnage;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author violentt
 */
public class Grille {
    
    //private final ArrayList <Tuile> tuiles;
    private final Tuile tabTuile[][] = new Tuile[6][6]; // A potentiellement changer pour mettre des cases vides
    private Personnage personnage1, personnage2, personnage3, personnage4;
    
    Grille(Personnage perso1, Personnage perso2) {
        //tuiles = new ArrayList();
        this.personnage1 = perso1;
        this.personnage2 = perso2;
    }
    
    Grille(Personnage perso1, Personnage perso2, Personnage perso3) {
        //tuiles = new ArrayList();
        this.personnage1 = perso1;
        this.personnage2 = perso2;
        this.personnage3 = perso3;
    }
    
    Grille(Personnage perso1, Personnage perso2, Personnage perso3, Personnage perso4) {
        //tuiles = new ArrayList();
        this.personnage1 = perso1;
        this.personnage2 = perso2;
        this.personnage3 = perso3;
        this.personnage4 = perso4;
    }
    
    public Tuile[][] getTuiles() {
        return tabTuile;    
    }
    
    public void AugmenterInnondation(Tuile PositionTuile) {
        
    }
    
    public void ReduireInondation(Tuile PositionTuile) {
        
    }
    
    public void getTuilesAutoursPraticable(Tuile tuile) {
        
    }
    
    public void getTuilesAutoursPraticable(Personnage personnage) {
        
    }
    
    public void getTuilesAutoursMouille(Tuile tuile) {
        
    }
    
    public void getTuilesAutoursMouille(Personnage Personnage) {
        
    }
        
}
