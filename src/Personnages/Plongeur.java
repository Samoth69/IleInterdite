/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnages;

import Enumerations.TypeEnumCouleurPion;
import Enumerations.TypeEnumInondation;
import Enumerations.TypeEnumPersonnages;
import IleInterdite.Grille;
import IleInterdite.Tuile;
import java.util.ArrayList;

/**
 *
 * @author violentt
 */
public class Plongeur extends Personnage {

    public Plongeur(String nom, Grille ile) {
        super(nom, ile, TypeEnumCouleurPion.VIOLET);
    }

    @Override
    public ArrayList<Tuile> getDeplacements() {
        ArrayList<Tuile> out = new ArrayList<>();
        Tuile tuileActuel = getEmplacement();
        drawChemin(out, tuileActuel);

        //filtre pour enlever les tuiles inondée
        ArrayList<Tuile> toDelete = new ArrayList<>();
        for (Tuile t : out) {
            if (t.getInondation() == TypeEnumInondation.INONDE) {
                toDelete.add(t);
            }
        }
        out.removeAll(toDelete);

        return out;
    }

    private void drawChemin(ArrayList<Tuile> out, Tuile pos) {
        for (Tuile t : ile.getTuilesAutours(pos)) {
            if (!out.contains(t)) {
                if (pos.getX() == t.getX() || pos.getY() == t.getY()) {
                    out.add(t);
                    if (t.getInondation() != TypeEnumInondation.SEC) {
                        drawChemin(out, t);
                    }
                }
            }
        }
    }

    @Override
    public TypeEnumPersonnages getType() {
        return TypeEnumPersonnages.PLONGEUR;
    }
}
