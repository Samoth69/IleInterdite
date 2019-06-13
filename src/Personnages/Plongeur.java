/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnages;

import Enumerations.TypeEnumCouleurPion;
import Enumerations.TypeEnumInondation;
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

    private ArrayList<Tuile> parcour = new ArrayList<>();

    @Override
    public ArrayList<Tuile> getDeplacements() {
        parcour.clear();
        drawChemin(super.getEmplacement());
        for (Tuile t : super.ile.getTuilesAutoursPraticable(this)) {
            if (!parcour.contains(t)) {
                parcour.add(t);
            }
        }
        System.out.println();
        return parcour;
    }

    private void drawChemin(Tuile pos) {
        for (Tuile t : getGrille().getTuilesAutours(pos)) {
            System.out.println("for: " + t.getNom());
            if (t.getX() == pos.getX() || t.getY() == pos.getY()) {
                if (!parcour.contains(pos)) {
                    if (t.getInondation() == TypeEnumInondation.SEC) {
                        parcour.add(pos);
                    } else {
                        parcour.add(pos);
                        drawChemin(t);
                    }
                }
            }
        }
    }
}