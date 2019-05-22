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
    private TypeEnumCarteAction typeAction;
        
        public CarteAction(String nom, boolean estTiree, TypeEnumCarteAction typeAction){
            super(nom,estTiree);
            this.typeAction=typeAction;
        }

         public CarteAction(String nom, boolean estTiree, String description,TypeEnumCarteAction typeAction){
            super(nom,estTiree,description);
            this.typeAction=typeAction;
        }
         
         @Override
         
         public String getNom(){
             return "CarteAction";
         }
}
