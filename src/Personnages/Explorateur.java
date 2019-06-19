/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnages;

import Enumerations.TypeEnumCouleurPion;
import Enumerations.TypeEnumPersonnages;
import IleInterdite.Grille;
import IleInterdite.Tuile;
import java.util.ArrayList;

/**
 *
 * @author violentt
 */
public class Explorateur extends Personnage {

    public Explorateur(String nom, Grille ile) {
        super(nom, ile, TypeEnumCouleurPion.VERT);
    }

    @Override
    public ArrayList<Tuile> getDeplacements() {
        return super.ile.getTuilesAutoursPraticable(this); //liste
    }

    @Override
    public ArrayList<Tuile> getTuileQuiPeutSecher() {
        return super.ile.getTuilesAutoursMouille(this);
    }

    @Override
    public TypeEnumPersonnages getType() {
        return TypeEnumPersonnages.EXPLORATEUR;
    }
}
