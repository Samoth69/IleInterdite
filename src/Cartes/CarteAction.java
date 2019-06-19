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
public class CarteAction extends CarteRouge{
        
    private TypeEnumCarteAction type;
    
    public CarteAction(String nom, TypeEnumCarteAction typeAction, String img){     //Enlever String nom car inutile (?)
        super(nom, typeAction.toString(), img);
        type = typeAction;
    }

    @Override
    public String getNom(){
        return super.getNom();
    }

    @Override
    public TypeEnumCarteAction getTypeCarteAction() {
        return type;
    }

    @Override
    public TypeEnumTresors getTypeTresor() {
        return TypeEnumTresors.AUCUN;
    }
}
