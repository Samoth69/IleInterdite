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
    private final String nom;
    private final TypeEnumTresors tresor;
    private final TypeEnumCouleurPion couleurPion;
    
    public CarteInondation(String nom){
        this.nom = nom;
        tresor = TypeEnumTresors.AUCUN;
        couleurPion = TypeEnumCouleurPion.AUCUN;
    }
    
    public CarteInondation(String nom, TypeEnumCouleurPion couleurPion){
        this.nom = nom;
        this.couleurPion = couleurPion;
        tresor = TypeEnumTresors.AUCUN;
    }
    
    public CarteInondation(String nom, TypeEnumTresors tresor){
        this.nom = nom;
        couleurPion = TypeEnumCouleurPion.AUCUN;
        this.tresor = tresor;
    }
    
    public CarteInondation(String nom, TypeEnumCouleurPion couleurPion, TypeEnumTresors tresor){
        this.nom = nom;
        this.couleurPion = couleurPion;
        this.tresor = tresor;
    }
    
    public String getNom(){
        return nom;
    }
    
    public TypeEnumCouleurPion getCouleurPion() {
        return couleurPion;
    }
    
    public TypeEnumTresors getTresor() {
        return tresor;
    }
}
