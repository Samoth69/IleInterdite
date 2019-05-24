/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;
import IleInterdite.Grille;
import Personnages.*;
import IleInterdite.Tuile;

/**
 *
 * @author mariottp
 */

public class IHMsimple {

    Explorateur explo;
    Ingenieur inge;
    Grille grille;
    Tuile plateau[][] = new Tuile[][];
    
    
    public IHMsimple() {
        explo = new Explorateur("NomExplorateur1", grille);
        inge = new Ingenieur("NomIngenieur1", grille);
        grille = new Grille(explo, inge);
        
        grille.getTuiles();

    }
        
    public static void main(String[] args){
        new IHMsimple();
    }
    
}
