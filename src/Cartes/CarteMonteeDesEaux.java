/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartes;

/**
 *
 * @author violentt
 */
public class CarteMonteeDesEaux extends CarteRouge{
        
        public CarteMonteeDesEaux(String nom, boolean estTiree){
            super(nom,estTiree);
        }

         public CarteMonteeDesEaux(String nom, boolean estTiree, String description){
            super(nom,estTiree,description);
        }
         
         @Override
         
         public String getNom(){
             return "Carte MonteeDesEaux";
         }
}
