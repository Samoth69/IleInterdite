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
    
    private final ArrayList <Tuile> tuiles;
    private final Personnage personnage;
    
    Grille(Personnage perso) {
        tuiles = new ArrayList();
        this.personnage = perso;
    }
    
    public Collection <Tuile> getTuiles() {
        return tuiles;    
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
