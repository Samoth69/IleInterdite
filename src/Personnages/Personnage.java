/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnages;

import Cartes.CarteRouge;
import IleInterdite.Grille;
import IleInterdite.Tuille;
import java.util.ArrayList;

/**
 *
 * @author violentt
 */
public abstract class Personnage {
    private boolean surTuilleInondee = false;
    private String nom;
    private Tuille emplacementJoueur;
    private Grille ile;
    private ArrayList<CarteRouge> cartes = new ArrayList<>();
    
    Personnage(String nom, Tuille emplacementJoueur, Grille ile) {
        this.nom = nom;
        this.emplacementJoueur = emplacementJoueur;
        this.ile = ile;
    }
    
    public Tuille[] getDeplacements(Tuille EmplacementJoueur) {
        
    }
    
    public void deplacement(Tuille nouvelleTuille) {
        
    }
    
    public Tuille getEmplacement() {
        
    }
    
    public void setSurTuilleInondee(boolean val) {
        surTuilleInondee = val;
    }
    
    public boolean getSurTuilleInondee() {
        return surTuilleInondee;
    }
   
    public Tuille[] getTuilleQuiPeutSecher() {
        
    }
    
    public int getNbTuilleSechable() {
        
    }
    
    public void donnerCarteAJoueur(Personnage personnage, CarteRouge[] cartes)  {
        
    }
    
    public void donnerCarteAJoueur(Personnage personnage, CarteRouge carte) {
        
    }
    
    public void addCartes(CarteRouge[] cartes) {
        
    }
    
    public void addCarte(CarteRouge[] carte) {
        
    }
    
    public CarteRouge[] getCartes() {
        
    }
    
    public void removeCartes(CarteRouge[] cartes) {
        
    }
    
    public void removeCarte(CarteRouge carte) {
        
    }
}
