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
public class CarteMonteeDesEaux extends CarteRouge {
    
    //CONSTRUCTEUR1
    public CarteMonteeDesEaux(String nom, String img) {
        super(nom, img);
    }
    
    //CONSTRUCTEUR2
    public CarteMonteeDesEaux(String nom, String description, String img) {
        super(nom, description, img);
    }
    
    //METHODES

    //retourne le nom
    @Override
    public String getNom() {
        return super.getNom();
    }

    // getter ==> ce n'est ni un carte action ni une carte tresor
    @Override
    public TypeEnumCarteAction getTypeCarteAction() {
        return TypeEnumCarteAction.NA;
    }

    @Override
    public TypeEnumTresors getTypeTresor() {
        return TypeEnumTresors.AUCUN; // A changer si besoin
    }
}
