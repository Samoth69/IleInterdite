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

    public CarteMonteeDesEaux(String nom, String img) {
        super(nom, img);
    }

    public CarteMonteeDesEaux(String nom, String description, String img) {
        super(nom, description, img);
    }

    @Override

    public String getNom() {
        return super.getNom();
    }

    @Override
    public TypeEnumCarteAction getTypeCarteAction() {
        return TypeEnumCarteAction.NA;
    }

    @Override
    public TypeEnumTresors getTypeTresor() {
        return TypeEnumTresors.AUCUN; // A changer si besoin
    }
}
