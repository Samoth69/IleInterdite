/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartes;

import Enumerations.TypeEnumTresors;

/**
 *
 * @author violentt
 */
public class CarteTresor extends CarteRouge{
    
        private TypeEnumTresors typeTresor;
        
        public CarteTresor(String nom, boolean estTiree, TypeEnumTresors typeTresor){
            super(nom,estTiree);
            this.typeTresor=typeTresor;
        }

         public CarteTresor(String nom, boolean estTiree, String description,TypeEnumTresors typeTresor){
            super(nom,estTiree,description);
            this.typeTresor=typeTresor;
        }
         
         @Override
         
         public String getNom(){
             return "CarteTresor";
         }
}
