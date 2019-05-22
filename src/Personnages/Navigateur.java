/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnages;

import IleInterdite.Grille;
import IleInterdite.Tuile;

/**
 *
 * @author violentt
 */
public class Navigateur extends Personnage{
    
    Navigateur(String nom, Tuile emplacementJoueur, Grille ile) {
        super(nom, emplacementJoueur, ile);
    }
    /*
    @Override
    public Tuile[] getDeplacements(Tuile EmplacementJoueur) {
        
    }*/
}
