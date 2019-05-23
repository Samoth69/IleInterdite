/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import Cartes.CarteInondation;
import Enumerations.TypeEnum;
import Enumerations.TypeEnumCouleurPion;
import Personnages.Personnage;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author violentt
 */
public class Grille {
    
   
    private final Tuile tabTuile[][] = new Tuile[5][5]; // A potentiellement changer pour mettre des cases vides --- par quoi mdr ?
    private ArrayList<Personnage> persos = new ArrayList<>(); 
    
    Grille(Personnage perso1, Personnage perso2) {
        persos.add(perso1);
        persos.add(perso2);
        genererTableauTuiles();
    }
    
    Grille(Personnage perso1, Personnage perso2, Personnage perso3) {
        persos.add(perso1);
        persos.add(perso2);
        persos.add(perso3);
        genererTableauTuiles();
    }
    
    Grille(Personnage perso1, Personnage perso2, Personnage perso3, Personnage perso4) {
        persos.add(perso1);
        persos.add(perso2);
        persos.add(perso3);
        persos.add(perso4);
        genererTableauTuiles();
    }
    
    public Tuile[][] getTuiles() {
        return tabTuile;    
    }
    
    //génère le tableau des tuiles aléatoire
    private void genererTableauTuiles() {
        ArrayList<CarteInondation> tuile = getListTuiles();
        Collections.shuffle(tuile); //mélange la liste des tuiles
        
        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 5; j++) {
                tabTuile[i][j] = new Tuile(i, j, tuile.get(i + j).getNom(), tuile.get(i + j).getCouleurPion());
            }
        }
    }
    
    //renvoie les cartes du jeu
    public ArrayList<CarteInondation> getListTuiles() {
        ArrayList<CarteInondation> out = new ArrayList<>();
        
        out.add(new CarteInondation("Le Pont des Abimes"));
        out.add(new CarteInondation("La Porte de Bronze", TypeEnumCouleurPion.ROUGE));
        out.add(new CarteInondation("La Caverne des Ombres"));
        out.add(new CarteInondation("La Porte de Fer", TypeEnumCouleurPion.VIOLET));
        out.add(new CarteInondation("La Porte d'Or", TypeEnumCouleurPion.JAUNE));
        out.add(new CarteInondation("Les Falaises de l’Oubli"));
        out.add(new CarteInondation("Le Palais de Corail"));
        out.add(new CarteInondation("La Porte d'Argent", TypeEnumCouleurPion.ORANGE));
        out.add(new CarteInondation("Les Dunes de l'Illusion"));
        out.add(new CarteInondation("Heliport", TypeEnumCouleurPion.BLEU));
        out.add(new CarteInondation("La Porte de Cuivre", TypeEnumCouleurPion.VERT));
        out.add(new CarteInondation("Le Jardin des Hurlements"));
        out.add(new CarteInondation("La Foret Pourpre"));
        out.add(new CarteInondation("Le Lagon Perdu"));
        out.add(new CarteInondation("Le Marais Brumeux"));
        out.add(new CarteInondation("Observatoire"));
        out.add(new CarteInondation("Le Rocher Fantome"));
        out.add(new CarteInondation("La Caverne du Brasier"));
        out.add(new CarteInondation("Le Temple du Soleil"));
        out.add(new CarteInondation("Le Temple de La Lune"));
        out.add(new CarteInondation("Le Palais des Marees"));
        out.add(new CarteInondation("Le Val du Crepuscule"));
        out.add(new CarteInondation("La Tour du Guet"));
        out.add(new CarteInondation("Le Jardin des Murmures"));
        
        return out;
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

    
    //Prend une tuile en parametre. renvoie un arrylist de tuile.
    //Renvoie les tuile praticable(SEC ou MOUILLE) autour de la tuile en parametre
    public ArrayList <Tuile> getTuilesAutoursPraticable(Tuile tuile) {
        ArrayList<Tuile> tuilesAutourPraticable = new ArrayList<>();
        //verif diagonale haut gauche
        for (int i = -1; i<1; i++) {
            for (int j = -1; j<1; j++) {
                    if(tuile.getX()+i<0 || tuile.getX()+i>5 || tuile.getY()<0 || tuile.getY()>5)
                    {
                        System.out.println("Tuile innexistante");
                    }
                    else
                    {
                        if((tabTuile[tuile.getX()+i][tuile.getY()+j].getInondation() == TypeEnum.SEC) || (tabTuile[tuile.getX()+i][tuile.getY()+j].getInondation() == TypeEnum.MOUILLE)) {
                        tuilesAutourPraticable.add(tuile);
                    }
                
                }
            } 
        }
        return tuilesAutourPraticable;
    }
    
    //Surcharge de la fonction precedente
    public ArrayList <Tuile> getTuilesAutoursPraticable(Personnage personnage) {
        return getTuilesAutoursPraticable(personnage.getEmplacement());
    }
    
    //Prend une tuile en parametre. renvoie un arrylist de tuile.
    //Renvoie les tuile MOUILLE autour de la tuile en parametre.
    public ArrayList <Tuile> getTuilesAutoursMouille(Tuile tuile) {
        ArrayList<Tuile> tuilesAutourMouille = new ArrayList<>();
        for (int i = -1; i<1; i++) {
                for (int j = -1; j<1; j++) {
                    if(tuile.getX()+i<0 || tuile.getX()+i>5 || tuile.getY()<0 || tuile.getY()>5)
                    {
                        System.out.println("Tuile innexistante");
                    }
                    else
                    {
                        if((tabTuile[tuile.getX()+i][tuile.getY()+j].getInondation() == TypeEnum.MOUILLE)) {
                        tuilesAutourMouille.add(tuile);  
                    }
                }
            }
        }
        return tuilesAutourMouille;
    }
    
    //Surcharge de la fonction precedente
    public ArrayList <Tuile> getTuilesAutoursMouille(Personnage personnage) {
        return getTuilesAutoursMouille(personnage.getEmplacement());
    }
        
}
