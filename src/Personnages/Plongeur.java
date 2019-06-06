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
public class Plongeur extends Personnage{
    
    public Plongeur(String nom, Grille ile) {
        super(nom, ile, TypeEnumCouleurPion.VIOLET);
    }
    
    @Override
    public ArrayList<Tuile> getDeplacements() {
        return super.ile.getTuilesAutoursPraticable(this);
    }
    
}