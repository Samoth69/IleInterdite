/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;
import IleInterdite.Grille;
import Personnages.Personnage;
import Personnages.Explorateur;
import Personnages.Ingenieur;
import Cartes.CarteInondation;
import Enumerations.TypeEnumCouleurPion;
import java.util.ArrayList;
/**
 *
 * @author mariottp
 */
public class IHMsimple {

    Grille plateauJeu;  
    Explorateur explo;
    Ingenieur inge;
            
    public IHMsimple(){
        
        explo = new Explorateur("explo", plateauJeu);
        inge = new Ingenieur("inge", plateauJeu);
        plateauJeu = new Grille(inge, explo);
    }
    
    public static void main(String[] args){
        new IHMsimple();
    }
    
}
