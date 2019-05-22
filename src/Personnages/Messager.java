/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnages;

import Cartes.CarteRouge;
import IleInterdite.Grille;
import IleInterdite.Tuile;
import java.util.ArrayList;

/**
 *
 * @author violentt
 */
public class Messager extends Personnage{
    
    Messager(String nom, Tuile emplacementJoueur, Grille ile) {
        super(nom, emplacementJoueur, ile);
    }
    
    @Override
    public void donnerCarteAJoueur(Personnage personnage, ArrayList<CarteRouge> cartes)  {
        
    }
    
    @Override
    public void donnerCarteAJoueur(Personnage personnage, CarteRouge carte) {
        
    }
}
