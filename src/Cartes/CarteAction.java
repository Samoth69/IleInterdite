/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartes;

import Enumerations.TypeEnumCarteAction;

/**
 *
 * @author violentt
 */
public class CarteAction extends CarteRouge{
        
        public CarteAction(String nom, TypeEnumCarteAction typeAction){
            super(nom, typeAction.toString());
        }
         
        @Override
        public String getNom(){
            return "CarteAction";
        }
}
