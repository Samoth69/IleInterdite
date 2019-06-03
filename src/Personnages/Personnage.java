/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnages;

import Cartes.CarteRouge;
import Enumerations.TypeEnumCouleurPion;
import Enumerations.TypeEnumInondation;
import IleInterdite.Grille;
import IleInterdite.Tuile;
import java.util.ArrayList;

/**
 *
 * @author violentt
 */
public abstract class Personnage {
    //nom du joueur
    private String nom;
    //emplacement du Joueur (liée à la tuille sur laquel le personnage est)
    private Tuile emplacementJoueur;
    //grille du jeu
    protected Grille ile;
    //cartes que le joueur possède
    private ArrayList<CarteRouge> cartes = new ArrayList<>();
    //couleur pion associé au personnage
    private TypeEnumCouleurPion pion = TypeEnumCouleurPion.AUCUN;
    
    Personnage(String nom, Grille ile, TypeEnumCouleurPion pion) {
        this.nom = nom;
        this.ile = ile;
        this.pion = pion;
        //System.out.println("init perso");
    }
    
    //utiliser uniquement pour définir l'emplacement de départ du joueur.
    //utiliser la fonction deplacement pour déplacer le personnage sur la grille
    public void setEmplacementJoueur(Tuile emplacementJoueur) {
        if (this.emplacementJoueur == null) {
            this.emplacementJoueur = emplacementJoueur;
        } else {
            new Error("La fonction setEmplacementJoueur ne doit être utiliser que à l'initialisation du personnage ! \nPas pour déplacer le joueur sur la map");
        }
    }
    
    //récupère la couleur du pion associé à ce personnage
    public TypeEnumCouleurPion getCouleurPion() {
        return pion;
    }
    
    //renvoie les déplacement possible autour de la position actuel du joueur
    //peut être renvoyer une arraylist vide
    public ArrayList<Tuile> getDeplacements(Tuile EmplacementJoueur) {
        ArrayList<Tuile> tmp = ile.getTuilesAutoursPraticable(this); //liste
        ArrayList<Tuile> out = new ArrayList<>(); //liste que l'on revoie à la fin (même si elle est vide)
        
        int currentX = this.emplacementJoueur.getX();
        int currentY = this.emplacementJoueur.getY();
        
        if (tmp != null) {
            if (!tmp.isEmpty()) {
               for (Tuile t : tmp) {
                    if (t.getX() == currentX || t.getY() == currentY) { //si X ou Y dans la liste est égal à la position actuel du personnage
                        out.add(t);
                    }
                } 
            }
        }
        return out;
    }
    
    public void deplacement(Tuile nouvelleTuille) {
        emplacementJoueur.removeJoueur(this);
        emplacementJoueur = nouvelleTuille;
        emplacementJoueur.addJoueur(this);
    }
    
    public Tuile getEmplacement() {
        return emplacementJoueur; 
    }
    
    protected Grille getGrille() {
        return ile;
    }
    
    public void setGrille(Grille g) {
        ile = g;
    }

    //renvoie les iles qui peuvent être sécher autour du joueur
    //peut être renvoyer une arraylist vide
    public ArrayList<Tuile> getTuileQuiPeutSecher() {
        ArrayList<Tuile> tmp = ile.getTuilesAutoursMouille(this);
        ArrayList<Tuile> out = new ArrayList<>();
        
        int currentX = this.emplacementJoueur.getX();
        int currentY = this.emplacementJoueur.getY();
        
        if (tmp != null) {
            if (tmp.size() != 0) {
               for (Tuile t : tmp) {
                    if (t.getX() == currentX || t.getY() == currentY) {
                        out.add(t);
                    }
                } 
            }
        }
        return out;
    }
    
    //assecher une tuile
    public void assecher(Tuile tuileAssechable){
        if (tuileAssechable.getInondation() == TypeEnumInondation.SEC){
            System.out.println("Tuile est déjà sec");
        } else if(tuileAssechable.getInondation() == TypeEnumInondation.MOUILLE){
            tuileAssechable.reduireInondation();
        } else{
            System.out.print("Tuile est innondée, impossible de l'assécher");
        }
        
    }
    
    //renvoie le nombre de case qui peuvent être sécher en une action
    public int getNbTuileSechable() {
        return 1;
    }
    
    //donne une (ou plusieur) carte(s) à un autre personnage
    public void donnerCarteAJoueur(Personnage personnage, ArrayList<CarteRouge> cartes)  {
        if (personnage.getEmplacement() == this.getEmplacement()) {
            personnage.addCartes(cartes);
            this.removeCartes(cartes);
        }
    }
    
    //donne une carte à un autre joueur
    public void donnerCarteAJoueur(Personnage personnage, CarteRouge carte) {
        if (personnage.getEmplacement() == this.getEmplacement()) {
            personnage.addCarte(carte);
            this.removeCarte(carte);
        }
    }
    
    //ajoute la/les cartes à la liste des cartes de ce joueur
    public void addCartes(ArrayList<CarteRouge> cartes) {
        this.cartes.addAll(cartes);
    }
    
    //ajoute la carte à la liste des cartes de ce joueur
    public void addCarte(CarteRouge carte) {
        this.cartes.add(carte);
    }
    
    //renvoie toute les cartes que possède ce joueur
    public ArrayList<CarteRouge> getCartes() {
        return cartes;
    }
    
    //enlève une/des cartes de ce joueur
    public void removeCartes(ArrayList<CarteRouge> cartes) {
        this.cartes.removeAll(cartes);
    }
    
    //enlève une carte de ce joueur
    public void removeCarte(CarteRouge carte) {
        this.cartes.add(carte);
    }
    
    public String getNom(){
        return nom;
    }
}
