/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import Cartes.CarteInondation;
import Cartes.CarteMonteeDesEaux;
import Cartes.CarteRouge;
import Personnages.Explorateur;
import Personnages.Ingenieur;
import Personnages.Messager;
import Personnages.Navigateur;
import Personnages.Personnage;
import Personnages.Pilote;
import Personnages.Plongeur;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

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
    private CarteRouge carteselectionne;
    private ArrayList<Personnage>personnages = new ArrayList<>();
    private ArrayList<CarteRouge>pilecarterouge = new ArrayList<>();
    private ArrayList<CarteRouge>defausecarterouge = new ArrayList<>();
    private ArrayList<CarteInondation>pilecarteinondation = new ArrayList<>();
    private ArrayList<CarteInondation>defausecarteinondation = new ArrayList<>();
    
    //-----------------------------------------------------------------------------------
    
    //METHODES

    public ControlleurJeu() {
        nombreJoueurDansPartie = 3;
        personnages.addAll(getPersonnagesDebutDePartie(nombreJoueurDansPartie));
        grille = new Grille(personnages);
        for (Personnage p : personnages) {
            System.out.println(p.getNom());
            Tuile t = p.getEmplacement();
            System.out.println(t.getNom() + "\t" + t.getX() + "\t" + t.getY());
        }
    }
    
    private ArrayList<Personnage> getPersonnagesDebutDePartie(int nbJoueurs) {
        ArrayList<Personnage> p = new ArrayList<>();
        ArrayList<Integer> PersonnageDejaUtilise = new ArrayList<>();
        
        for (int i = 0; i < nbJoueurs; i++) {
            int rnd = 0;
            
            while (rnd == 0 || search(PersonnageDejaUtilise, rnd)) {
                rnd = ThreadLocalRandom.current().nextInt(1, 6 + 1);
            }
            PersonnageDejaUtilise.add(rnd);
            
            switch (rnd) {
                case 1:
                    p.add(new Explorateur("Pseudo Explo", grille));
                    break;
                case 2:
                    p.add(new Ingenieur("Pseudo Inge", grille));
                    break;
                case 3:
                    p.add(new Messager("Pseudo Messager", grille));
                    break;
                case 4:
                    p.add(new Navigateur("Pseudo Nav", grille));
                    break;
                case 5:
                    p.add(new Pilote("Pseudo Pilote", grille));
                    break;
                case 6:
                    p.add(new Plongeur("Pseudo Plongeur", grille));
                    break;  
            }
        }
        
        return p;
    }
    
    private boolean search(ArrayList<Integer> ar, int num) {
        boolean exist = false;
        for(int i=0; i<ar.size();i++){
            if(ar.get(i) == num){
                exist=true;
                break;
            }
        }

        if(exist) {
            return true;
        } else {
            return false;
        }
    }
    
    
    //Gerer le niveau d'eau
    public int getNiveauEau() {
        return niveauEau;
    }
    
    public CarteRouge getCarteSelectionne() {
        return carteselectionne;
    }
    
    public void addNiveauEau() {
        niveauEau++;
    }
    
    public CarteRouge PiocherCarteRouge() {
        return pilecarterouge.get(pilecarterouge.size()-1);
    }
    
    public CarteInondation PiocherCarteInond() {
        return pilecarteinondation.get(pilecarteinondation.size()-1);
    }
    
    public void VerifNbCarte(Personnage perso) {
        if (personnages.get(numJoueurEnCours).getCartes().size()>5){
            while (personnages.get(numJoueurEnCours).getCartes().size()>5) {
                CarteRouge cartechoisi = getCarteSelectionne();
                defausecarterouge.add(cartechoisi);
            }
        }
    }
    
    public int passerJoueurSuivant() {
        numJoueurEnCours++;
        if (nombreJoueurDansPartie > numJoueurEnCours){
            numJoueurEnCours = 0;
        }      
        return numJoueurEnCours;
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
        VerifNbCarte(personnages.get(numJoueurEnCours));
        
        //ACTION NUMERO 1 : FAIRE SES ACTIONS
        int nbaction = 3;
        while(nbaction>0){
            //FaireAction(typeAction);
        }
        //ACTION NUMERO 2 : PIOCHER CARTES ROUGES
        for(int i = 1; i >= 2; i++) {
            CarteRouge cartepioche = this.PiocherCarteRouge();
            pilecarterouge.remove(pilecarterouge.size()-1);
            
            if(cartepioche.getNom() == "CarteMonteeDesEaux") {
                addNiveauEau();
                if (defausecarteinondation.size()!=0){
                    MelangeCarteInnondation();
                }
                defausecarterouge.add(cartepioche);
            }
            else if(cartepioche.getNom() == "CarteTresor") {
                personnages.get(numJoueurEnCours).addCarte(cartepioche);
            }
        }
        
        int niveau = getNiveauEau();
        
        //ACTION NUMERO 3 : PIOCHER CARTES INNONDATION
        int i=0;
        while(niveau>i){
            i++;
            CarteInondation cartepiocheinond = this.PiocherCarteInond();
            grille.AugmenterInnondation(cartepiocheinond.getNom());
            defausecarteinondation.add(cartepiocheinond);
        }
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        new ControlleurJeu();
        
        
        
        
    }
    
}
