/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import Cartes.CarteInondation;
import Cartes.CarteMonteeDesEaux;
import Cartes.CarteRouge;
import Enumerations.TypeEnumInondation;
import Enumerations.TypeEnumMessage;
import Enumerations.TypeEnumTresors;
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
public class ControleurJeuSecondaire implements Observe{

    /**
     * @param args the command line arguments
     */
    
    //DECLARATION
    private int niveauEau;
    private int numJoueurEnCours;
    private int nombreJoueurDansPartie;
    private double nombreAction;
    private Grille grille;    
    
    //  Variables qui indiques si les tresors on etait pris ou non
    private boolean pierreSacre, statueZephyr, cristalArdent, caliceOnde;
    
    private ArrayList<Personnage>personnages = new ArrayList<>();
    private ArrayList<CarteRouge>pileCarteRouge = new ArrayList<>();
    private ArrayList<CarteRouge>defauseCarteRouge = new ArrayList<>();
    private ArrayList<CarteInondation>pileCarteInondation = new ArrayList<>();
    private ArrayList<CarteInondation>defauseCarteInondation = new ArrayList<>();
    
    //-----------------------------------------------------------------------------------
    
    //METHODES
/*
    public ControleurJeuSecondaire(int nbJoueur) {
        if (nbJoueur < 2 || nbJoueur > 4) {
            throw new Error("Le nombre de joueur doit être compris entre 2 et 4 (inclus)");
        }
        nombreJoueurDansPartie = nbJoueur;
        personnages.addAll(getPersonnagesDebutDePartie(nombreJoueurDansPartie));
        grille = new Grille(personnages);
    }*/
    
    public ControleurJeuSecondaire(ArrayList<Personnage> perso, int niveauEau) {
        if (perso.size() < 2 || perso.size() > 4) {
            throw new Error("Le nombre de joueur doit être compris entre 2 et 4 (inclus)");
        }
        this.niveauEau = niveauEau;
        nombreJoueurDansPartie = perso.size();
        personnages = perso;
        grille = new Grille(personnages);
        pileCarteInondation = grille.getListCarteInondation();
        Collections.shuffle(pileCarteInondation);
        
        for (int i = 0; i <= 5; i++) {
            CarteInondation ci = PiocherCarteInond();
            augementerInondation(ci.getNom());
        } 
        
        //  Initialise les variables pour indiquer qu'aucun tresor n'est possédé 
        pierreSacre = false;
        statueZephyr = false;
        cristalArdent = false;
        caliceOnde = false;
        
        //  initialise nombre d'action
        nombreAction = 3;
    }
    
    //Obtient les personnages pour démararer la partie.
    //renvoie une liste contenant de NOUVEAU joueurs avec tous un role différent
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
    
    public void deplacerJoueurEnCour(Tuile newPos) {
        if (nombreAction != Math.round(nombreAction)) { //si le nombre d'action est décimal on le rend entier
            nombreAction = (int)nombreAction;
        }
        personnages.get(numJoueurEnCours).deplacement(newPos);
        decrementAction();
    }

    public void recupererTresor(Personnage personnage){
      int QuatreCartesTresors=0;
    
      if(personnage.getEmplacement().getTresor() != TypeEnumTresors.AUCUN){
            
          for(int i=0; i<personnage.getCartes().size(); i++){
                 if(personnage.getCartes().get(i).getTypeTresor().contentEquals(personnage.getEmplacement().getTresor().toString())){
                     QuatreCartesTresors = QuatreCartesTresors +1;
                 }
             }
             
            if(QuatreCartesTresors>=4){
                   for(int i=0; i<grille.getListTuile().size(); i++){
                          if(grille.getListTuile().get(i).getNom().equals(personnage.getEmplacement().getNom())){

                              switch(grille.getListTuile().get(i).getTresor()){
                                  case LUNE : pierreSacre=true;
                                              break;

                                  case LION : statueZephyr=true;
                                              break;

                                  case FEU : cristalArdent=true;
                                                 break;

                                  case TROPHEE :caliceOnde=true;
                                                break;

                                  default:        System.out.println("REVERIFIER CONTROLLEURJEU2");;    


                                          }
                    
                    
                                grille.getListTuile().get(i).setTresor(TypeEnumTresors.AUCUN);
                         }
                     }
             } else{
                 System.out.println("Vous n'avez pas 4 cartes correspondant à cet trésor");
            } 
            
     }else{
            System.out.println("Aucun trésor sur la tuile actuelle");
       }
    }
    
    public Personnage getJoueurEntrainDeJouer() {
        return personnages.get(numJoueurEnCours);
    }
    
    public int getJoueurNum() {
        return numJoueurEnCours;
    }
    
    public void assecher(Tuile t) {
        t.reduireInondation();
        if (getJoueurEntrainDeJouer() instanceof Ingenieur) {
            decrementAction(0.5);
        } else {
            decrementAction();
        }
    }
    
    //cherche dans un arraylist si num est trouvé, renvoie true. sinon renvoie faux.
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
    
    //renvoie l'objet Grille
    public Grille getIle() {
        return grille;
    }
    
    //renvoie le nom du joueur qui est actuellement entrain de jouer
    public String getNomJoueur() {
        //notifierObservateur(new Message(TypeEnumMessage.ACTION));
        return personnages.get(numJoueurEnCours).getNom();
    }  
    
    //Get le niveau d'eau
    public int getNiveauEau() {
        return niveauEau;
    }
    
    //récupère le nombre de carte inondation à piocher en fonction de l'état de la frise inondation
    public int getNombreCarteInondationAPiocher() {
        if (niveauEau < 2) {
            return 2;
        } else if (niveauEau >=2 && niveauEau < 5) {
            return 3;
        } else if (niveauEau >=5 && niveauEau < 7) {
            return 4;
        } else { //niveauEau >= 9
            return 5;
        }
    }
    
    public CarteRouge getCarteSelectionne() {
        //return carteselectionne;
        return null;
    }
    
    public double getNbActionRestante() {
        return nombreAction;
    }
    
    public CarteRouge PiocherCarteRouge() {
        if(pileCarteRouge.isEmpty())
        {
            pileCarteRouge.addAll(defauseCarteRouge);
            defauseCarteRouge.clear();
            MelangeCarteRouge();
        }
        CarteRouge cr = pileCarteRouge.get(pileCarteRouge.size() - 1);
        pileCarteRouge.remove(pileCarteRouge.size() - 1);
        return cr;
    }
    
    public CarteInondation PiocherCarteInond() {
        if(pileCarteInondation.isEmpty())
        {
            pileCarteInondation.addAll(defauseCarteInondation);
            defauseCarteInondation.clear();
            MelangeCarteInnondation();
        }
        CarteInondation ci = pileCarteInondation.get(pileCarteInondation.size() - 1);
        pileCarteInondation.remove(pileCarteInondation.size() - 1);
        return ci;
    }
    
    public void DefausserCarte(CarteInondation ci) {
        defauseCarteInondation.add(ci);
    }
    
    public void DefausserCarte(CarteRouge cr) {
        defauseCarteRouge.add(cr);
    }
    
    public Tuile[][] getGrille() {
        return grille.getTuiles();
    }
    
    public ArrayList<Tuile> getListeCarte() {
        return grille.getListTuile();
    }
    
    public void VerifNbCarte(Personnage perso) {
        if (personnages.get(numJoueurEnCours).getCartes().size()>5){
            while (personnages.get(numJoueurEnCours).getCartes().size()>5) {
                CarteRouge cartechoisi = getCarteSelectionne();
                defauseCarteRouge.add(cartechoisi);
            }
        }
    }
    
    public void passerJoueurSuivant() {
        getJoueurEntrainDeJouer().passageJoueurSuivant();
        numJoueurEnCours++;
        if (nombreJoueurDansPartie <= numJoueurEnCours){
            numJoueurEnCours = 0;
            NouveauTourDeJeu();
        }
        nombreAction = 3;
        notifierObservateur(new Message(TypeEnumMessage.JOUEUR_SUIVANT));
        verifFinDePartie();
    }
    
    private void NouveauTourDeJeu() {
        ArrayList<CarteInondation> aci = new ArrayList<>();
        for (int i = 0; i < getNombreCarteInondationAPiocher(); i++) {
            CarteInondation ci = PiocherCarteInond();
            aci.add(ci);
            grille.AugmenterInnondation(ci.getNom());
            DefausserCarte(ci);
        }
        notifierObservateur(new Message(TypeEnumMessage.PIOCHE_CARTE_INONDATION, aci));
        notifierObservateur(new Message(TypeEnumMessage.NOUVEAU_TOUR));
        //System.out.println("");
        
    }
    
    private void MelangeCarteRouge() {
        Collections.shuffle(pileCarteRouge);
    }
    
    private void MelangeCarteInnondation() {
        //defausecarteinondatio
        Collections.shuffle(pileCarteInondation);
    }
    
    private void decrementAction(){
        if (nombreAction != Math.round(nombreAction)) {
            nombreAction = Math.round(nombreAction);
        }
        nombreAction--;
        if(nombreAction <= 0)
        {
            passerJoueurSuivant();
        }
        decrementActionAfterCheck();
    }
    
    private void decrementAction(double reducVal){
        nombreAction -= reducVal;
        if(nombreAction <= 0)
        {
            passerJoueurSuivant();
        }
        decrementActionAfterCheck();
    }
    
    //vérification après avoir enlever un certain nombre d'action dispo
    private void decrementActionAfterCheck() {
        if (nombreAction < 1.0 && getJoueurEntrainDeJouer().getTuileQuiPeutSecher().isEmpty()) {
            ArrayList<String> t = new ArrayList<>();
            t.add("Le joueur ne peux plus assécher de tuile.");
            notifierObservateur(new Message(TypeEnumMessage.HISTORIQUE, t));
            passerJoueurSuivant();
        }
    }
    
    //doit être privée. est mis en public pour débug
    public void augmenterNiveauEau() {
        niveauEau++;
        notifierObservateur(new Message(TypeEnumMessage.CHANGEMENT_NIVEAU_EAU));
        verifFinDePartie();
    }
    
    //cherche une tuile et si il la trouve, augement sont inondation
    public void augementerInondation(String s) {
        for (Tuile t : getListeCarte()) {
            if (t.getNom().equals(s)) {
                t.augmenterInondation();
                return;
            }
        }
    }
    
    //cherche une tuile et si il la trouve, reduit sont inondation
    public void reduireInondation(String s) {
        for (Tuile t : getListeCarte()) {
            if (t.getNom().equals(s)) {
                t.reduireInondation();
                return;
            }
        }
    }
    
    private void actionDebutTour() {
        //CODE DE VERIF NB CARTE
        VerifNbCarte(personnages.get(numJoueurEnCours));
    }
    
    //renvoie le nombre de joueur dans la partie
    public int getNombreJoueurDansPartie() {
        return nombreJoueurDansPartie;
    }
    
    //Gerer le tour de Jeu
    public void TourDeJeu() {
        verifFinDePartie();
        
        
        //ACTION NUMERO 1 : FAIRE SES ACTIONS
        int nbaction = 3;
        while(nbaction>0){
            //FaireAction(typeAction);
        }
        //ACTION NUMERO 2 : PIOCHER CARTES ROUGES
        for(int i = 1; i >= 2; i++) {
            CarteRouge cartepioche = this.PiocherCarteRouge();
            pileCarteRouge.remove(pileCarteRouge.size()-1);
            
            if(cartepioche.getNom() == "CarteMonteeDesEaux") {
                augmenterNiveauEau();
                if (defauseCarteInondation.size()!=0){
                    MelangeCarteInnondation();
                }
                defauseCarteRouge.add(cartepioche);
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
            defauseCarteInondation.add(cartepiocheinond);
        }
    }
    
    public void partieGagne(){
        int nbJoueurSurHeliport = 0;
        
        //  Si tous les tresors ont été récupérés
        if(pierreSacre == true && caliceOnde == true && cristalArdent == true && statueZephyr == true)
        {
            for(int i = 0; i < personnages.size(); i++)
            {
                //  Compte le nombre de joueurs sur l'heliport
                if(personnages.get(i).getEmplacement().getNom() == "Heliport")
                {
                    nbJoueurSurHeliport++;
                }
            }
            //  Si tous les joueurs sont sur l'heliport
            //  On regarde si un des joueurs a une carte helicoptere
            if(nbJoueurSurHeliport == personnages.size())
            {
                for(int i = 0; i < personnages.size(); i++)
                {
                    ArrayList<CarteRouge> lesCartesDuJoueur = personnages.get(i).getCartes();
                    
                    for(int j = 0; j < lesCartesDuJoueur.size(); j++)
                    {
                        //  Si un joueur a une carte helicoptere, partie gagne
                        if(lesCartesDuJoueur.get(j).getDescription() == "Hélicopère")
                        {
                            notifierObservateur(new Message(TypeEnumMessage.PARTIE_GAGNE, "Partie Gagnée"));
                        }
                    }
                }
            }
        }
    }
    
    public void verifFinDePartie(){
        //  Si le niveau d'eau est au max, alors fin de partie
        if(niveauEau >= 10)
        {
            notifierObservateur(new Message(TypeEnumMessage.FIN_PARTIE, "Niveau d'eau maximum atteint"));
        }
        
        //  ------------------------------------------------------
        
        //  La boucle regarde si un personnage est mort
        for(int i = 0; i < personnages.size(); i++)
        {
            if(personnages.get(i).getDeplacements().isEmpty())
            {
                notifierObservateur(new Message(TypeEnumMessage.FIN_PARTIE, personnages.get(i).getNom()+" est mort"));
            }
        }
        
        //  -------------------------------------------------------
        
        //  Verifie si l'heliport n'est pas Inondé, sinon fin de partie
        for(int i = 0; i < grille.getListTuile().size(); i++)
        {
            if(grille.getListTuile().get(i).getNom() == "Heliport")
            {
                if(grille.getListTuile().get(i).getInondation() == TypeEnumInondation.INONDE)
                {
                    notifierObservateur(new Message(TypeEnumMessage.FIN_PARTIE, "Heliport Inondé"));
                }
                break; // vas peut-etre poser probleme suite au notifierObservateur
            }
        }
        
        //  -------------------------------------------------------
        
        
        //  Verifie
        for(int i = 0; i < grille.getListTuile().size(); i++)
        {
            if(grille.getListTuile().get(i).getNom() == "La Caverne des Ombres" && grille.getListTuile().get(i).getInondation() == TypeEnumInondation.INONDE && cristalArdent == false)
            {
                for(int j = 0; j < grille.getListTuile().size(); j++)
                {
                    if(grille.getListTuile().get(i).getNom() == "La Caverne du Brasier" && grille.getListTuile().get(i).getInondation() == TypeEnumInondation.INONDE)
                    {
                        notifierObservateur(new Message(TypeEnumMessage.FIN_PARTIE, "Les 2 cases caverne sont inondé et les trésors avec"));
                        break;  //  peut-etre problematique
                    }
                }
            }
            
            if(grille.getListTuile().get(i).getNom() == "Le Temple du Soleil" && grille.getListTuile().get(i).getInondation() == TypeEnumInondation.INONDE && pierreSacre == false)
            {
                for(int j = 0; j < grille.getListTuile().size(); j++)
                {
                    if(grille.getListTuile().get(i).getNom() == "Le Temple de La Lune" && grille.getListTuile().get(i).getInondation() == TypeEnumInondation.INONDE)
                    {
                        notifierObservateur(new Message(TypeEnumMessage.FIN_PARTIE, "Les 2 cases Temple sont inondé et les trésors avec"));
                        break;  //  peut-etre problematique
                    }
                }
            }
            
            if(grille.getListTuile().get(i).getNom() == "Le Palais de Corail" && grille.getListTuile().get(i).getInondation() == TypeEnumInondation.INONDE && caliceOnde == false)
            {
                for(int j = 0; j < grille.getListTuile().size(); j++)
                {
                    if(grille.getListTuile().get(i).getNom() == "Le Palais des Marees" && grille.getListTuile().get(i).getInondation() == TypeEnumInondation.INONDE)
                    {
                        notifierObservateur(new Message(TypeEnumMessage.FIN_PARTIE, "Les 2 cases palais sont inondé et les trésors avec"));
                        break;  //  peut-etre problematique
                    }
                }
            }
            
            if(grille.getListTuile().get(i).getNom() == "Le Jardin des Hurlements" && grille.getListTuile().get(i).getInondation() == TypeEnumInondation.INONDE && statueZephyr == false)
            {
                for(int j = 0; j < grille.getListTuile().size(); j++)
                {
                    if(grille.getListTuile().get(i).getNom() == "Le Jardin des Murmures" && grille.getListTuile().get(i).getInondation() == TypeEnumInondation.INONDE)
                    {
                        notifierObservateur(new Message(TypeEnumMessage.FIN_PARTIE, "Les 2 cases jardins sont inondé et les trésors avec"));
                        break;  //  peut-etre problematique
                    }
                }
            }
            
        }
        
    }
    

    private Observateur observateur;    
    public void addObservateur(Observateur o) {
        this.observateur = o;
    }
    
    public void notifierObservateur(Message m) {
        if (observateur != null) {
            observateur.traiterMessage(m);
        }
    } 
    
    public ArrayList<Personnage> getPerso(){
        return personnages;
    }
}
