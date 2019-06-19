/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartes;

import Enumerations.TypeEnumCouleurPion;
import Enumerations.TypeEnumTresors;

/**
 *
 * @author violentt
 */
public class CarteInondation {
    
    //ATTRIBUTS
    private final String nom;
    private TypeEnumTresors tresor;
    private final TypeEnumCouleurPion couleurPion;
    private final String cheminCarte;
    private final String cheminTuile;

    //CONSTRUCTEUR1
    public CarteInondation(String nom, String cheminCarte, String cheminTuile) {
        this.nom = nom;
        tresor = TypeEnumTresors.AUCUN;
        couleurPion = TypeEnumCouleurPion.AUCUN;
        this.cheminCarte = cheminCarte;
        this.cheminTuile = cheminTuile;
    }
    
    //CONSTRUCTEUR2
    public CarteInondation(String nom, TypeEnumCouleurPion couleurPion, String cheminCarte, String cheminTuile) {
        this.nom = nom;
        this.couleurPion = couleurPion;
        tresor = TypeEnumTresors.AUCUN;
        this.cheminCarte = cheminCarte;
        this.cheminTuile = cheminTuile;
    }
    
    //CONSTRUCTEUR3
    public CarteInondation(String nom, TypeEnumTresors tresor, String cheminCarte, String cheminTuile) {
        this.nom = nom;
        couleurPion = TypeEnumCouleurPion.AUCUN;
        this.tresor = tresor;
        this.cheminCarte = cheminCarte;
        this.cheminTuile = cheminTuile;
    }

    //CONSTRUCTEUR4
    public CarteInondation(String nom, TypeEnumCouleurPion couleurPion, TypeEnumTresors tresor, String cheminCarte, String cheminTuile) {
        this.nom = nom;
        this.couleurPion = couleurPion;
        this.tresor = tresor;
        this.cheminCarte = cheminCarte;
        this.cheminTuile = cheminTuile;
    }
    
    //retourne le nom
    public String getNom() {
        return nom;
    }
    
    //retourne la couleur du pion
    public TypeEnumCouleurPion getCouleurPion() {
        return couleurPion;
    }
    
    //retourne le "type" de tresor
    public TypeEnumTresors getTresor() {
        return tresor;
    }
    
    //attribue un tresor
    public void setTresor(TypeEnumTresors tresor) {
        this.tresor = tresor;
    }
    
    //chemin fichier vers la carte
    public String getCheminCarte() {
        return cheminCarte;
    }
    
    //chemin fichier vers la tuile
    public String getCheminTuile() {
        return cheminTuile;
    }
    
    //chemin fichier vers tuileinnondee
    public String getCheminTuileInondee() {
        String tmp = cheminTuile;
        tmp.replace(".png", "_Inonde.png");
        return tmp;
    }

}
