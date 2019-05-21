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
    
    private final ArrayList <Tuille> tuiles;
    private final Personnage personnage;
    
    Grille(Personnage perso) {
        tuiles = new ArrayList();
        this.personnage = perso;
    }
    
    public Collection <Tuille> getTuiles() {
        return tuiles;    
    }
    
    public void AugmenterInnondation(Tuille PositionTuile) {
        
    }
    
    public void ReduireInondation(Tuille PositionTuile) {
        
    }
    
    public void getTuilesAutoursPraticable(Tuille tuile) {
        
    }
    
    public void getTuilesAutoursPraticable(Personnage personnage) {
        
    }
    
    public void getTuilesAutoursMouille(Tuille tuile) {
        
    }
    
    public void getTuilesAutoursMouille(Personnage Personnage) {
        
    }
        
}
