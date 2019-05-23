/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnages;

import Enumerations.TypeEnumCouleurPion;
import IleInterdite.Grille;
import IleInterdite.Tuile;
import java.util.ArrayList;

/**
 *
 * @author violentt
 */
public class Explorateur extends Personnage{
    
    public Explorateur(String nom, Grille ile) {
        super(nom, ile, TypeEnumCouleurPion.VERT);
    }
    
    public ArrayList<Tuile> getDeplacements(Tuile EmplacementJoueur) {
        return super.ile.getTuilesAutoursPraticable(this); //liste
    }
    
    public ArrayList<Tuile> getTuilleQuiPeutSecher() {
        return super.ile.getTuilesAutoursMouille(this);
    }
}
