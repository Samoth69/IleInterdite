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
    
    //CONSTRUCTEUR
    public Explorateur(String nom, Grille ile) {
        super(nom, ile, TypeEnumCouleurPion.VERT);
    }
    
    //METHODES
    @Override
    public ArrayList<Tuile> getDeplacements() {
        return super.ile.getTuilesAutoursPraticable(this); //retourne la liste des tuiles praticable
    }

    @Override
    public ArrayList<Tuile> getTuileQuiPeutSecher() {
        return super.ile.getTuilesAutoursMouille(this); //retourne la liste des tuile que l'explorateur peut secher
    }

    @Override
    public TypeEnumPersonnages getType() {
        return TypeEnumPersonnages.EXPLORATEUR; //retourne le type : Explorateur
    }
}
