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
    
   
    private final Tuile tabTuile[][] = new Tuile[5][5]; // A potentiellement changer pour mettre des cases vides
    private ArrayList<Personnage> persos = null;
    
    Grille(Personnage perso1, Personnage perso2) {
        persos.add(perso1);
        persos.add(perso2);
    }
    
    Grille(Personnage perso1, Personnage perso2, Personnage perso3) {
        persos.add(perso1);
        persos.add(perso2);
        persos.add(perso3);
    }
    
    Grille(Personnage perso1, Personnage perso2, Personnage perso3, Personnage perso4) {
        persos.add(perso1);
        persos.add(perso2);
        persos.add(perso3);
        persos.add(perso4);
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
        ArrayList<Tuile> tuilesAutourPraticable = null;
        
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
        ArrayList<Tuile> tuilesAutourPraticable = null;
        Tuile tuile = personnage.getEmplacement();
                
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
    
    //Prend une tuile en parametre. Renvoie un Arraylist de tuile.
    //Renvoie les tuiles mouille autour de celle passé en paramétre.
    public ArrayList<Tuile> getTuilesAutoursMouille(Tuile tuile) {
        ArrayList<Tuile> tuilesAutourMouille = null;
        
        for(int i = -1; i < 1; i++)
        {
            for(int j = -1; j < 1; j++)
            {
                if(tabTuile[tuile.getX()+i][tuile.getY()+i].getInondation() == TypeEnum.MOUILLE)
                {
                    tuilesAutourMouille.add(tuile);
                }
            }
        }
        return tuilesAutourMouille;
    }
    
    //Prend un personnage en parametre. Renvoie un Arraylist de tuile.
    //Renvoie les tuiles mouille autour de la tuile sur laquelle est le personnage.
    public ArrayList<Tuile> getTuilesAutoursMouille(Personnage personnage) {
        ArrayList<Tuile> tuilesAutourMouille = null;
        Tuile tuile = personnage.getEmplacement();
        
        for(int i = -1; i < 1; i++)
        {
            for(int j = -1; j < 1; j++)
            {
                if(tabTuile[tuile.getX()+i][tuile.getY()+i].getInondation() == TypeEnum.MOUILLE)
                {
                    tuilesAutourMouille.add(tuile);
                }
            }
        }
        return tuilesAutourMouille;
    }
        
}
