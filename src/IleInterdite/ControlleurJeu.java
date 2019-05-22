/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import Cartes.CarteInondation;
import Cartes.CarteMonteeDesEaux;
import Cartes.CarteRouge;
import Personnages.Personnage;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author violentt
 */
public class ControlleurJeu {

    /**
     * @param args the command line arguments
     */
    
    //DECLARATION
    private int niveauEau;
    private int numJoueurEnCours;
    private int nombreJoueurDansPartie;
    private Grille grille;
    private ArrayList<Personnage>personnages = new ArrayList<>();
    private ArrayList<CarteRouge>pilecarterouge = new ArrayList<>();
    private ArrayList<CarteRouge>defausecarterouge = new ArrayList<>();
    private ArrayList<CarteInondation>pilecarteinondation = new ArrayList<>();
    private ArrayList<CarteInondation>defausecarteinondation = new ArrayList<>();
    
    //-----------------------------------------------------------------------------------
    
    //METHODES
    
    //Gerer le niveau d'eau
    public int getNiveauEau() {
        return niveauEau;
    }
    
    public void addNiveauEau() {
        niveauEau++;
    }
    
    public CarteRouge PiocherCarteRouge() {
        return pilecarterouge.get(0);
    }
    
    public CarteInondation PiocherCarteInond() {
        return pilecarteinondation.get(0);
    }
    
    public void VerifNbCarte(Personnage perso) {
        
    }
    
    public void MelangeCarteInnondation() {
        //defausecarteinondatio
        Collections.shuffle(defausecarteinondation);
        for (CarteInondation carteinond : defausecarteinondation){
            pilecarteinondation.add(carteinond);
        }
    }
    
    //Gerer le tour de Jeu
    public void TourDeJeu() {
        
        //CODE DE VERIF NB CARTE
        VerifNbCarte(personnages.get(0));
        
        //ACTION NUMERO 1 : FAIRE SES ACTIONS
        int nbaction = 3;
        while(nbaction>0){
            //FaireAction(typeAction);
        }
        //ACTION NUMERO 2 : PIOCHER CARTES ROUGES
        for(int i = 1; i >= 2; i++) {
            CarteRouge cartepioche = this.PiocherCarteRouge();
            
            if(cartepioche.getNom() == "CarteMonteeDesEaux") {
                addNiveauEau();
                if (defausecarteinondation.size()!=0){
                    MelangeCarteInnondation();
                }
                defausecarterouge.add(cartepioche);
            }
            else if(cartepioche.getNom() == "CarteTresor") {
                
            }
        }
        
        int niveau = getNiveauEau();
        
        //ACTION NUMERO 3 : PIOCHER CARTES INNONDATION
        int i=0;
        while(niveau>i){
            i++;
            CarteInondation cartepiocheinond = this.PiocherCarteInond();
            grille.AugmenterInnondation(cartepiocheinond.getNom());
           // AugmenterInondation(cartepiocheinond);
        }
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        
        
        
    }
    
}
