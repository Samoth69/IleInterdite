/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartes;

import Enumerations.TypeEnumCarteAction;
import Enumerations.TypeEnumTresors;

/**
 *
 * @author violentt
 */
public class CarteTresor extends CarteRouge {
    
    //ATTRIBUT
    private TypeEnumTresors typeTresor;
    
    //CONSTRUCTEUR
    public CarteTresor(String nom, TypeEnumTresors typeTresor, String img) { //instanciation
        super(nom, typeTresor.toString(), img);
        this.typeTresor = typeTresor;
    }
    
    //retourne le nom (voir super)
    @Override
    public String getNom() {
        return super.getNom();
    }
    
    //retourne qu il s'agit d une carte action et non d une carte tresor
    public TypeEnumCarteAction getTypeCarteAction() {
        return TypeEnumCarteAction.NA;
    }

    public TypeEnumTresors getTypeTresor() {
        return typeTresor;
    }
}
