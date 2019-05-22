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
public class Plongeur extends Personnage{
    
    Plongeur(String nom, Tuile emplacementJoueur, Grille ile) {
        super(nom, emplacementJoueur, ile);
    }
    
    @Override
    public ArrayList<Tuile> getDeplacements(Tuile emplacementJoueur) {
        Tuile tb[][] = super.getGrille().getTuiles();
        ArrayList<Tuile> out = new ArrayList<>();
        int currentX = emplacementJoueur.getX();
        int currentY = emplacementJoueur.getY();
        
        ArrayList<XY> pos = new ArrayList<>();
        return out;
    }
    
}

class XY {
    private int x;
    private int y;
    
    XY(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
}
