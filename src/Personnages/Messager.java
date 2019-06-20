/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnages;

import Cartes.CarteRouge;
import Enumerations.TypeEnumCouleurPion;
import Enumerations.TypeEnumPersonnages;
import IleInterdite.Grille;
import IleInterdite.Tuile;
import java.util.ArrayList;

/**
 *
 * @author violentt
 */
public class Messager extends Personnage {

    public Messager(String nom, Grille ile) {
        super(nom, ile, TypeEnumCouleurPion.ORANGE);
    }

    @Override
    public void donnerCarteAJoueur(Personnage personnage, ArrayList<CarteRouge> cartes) {
        if (personnage.getEmplacement() == this.getEmplacement()) {
            personnage.addCartes(cartes);
            this.removeCartes(cartes);
        }
    }

    @Override
    public void donnerCarteAJoueur(Personnage personnage, CarteRouge carte) {
        if (personnage.getEmplacement() == this.getEmplacement()) {
            personnage.addCarte(carte);
            this.removeCarte(carte);
        }
    }

    @Override
    public TypeEnumPersonnages getType() {
        return TypeEnumPersonnages.MESSAGER;
    }
}
