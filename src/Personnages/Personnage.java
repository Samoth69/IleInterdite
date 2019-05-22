/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnages;

import Cartes.CarteRouge;
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
    private Grille ile;
    //cartes que le joueur possède
    private ArrayList<CarteRouge> cartes = new ArrayList<>();
    
    Personnage(String nom, Tuile emplacementJoueur, Grille ile) {
        this.nom = nom;
        this.emplacementJoueur = emplacementJoueur;
        this.ile = ile;
    }
    
    //renvoie les déplacement possible des joueurs
    //peut être renvoyer une arraylist vide
    public ArrayList<Tuile> getDeplacements(Tuile EmplacementJoueur) {
        ArrayList<Tuile> tmp = ile.getTuilesAutoursPraticable(this);
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
    
    public void deplacement(Tuile nouvelleTuille) {
        emplacementJoueur.removeJoueur(this);
        emplacementJoueur = nouvelleTuille;
        emplacementJoueur.addJoueur(this);
    }
    
    public Tuile getEmplacement() {
        return emplacementJoueur; 
    }

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
    
    public int getNbTuileSechable() {
        return 1;
    }
    
    public void donnerCarteAJoueur(Personnage personnage, ArrayList<CarteRouge> cartes)  {
        if (personnage.getEmplacement() == this.getEmplacement()) {
            personnage.addCartes(cartes);
            this.removeCartes(cartes);
        }
    }
    
    public void donnerCarteAJoueur(Personnage personnage, CarteRouge carte) {
        if (personnage.getEmplacement() == this.getEmplacement()) {
            personnage.addCarte(carte);
            this.removeCarte(carte);
        }
    }
    
    public void addCartes(ArrayList<CarteRouge> cartes) {
        this.cartes.addAll(cartes);
    }
    
    public void addCarte(CarteRouge carte) {
        this.cartes.add(carte);
    }
    
    public ArrayList<CarteRouge> getCartes() {
        return cartes;
    }
    
    public void removeCartes(ArrayList<CarteRouge> cartes) {
        this.cartes.removeAll(cartes);
    }
    
    public void removeCarte(CarteRouge carte) {
        this.cartes.add(carte);
    }
    
    public String getNom(){
        return nom;
    }
}
