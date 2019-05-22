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

    
    public ArrayList <Tuile> getTuilesAutoursPraticable(Tuile tuile) {
        ArrayList<Tuile> tuilesAutourPraticable = new ArrayList<>();
        //verif diagonale haut gauche
        for (int i = 0; i<6; i++) {
            for (int j = 0; j<6; j++) {
                if((tabTuile[i][j].getInondation() == TypeEnum.SEC) || (tabTuile[i][j].getInondation() == TypeEnum.MOUILLE)) {
                    tuilesAutourPraticable.add(tuile);
                }
            } 
        }
        return tuilesAutourPraticable;
    }
    
    public ArrayList <Tuile> getTuilesAutoursPraticable(Personnage personnage) {
        return getTuilesAutoursPraticable(personnage.getEmplacement());
    }
    
    public ArrayList <Tuile> getTuilesAutoursMouille(Tuile tuile) {
        ArrayList<Tuile> tuilesAutourMouille = new ArrayList<>();
        for (int i = 0; i<6; i++) {
                for (int j = 0; j<6; j++) {
                    if((tabTuile[i][j].getInondation() == TypeEnum.MOUILLE)) {
                    tuilesAutourMouille.add(tuile);
                }
            }
        }
        return tuilesAutourMouille;
    }
    
    public ArrayList <Tuile> getTuilesAutoursMouille(Personnage personnage) {
        return getTuilesAutoursMouille(personnage.getEmplacement());
    }
        
}
