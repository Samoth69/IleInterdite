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
        public CarteTresor(String nom, TypeEnumTresors typeTresor){
            super(nom, typeTresor.toString());
        }
         
        @Override
        public String getNom(){
            return "CarteTresor";
        }
        
        public String getTypeTresor(){
            return typeTresor.toString();
        }
}
