/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import Personnages.Personnage;
import java.util.ArrayList;

/**
 *
 * @author violentt
 */
public class Grille {
    
    private ArrayList<Tuile> tuilesAutourPraticable;
    private final Tuile tabTuile[][] = new Tuile[6][6]; // A potentiellement changer pour mettre des cases vides
    private Personnage personnage1, personnage2, personnage3, personnage4;
    
    Grille(Personnage perso1, Personnage perso2) {
        //tuiles = new ArrayList();
        this.personnage1 = perso1;
        this.personnage2 = perso2;
    }
    
    Grille(Personnage perso1, Personnage perso2, Personnage perso3) {
        //tuiles = new ArrayList();
        this.personnage1 = perso1;
        this.personnage2 = perso2;
        this.personnage3 = perso3;
    }
    
    Grille(Personnage perso1, Personnage perso2, Personnage perso3, Personnage perso4) {
        //tuiles = new ArrayList();
        this.personnage1 = perso1;
        this.personnage2 = perso2;
        this.personnage3 = perso3;
        this.personnage4 = perso4;
    }
    
    public Tuile[][] getTuiles() {
        return tabTuile;    
    }
    
    public void AugmenterInnondation(Tuile tuile) { //peut-etre changer x et y par un tableau
        tuile.augmenterInondation();
    }
    
    public void AugmenterInnondation(String nom){
        for(int i = 0; i<6; i++)
        {
            for(int j = 0; i<6; j++)
            {
                if(tabTuile[i][j].getNom() == nom)
                {
                    tabTuile[i][j].augmenterInondation();
                }
            }
        }
    }
    
    public void ReduireInondation(Tuile tuile) {
        tuile.reduireInondation();
    }
    
    public void ReduireInondation(String nom) {
        for(int i = 0; i<6; i++)
        {
            for(int j = 0; i<6; j++)
            {
                if(tabTuile[i][j].getNom() == nom)
                {
                    tabTuile[i][j].reduireInondation();
                }
            }
        } 
    }
    
    public void getTuilesAutoursPraticable(Tuile tuile) {
        //verif diagonale haut gauche
        if((tabTuile[tuile.getX()-1][tuile.getY()-1].getInondation() == TypeEnum.SEC) || (tabTuile[tuile.getX()-1][tuile.getY()-1].getInondation() == TypeEnum.MOUILLE))
        {
            tuilesAutourPraticable.add(tuile);
        }
    }
    
    public void getTuilesAutoursPraticable(Personnage personnage) {
        
    }
    
    public void getTuilesAutoursMouille(Tuile tuile) {
        
    }
    
    public void getTuilesAutoursMouille(Personnage Personnage) {
        
    }
        
}
