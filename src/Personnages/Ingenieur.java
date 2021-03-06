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

/**
 *
 * @author violentt
 */
public class Ingenieur extends Personnage {

    //CONSTRUCTEUR
    public Ingenieur(String nom, Grille ile) {
        super(nom, ile, TypeEnumCouleurPion.ROUGE);
    }

    //METHODE
    @Override
    public TypeEnumPersonnages getType() {
        return TypeEnumPersonnages.INGENIEUR;
    }
}
