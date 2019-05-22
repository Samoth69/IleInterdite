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
    
    //Prend une tuile en parametre. Augmente directement le niveau d'innondation d'une tuile
    //sans passer par le tableau de tuile
    public void AugmenterInnondation(Tuile tuile) {
        tuile.augmenterInondation();
    }
    
    //Prend un nom de carte en parametre. Augmente le niveau d'inondation d'une tuile en
    //fonction du nom d'une carte innondation
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
    
    //Prend une tuile en parametre. Reduit directement le niveau d'innondation d'une tuile
    //sans passer par le tableau de tuile
    public void ReduireInondation(Tuile tuile) {
        tuile.reduireInondation();
    }

    
    //prend une tuile en parametre et renvoie un arraylist de tuile praticable autour de celle-ci
    public ArrayList<Tuile> getTuilesAutoursPraticable(Tuile tuile) {
        ArrayList<Tuile> tuilesAutourPraticable = new ArrayList<>();
        
        for(int i = -1; i < 1; i++)
        {
            for(int j = -1; j < 1; j++)
            {
                if((tabTuile[tuile.getX()+i][tuile.getY()+i].getInondation() == TypeEnum.SEC) || (tabTuile[tuile.getX()+i][tuile.getY()+i].getInondation() == TypeEnum.MOUILLE))
                {
                    tuilesAutourPraticable.add(tuile);
                }
            }
        }
        return tuilesAutourPraticable;
    }
    
    //Surcharge de la fonction précédente pour prendre un personnage en paramétre
    public ArrayList<Tuile> getTuilesAutoursPraticable(Personnage personnage) {
        return getTuilesAutoursPraticable(personnage.getEmplacement());
    }
    
    public ArrayList<Tuile> getTuilesAutoursMouille(Tuile tuile) {
        ArrayList<Tuile> tuilesAutourPraticable = new ArrayList<>();
        for(int i = -1; i < 1; i++)
        {
            for(int j = -1; j < 1; j++)
            {
                if(tabTuile[tuile.getX()+i][tuile.getY()+i].getInondation() == TypeEnum.MOUILLE)
                {
                    tuilesAutourPraticable.add(tuile);
                }
            }
        }
        return tuilesAutourPraticable;
    }
    
    public ArrayList<Tuile> getTuilesAutoursMouille(Personnage Personnage) {
        return getTuilesAutoursMouille(Personnage.getEmplacement());
    }
        
}
