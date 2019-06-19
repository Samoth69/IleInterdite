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

    private TypeEnumTresors typeTresor;

    public CarteTresor(String nom, TypeEnumTresors typeTresor, String img) {
        super(nom, typeTresor.toString(), img);
        this.typeTresor = typeTresor;
    }

    @Override
    public String getNom() {
        return super.getNom();
    }

    public TypeEnumCarteAction getTypeCarteAction() {
        return TypeEnumCarteAction.NA;
    }

    public TypeEnumTresors getTypeTresor() {
        return typeTresor;
    }
}
