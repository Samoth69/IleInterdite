/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnages;

import IleInterdite.Grille;
import IleInterdite.Tuile;
import java.util.ArrayList;

/**
 *
 * @author violentt
 */
public class Explorateur extends Personnage{
    
    Explorateur(String nom, Tuile emplacementJoueur, Grille ile) {
        super(nom, emplacementJoueur, ile);
    }
    
    public ArrayList<Tuile> getDeplacements(Tuile EmplacementJoueur) {
        return ile.getTuilesAutoursPraticable(this); //liste
    }
    
    public ArrayList<Tuile> getTuilleQuiPeutSecher() {
        return ile.getTuilesAutoursMouille(this);
    }
}
