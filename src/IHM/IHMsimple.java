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
    Grille plateauJeu;
    
    public IHMsimple() {
        explo = new Explorateur("NomExplorateur1", plateauJeu);
        inge = new Ingenieur("NomIngenieur1", plateauJeu);
        plateauJeu = new Grille(explo, inge);

    }
        
    public static void main(String[] args){
        new IHMsimple();
    }
    
}
