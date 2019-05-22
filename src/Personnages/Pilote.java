/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnages;

import IleInterdite.Grille;
import IleInterdite.Tuile;
import java.util.ArrayList;

/**
 *
 * @author violentt
 */
public class Pilote extends Personnage{
    
    Pilote(String nom, Tuile emplacementJoueur, Grille ile) {
        super(nom, emplacementJoueur, ile);
    }
    
    @Override
    public ArrayList<Tuile> getDeplacements(Tuile EmplacementJoueur) {
        Tuile tb[][] = super.getGrille().getTuiles();
        ArrayList<Tuile> out = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 5; j++) {
                out.add(tb[i][j]);
            }
        }
        return out;
    }
    
}
