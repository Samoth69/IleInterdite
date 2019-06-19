/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartes;

import Enumerations.TypeEnumAction;
import Enumerations.TypeEnumCarteAction;
import Enumerations.TypeEnumTresors;

/**
 *
 * @author violentt
 */
public class CarteAction extends CarteRouge {

    //Attribut
    private TypeEnumCarteAction type;

    //Constructeur
    public CarteAction(String nom, TypeEnumCarteAction typeAction, String img) {     //Enlever String nom car inutile (?)
        super(nom, typeAction.toString(), img);
        type = typeAction;
    }

    //METHODES
    //retourne le nom :carte action
    @Override
    public String getNom() {
        return super.getNom();
    }

    //retourne le type de la carte action (voir typeenum)
    @Override
    public TypeEnumCarteAction getTypeCarteAction() {
        return type;
    }

    //retourne que ce n est pas un type tresor
    @Override
    public TypeEnumTresors getTypeTresor() {
        return TypeEnumTresors.AUCUN;
    }
}
