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
public class Pilote extends Personnage{
    
    public Pilote(String nom, Grille ile) {
        super(nom, ile, TypeEnumCouleurPion.BLEU);
    }
    
    @Override
    public ArrayList<Tuile> getDeplacements() {
        Tuile tb[][] = super.getGrille().getTuiles();
        ArrayList<Tuile> out = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 5; j++) {
                if (i != super.getEmplacement().getX() || j != super.getEmplacement().getY()) {
                    out.add(tb[i][j]);
                }
            }
        }
        return out;
    }
    
}
