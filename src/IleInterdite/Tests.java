/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import Cartes.CarteInondation;
import Enumerations.TypeEnumCouleurPion;
import Personnages.Explorateur;
import Personnages.Ingenieur;
import java.util.ArrayList;

/**
 *
 * @author Thomas
 */
public class Tests {

    /**
     * @param args the command line arguments
     */
    
    //permet de tester les différentes fonctions du programme
    //A executer puis ce laissez guider
    //ArrayList<Personnage> perso = new ArrayList<>();
    Grille grille;
    Explorateur perso1;
    Ingenieur perso2;
    
    public Tests() {
        perso1 = new Explorateur("NomExplorateur1", grille);
        perso2 = new Ingenieur("NomIngenieur1", grille);
        grille = new Grille(perso1, perso2);
        
        Tuile[][] t = grille.getTuiles();
        
        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 5; j++) {
                if (t[i][j] != null) {
                    System.out.print(i + ":" + j + "\t" + t[i][j].getNom() + "\t");
                } else {
                    System.out.print(i + ":" + j + "\t NULL \t\t");
                }
                
            }
            System.out.println();
        }
        
        System.out.println("perso1:");
        Tuile tuile = perso1.getEmplacement();
        System.out.println(tuile.getNom() + "\t" + tuile.getX() + "\t" + tuile.getY());
        System.out.println("perso2:");
        tuile = perso2.getEmplacement();
        System.out.println(tuile.getNom() + "\t" + tuile.getX() + "\t" + tuile.getY());
        
        
    }
    
    public static void main(String[] args) {new Tests();}
}
