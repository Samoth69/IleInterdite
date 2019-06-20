/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import Cartes.CarteAction;
import Cartes.CarteInondation;
import Cartes.CarteMonteeDesEaux;
import Cartes.CarteRouge;
import Cartes.CarteTresor;
import Enumerations.TypeEnumCarteAction;
import Enumerations.TypeEnumInondation;
import Enumerations.TypeEnumMessage;
import Enumerations.TypeEnumTresors;
import IHM.VuDefausse;
import Personnages.Explorateur;
import Personnages.Ingenieur;
import Personnages.Messager;
import Personnages.Navigateur;
import Personnages.Personnage;
import Personnages.Pilote;
import Personnages.Plongeur;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author violentt
 */
public class ControleurJeuSecondaire implements Observe {

    /**
     * @param args the command line arguments
     */
    //DECLARATION
    private int niveauEau;
    private int numJoueurEnCours;
    private int nombreJoueurDansPartie;
    private double nombreAction;
    private Grille grille;
    private boolean demarrage = true; //est vrai pendant que le constructeur travaille. devient définitivement false après la fin du constructeur
    private boolean demoMode = false; //indique si le jeu est en mode demo, désactive tout l'aléatoire du jeu
    
    //  Variables qui indiques si les tresors on etait pris ou non
    private boolean pierreSacre, statueZephyr, cristalArdent, caliceOnde;
    
    // Media Player pour le son
    private MediaPlayer mediaPlayer;
    
    
    private ArrayList<Personnage> personnages = new ArrayList<>();
    private ArrayList<CarteRouge> pileCarteRouge = new ArrayList<>();
    private ArrayList<CarteRouge> defausseCarteRouge = new ArrayList<>();
    private ArrayList<CarteInondation> pileCarteInondation = new ArrayList<>();
    private ArrayList<CarteInondation> defausseCarteInondation = new ArrayList<>();

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
    public ControleurJeuSecondaire(ArrayList<Personnage> perso, int niveauEau, boolean demo, ArrayList<CarteRouge> cr, ArrayList<CarteInondation> ci) {
        if (demo) {
            System.out.println("Mode scénario activé");
            demoMode = true;
        }
        this.niveauEau = niveauEau;
        nombreJoueurDansPartie = perso.size();
        personnages = perso;
        grille = new Grille(personnages, demo, ci);

        if (demo && ci != null) {
            pileCarteInondation = ci;
        } else {
            pileCarteInondation = grille.getListCarteInondation();
        }
        if (demo && cr != null) {
            pileCarteRouge = cr;
        } else {
            pileCarteRouge = getListCarteRouge();
        }

        if (!demo) {
            Collections.shuffle(pileCarteInondation);
            Collections.shuffle(pileCarteRouge);
        }
        for (int i = 0; i <= 5; i++) {
            CarteInondation c = PiocherCarteInond();
            augementerInondation(c.getNom());
            defausserCarte(c);
        }
        for (int i = 1; i <= 2; i++) {
            for (Personnage p : perso) {
                CarteRouge c = PiocherCarteRouge();
                if (c != null) {
                    p.addCarte(c);
                }
            }
        }

        //  Initialise les variables pour indiquer qu'aucun tresor n'est possédé 
        pierreSacre = false;
        statueZephyr = false;
        cristalArdent = false;
        caliceOnde = false;

        //  initialise nombre d'action
        nombreAction = 3;
        demarrage = false;
    }

    /* 
    public ControleurJeuSecondaire(ArrayList<Personnage> perso, int niveauEau) {
        if (perso.size() < 2 || perso.size() > 4) {
            throw new Error("Le nombre de joueur doit être compris entre 2 et 4 (inclus)");
        }
        this.niveauEau = niveauEau;
        nombreJoueurDansPartie = perso.size();
        personnages = perso;
        grille = new Grille(personnages, false);
        pileCarteInondation = grille.getListCarteInondation();
        pileCarteRouge = getListCarteRouge();
        Collections.shuffle(pileCarteInondation);
        Collections.shuffle(pileCarteRouge);
        
        for (int i = 0; i <= 5; i++) {
            CarteInondation ci = PiocherCarteInond();
            augementerInondation(ci.getNom());
            ControleurJeuSecondaire.this.defausserCarte(ci);
        } 
        for (int i = 1; i <= 2; i++) {
            for (Personnage p : perso) {
                CarteRouge cr = PiocherCarteRouge();
                if (cr != null) {
                    p.addCarte(cr);
                }
            }
        }
        
        //  Initialise les variables pour indiquer qu'aucun tresor n'est possédé 
        pierreSacre = false;
        statueZephyr = false;
        cristalArdent = false;
        caliceOnde = false;
        
        //  initialise nombre d'action
        nombreAction = 3;
        demarrage = false;
    }
     */
    
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
                    p.add(new Explorateur("Pseudo Explorateur", grille));
                    break;
                case 2:
                    p.add(new Ingenieur("Pseudo Ingénieur", grille));
                    break;
                case 3:
                    p.add(new Messager("Pseudo Messager", grille));
                    break;
                case 4:
                    p.add(new Navigateur("Pseudo Navigateur", grille));
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
    
    public int getNumJoueur(String nomJoueur) {
        for (int i = 0; i < personnages.size(); i++) {
            if (personnages.get(i).getNom().toLowerCase().equals(nomJoueur.toLowerCase())) {
                return i;
            }
        }
        return -1;
    }
    
    public void deplacerJoueur(Personnage perso, Tuile newPos) {
        personnages.get(getNumJoueur(perso.getNom())).deplacement(newPos);
        notifierObservateur(new Message(TypeEnumMessage.HISTORIQUE, "Déplacement de " + getJoueurEntrainDeJouer().getNom() + " sur " + newPos.getNom() + "."));
        notifierObservateur(new Message(TypeEnumMessage.UPDATE_GUI));
        partieGagne();  // regarde si la partie est gagnée
    }

    public void deplacerJoueurEnCours(Tuile newPos) {
        if (nombreAction != Math.round(nombreAction)) { //si le nombre d'action est décimal on le rend entier
            nombreAction = (int) nombreAction;
        }
        personnages.get(numJoueurEnCours).deplacement(newPos);
        decrementAction();
        notifierObservateur(new Message(TypeEnumMessage.HISTORIQUE, "Déplacement de " + getJoueurEntrainDeJouer().getNom() + " sur " + newPos.getNom() + "."));
        partieGagne();  // regarde si la partie est gagnée
    }

    //recupère le trésor à l'emplacement donnée
    //force: force la récupération du trésor, ignore les cartes nécéssaire et l'emplacement du joueur. NE DECREMENTE PAS LES ACTIONS RESTANTE
    public void recupererTresor(Tuile emplacementJoueur, boolean force) {
        if (emplacementJoueur != null) {
            int nbCarteTresor = 0;
            ArrayList<CarteRouge> carteUtilise = new ArrayList<>();

            //  Si il y a bien un tresor
            if (emplacementJoueur.getTresor() != TypeEnumTresors.AUCUN) {
                //  Compte le nombre de carte tresor du joueur correspondant au tresor de la case
                for (int i = 0; i < getJoueurEntrainDeJouer().getCartes().size(); i++) {
                    if (getJoueurEntrainDeJouer().getCartes().get(i).getTypeTresor() == emplacementJoueur.getTresor()) {
                        nbCarteTresor++;
                    }
                }
                //  Si le joueur a 4 carte du tresor de la case alors...
                if (nbCarteTresor >= 4 || force) {
                    switch (emplacementJoueur.getTresor()) {
                        case FEU:   //si le tresor = feu
                            cristalArdent = true;   //tresor est possédé
                            notifierObservateur(new Message(TypeEnumMessage.HISTORIQUE, "Le trésor Cristal Ardent a été récupéré."));
                            //message qui indique la tuile où le tresor doit etre supprimé
                            notifierObservateur(new Message(TypeEnumMessage.RM_TRESOR, emplacementJoueur));

                            for (CarteRouge i : getJoueurEntrainDeJouer().getCartes()) {
                                if (i.getTypeTresor() == TypeEnumTresors.FEU) {
                                    if (nbCarteTresor <= 0) {
                                        break;
                                    }

                                    carteUtilise.add(i);

                                    nbCarteTresor--;
                                }
                            }
                            if (!force) {
                                getJoueurEntrainDeJouer().getCartes().removeAll(carteUtilise);
                                defausserCarte(carteUtilise);
                            }
                            

                            break;
                        case LION:  // idem
                            statueZephyr = true;
                            notifierObservateur(new Message(TypeEnumMessage.HISTORIQUE, "Le trésor Statue de Zephyr a été récupéré."));
                            notifierObservateur(new Message(TypeEnumMessage.RM_TRESOR, emplacementJoueur));

                            for (CarteRouge i : getJoueurEntrainDeJouer().getCartes()) {
                                if (i.getTypeTresor() == TypeEnumTresors.LION) {
                                    if (nbCarteTresor <= 0) {
                                        break;
                                    }

                                    carteUtilise.add(i);

                                    nbCarteTresor--;
                                }
                            }
                            if (!force) {
                                getJoueurEntrainDeJouer().getCartes().removeAll(carteUtilise);
                                defausserCarte(carteUtilise);
                            }
                            break;
                        case LUNE:  //idem
                            pierreSacre = true;
                            notifierObservateur(new Message(TypeEnumMessage.HISTORIQUE, "Le trésor Pierre Sacré a été récupéré."));
                            notifierObservateur(new Message(TypeEnumMessage.RM_TRESOR, emplacementJoueur));

                            for (CarteRouge i : getJoueurEntrainDeJouer().getCartes()) {
                                if (i.getTypeTresor() == TypeEnumTresors.LUNE) {
                                    if (nbCarteTresor <= 0) {
                                        break;
                                    }

                                    carteUtilise.add(i);

                                    nbCarteTresor--;
                                }
                            }
                            if (!force) {
                                getJoueurEntrainDeJouer().getCartes().removeAll(carteUtilise);
                                defausserCarte(carteUtilise);
                            }
                            break;
                        case TROPHEE:   //idem
                            caliceOnde = true;
                            notifierObservateur(new Message(TypeEnumMessage.HISTORIQUE, "Le trésor Calice des Ondes a été récupéré."));
                            notifierObservateur(new Message(TypeEnumMessage.RM_TRESOR, emplacementJoueur));

                            for (CarteRouge i : getJoueurEntrainDeJouer().getCartes()) {
                                if (i.getTypeTresor() == TypeEnumTresors.TROPHEE) {
                                    if (nbCarteTresor <= 0) {
                                        break;
                                    }

                                    carteUtilise.add(i);

                                    nbCarteTresor--;
                                }
                            }
                            if (!force) {
                                getJoueurEntrainDeJouer().getCartes().removeAll(carteUtilise);
                                defausserCarte(carteUtilise);
                            }
                            break;
                    }
                    //  retire le tresor de la case
                    emplacementJoueur.setTresor(TypeEnumTresors.AUCUN);

                    if (!force) {
                        decrementAction();
                    }
                    notifierObservateur(new Message(TypeEnumMessage.UPDATE_GUI, emplacementJoueur));
                }
            } else {
                notifierObservateur(new Message(TypeEnumMessage.HISTORIQUE, "Il n'est pas possible de récupérer le trésor."));
            }
        }
    }

    public Personnage getJoueurEntrainDeJouer() {
        return personnages.get(numJoueurEnCours);
    }

    public int getJoueurNum() {
        return numJoueurEnCours;
    }
    
    public boolean isDemoMode() {
        return demoMode;
    }

    public void assecher(Tuile t) {
        t.reduireInondation();
        if (getJoueurEntrainDeJouer() instanceof Ingenieur) {
            decrementAction(0.5);
        } else {
            decrementAction();
        }

        notifierObservateur(new Message(TypeEnumMessage.HISTORIQUE, "La case " + t.getNom() + " a été asséchée."));
    }

    //cherche dans un arraylist si num est trouvé, renvoie true. sinon renvoie faux.
    private boolean search(ArrayList<Integer> ar, int num) {
        boolean exist = false;
        for (int i = 0; i < ar.size(); i++) {
            if (ar.get(i) == num) {
                exist = true;
                break;
            }
        }

        if (exist) {
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
        } else if (niveauEau >= 2 && niveauEau < 5) {
            return 3;
        } else if (niveauEau >= 5 && niveauEau < 7) {
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

    public Tuile searchTuile(String nom) {
        for (Tuile t : grille.getListTuile()) {
            if (t.getNom().toLowerCase().equals(nom.toLowerCase())) {
                return t;
            }
        }
        return null;
    }

    //pioche une carte rouge.
    //RENVOIE NULL SI LA CARTE PIOCHER EST UNE MONTEE DES EAUX
    public CarteRouge PiocherCarteRouge() {
        if (pileCarteRouge.isEmpty()) {
            pileCarteRouge.addAll(defausseCarteRouge);
            defausseCarteRouge.clear();
            MelangeCarteRouge();
        }
        CarteRouge cr = pileCarteRouge.get(pileCarteRouge.size() - 1);
        pileCarteRouge.remove(pileCarteRouge.size() - 1);
        if (cr instanceof CarteMonteeDesEaux) {
            if (demarrage) {
                if (demoMode) {
                    defausseCarteRouge.add(cr);
                } else {
                    pileCarteRouge.add(cr);
                }
                MelangeCarteRouge();
                return PiocherCarteRouge();
            } else {
                augmenterNiveauEau();
                viderDefausseCarteInondation();
                defausseCarteRouge.add(cr);
                return null;
            }
        } else {
            return cr;
        }
    }

    public CarteInondation PiocherCarteInond() {
        if (pileCarteInondation.isEmpty()) {
            viderDefausseCarteInondation();
        }
        CarteInondation ci = pileCarteInondation.get(pileCarteInondation.size() - 1);
        pileCarteInondation.remove(pileCarteInondation.size() - 1);
        return ci;
    }

    private void viderDefausseCarteInondation() {
        MelangeDefausseCarteInnondation();
        pileCarteInondation.addAll(defausseCarteInondation);
        defausseCarteInondation.clear();
        for (CarteInondation ci : pileCarteInondation) {
            System.out.println(ci.getNom());
        }
    }

    public void defausserCarte(CarteInondation ci) {
        defausseCarteInondation.add(ci);
    }

    public void defausserCarte(Collection<CarteRouge> cr) {
        defausseCarteRouge.addAll(cr);
    }

    public void defausserCarte(CarteRouge cr) {
        defausseCarteRouge.add(cr);
    }

    public Tuile[][] getGrille() {
        return grille.getTuiles();
    }

    public ArrayList<Tuile> getListeCarte() {
        return grille.getListTuile();
    }

    public void VerifNbCarte(Personnage perso) {
        if (perso.getCartes().size() > 5) {
            VuDefausse vd = new VuDefausse(perso.getCartes(), "Défaussez une carte", perso.getCartes().size() - 5);
            vd.setVisible(true);
            for (CarteRouge cr : vd.getSelectedItems()) {
                perso.removeCarte(cr);
                defausserCarte(cr);
            }
            notifierObservateur(new Message(TypeEnumMessage.UPDATE_GUI));
        }
    }

    public void passerJoueurSuivant() {
        for (int i = 1; i <= 2; i++) {
            CarteRouge cr = PiocherCarteRouge();
            if (cr != null) {
                getJoueurEntrainDeJouer().addCarte(cr);
            }
        }
        getJoueurEntrainDeJouer().passageJoueurSuivant();
        numJoueurEnCours++;
        if (nombreJoueurDansPartie <= numJoueurEnCours) {
            numJoueurEnCours = 0;
        }
        ArrayList<CarteInondation> aci = new ArrayList<>();
        for (int i = 0; i < getNombreCarteInondationAPiocher(); i++) {
            CarteInondation ci = PiocherCarteInond();
            aci.add(ci);
            grille.AugmenterInnondation(ci.getNom());
            ControleurJeuSecondaire.this.defausserCarte(ci);
        }
        notifierObservateur(new Message(TypeEnumMessage.PIOCHE_CARTE_INONDATION, aci));
        nombreAction = 3;
        notifierObservateur(new Message(TypeEnumMessage.JOUEUR_SUIVANT));
        verifFinDePartie();
        partieGagne();
        if(estDansEau(getJoueurEntrainDeJouer()))
        {
            notifierObservateur(new Message(TypeEnumMessage.PERSO_DANS_EAU));
        }
        
        actionDebutTour();
    }

    private void MelangeCarteRouge() {
        if (!demoMode) {
            Collections.shuffle(pileCarteRouge);
        }
        
    }

    private void MelangeDefausseCarteInnondation() {
        //defausecarteinondatio
        if (!demoMode) {
            Collections.shuffle(defausseCarteInondation);
        }
    }

    private void decrementAction() {
        if (nombreAction != Math.round(nombreAction)) {
            nombreAction = Math.round(nombreAction);
        }
        nombreAction--;
        if (nombreAction <= 0) {
            passerJoueurSuivant();
        }
        decrementActionAfterCheck();
    }

    private void decrementAction(double reducVal) {
        nombreAction -= reducVal;
        if (nombreAction <= 0) {
            passerJoueurSuivant();
        }
        decrementActionAfterCheck();
    }

    //vérification après avoir enlever un certain nombre d'action dispo
    private void decrementActionAfterCheck() {
        if (nombreAction < 1.0 && getJoueurEntrainDeJouer().getTuileQuiPeutSecher().isEmpty()) {
            ArrayList<String> t = new ArrayList<>();
            t.add("Le joueur ne peut plus assécher de tuile.");
            notifierObservateur(new Message(TypeEnumMessage.HISTORIQUE, t));
            passerJoueurSuivant();
        }
    }

    //doit être privée. est mis en public pour débug
    public void augmenterNiveauEau() {
        niveauEau++;
        notifierObservateur(new Message(TypeEnumMessage.CHANGEMENT_NIVEAU_EAU));
        notifierObservateur(new Message(TypeEnumMessage.HISTORIQUE, "Le niveau d'eau a augmenté."));
        if (!demarrage) {
            verifFinDePartie();
        }
    }

    //cherche une tuile et si il la trouve, augement sont inondation
    public void augementerInondation(String s) {
        for (Tuile t : getListeCarte()) {
            if (t.getNom().toLowerCase().equals(s.toLowerCase())) {
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

    public boolean getTresorPierreSacre() {
        return pierreSacre;
    }

    public boolean getTresorStatueZephyr() {
        return statueZephyr;
    }

    public boolean getTresorCristalArdent() {
        return cristalArdent;
    }

    public boolean getTresorCaliceOnde() {
        return caliceOnde;
    }

    public boolean estDansEau(Personnage perso){
        if(perso.getEmplacement().getInondation() == TypeEnumInondation.INONDE)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void partieGagne() {
        int nbJoueurSurHeliport = 0;

        //  Si tous les tresors ont été récupérés
        if (pierreSacre == true && caliceOnde == true && cristalArdent == true && statueZephyr == true) {
            for (int i = 0; i < personnages.size(); i++) {
                //  Compte le nombre de joueurs sur l'heliport
                if ("Heliport".equals(personnages.get(i).getEmplacement().getNom())) {
                    nbJoueurSurHeliport++;
                }
            }
            //  Si tous les joueurs sont sur l'heliport
            //  On regarde si un des joueurs a une carte helicoptere
            if (nbJoueurSurHeliport == personnages.size()) {
                for (int i = 0; i < personnages.size(); i++) {
                    ArrayList<CarteRouge> lesCartesDuJoueur = personnages.get(i).getCartes();

                    for (int j = 0; j < lesCartesDuJoueur.size(); j++) {
                        //  Si un joueur a une carte helicoptere, partie gagne
                        if (lesCartesDuJoueur.get(j) instanceof CarteAction) {
                            if (lesCartesDuJoueur.get(j).getTypeCarteAction() == TypeEnumCarteAction.HELICOPTERE) {
                                Media hit = new Media(new File("src/RessourcesJoueur/Victoire.mp3").toURI().toString());
                                mediaPlayer = new MediaPlayer(hit);          //créer le media player
                                mediaPlayer.play();
                                notifierObservateur(new Message(TypeEnumMessage.HISTORIQUE, "Partie Gagnée !"));
                                notifierObservateur(new Message(TypeEnumMessage.PARTIE_GAGNE, "Partie Gagnée"));
                                break;
                            }
                        }

                    }
                }
            }
        }
    }

    public void verifFinDePartie() {
        //  Si le niveau d'eau est au max, alors fin de partie
        if (niveauEau >= 9) {
            Media hit = new Media(new File("src/RessourcesJoueur/JeuPerdu.mp3").toURI().toString());
            mediaPlayer = new MediaPlayer(hit);          //créer le media player
            mediaPlayer.play();
            notifierObservateur(new Message(TypeEnumMessage.HISTORIQUE, "Fin de la partie : le niveau d'eau maximal a été atteint."));
            notifierObservateur(new Message(TypeEnumMessage.FIN_PARTIE, "Niveau d'eau maximum atteint"));
        }

        //  ------------------------------------------------------
        //  La boucle regarde si un personnage est mort
        for (int i = 0; i < personnages.size(); i++) {
            if (personnages.get(i).getDeplacements().isEmpty() && personnages.get(i).getEmplacement().getInondation() == TypeEnumInondation.INONDE) {
                Media hit = new Media(new File("src/RessourcesJoueur/JeuPerdu.mp3").toURI().toString());
                mediaPlayer = new MediaPlayer(hit);          //créer le media player
                mediaPlayer.play();
                notifierObservateur(new Message(TypeEnumMessage.HISTORIQUE, personnages.get(i).getNom() + " est mort."));
                notifierObservateur(new Message(TypeEnumMessage.FIN_PARTIE, personnages.get(i).getNom() + " est mort."));
            }
        }

        //  -------------------------------------------------------
        //  Verifie si l'heliport n'est pas Inondé, sinon fin de partie
        for (int i = 0; i < grille.getListTuile().size(); i++) {
            if ("Heliport".equals(grille.getListTuile().get(i).getNom())) {
                if (grille.getListTuile().get(i).getInondation() == TypeEnumInondation.INONDE) {
                    Media hit = new Media(new File("src/RessourcesJoueur/JeuPerdu.mp3").toURI().toString());
                    mediaPlayer = new MediaPlayer(hit);          //créer le media player
                    mediaPlayer.play();
                    notifierObservateur(new Message(TypeEnumMessage.HISTORIQUE, "Héliport Inondé."));
                    notifierObservateur(new Message(TypeEnumMessage.FIN_PARTIE, "Héliport Inondé."));
                    
                }
                break; // vas peut-etre poser probleme suite au notifierObservateur
            }
        }

        //  -------------------------------------------------------
        //  Verifie si le tresor n'est pas recupéré et que les 2 cases où il est ne sont pas inondées
        for (int i = 0; i < grille.getListTuile().size(); i++) {
            // Si la caverne est inondé et que l'on a pas recupéré le trésor alors...
            if ("La Caverne des Ombres".equals(grille.getListTuile().get(i).getNom()) && grille.getListTuile().get(i).getInondation() == TypeEnumInondation.INONDE && cristalArdent == false) {
                for (int j = 0; j < grille.getListTuile().size(); j++) {
                    //  Si l'autre caverne est inondé et que l'on a pas recupéré le trésor la partie est fini
                    if ("La Caverne du Brasier".equals(grille.getListTuile().get(j).getNom()) && grille.getListTuile().get(j).getInondation() == TypeEnumInondation.INONDE) {
                        Media hit = new Media(new File("src/RessourcesJoueur/JeuPerdu.mp3").toURI().toString());
                        mediaPlayer = new MediaPlayer(hit);          //créer le media player
                        mediaPlayer.play();
                        notifierObservateur(new Message(TypeEnumMessage.HISTORIQUE, "Fin de partie : les deux cases Caverne ainsi que leurs trésors ont été inondées."));
                        notifierObservateur(new Message(TypeEnumMessage.FIN_PARTIE, "les deux cases Caverne ainsi que leurs trésors ont été inondées."));
                        break;  //  peut-etre problematique
                    }
                }
            }
            //  idem precedent
            if ("Le Temple du Soleil".equals(grille.getListTuile().get(i).getNom()) && grille.getListTuile().get(i).getInondation() == TypeEnumInondation.INONDE && pierreSacre == false) {
                for (int j = 0; j < grille.getListTuile().size(); j++) {
                    if ("Le Temple de La Lune".equals(grille.getListTuile().get(j).getNom()) && grille.getListTuile().get(j).getInondation() == TypeEnumInondation.INONDE) {
                        Media hit = new Media(new File("src/RessourcesJoueur/JeuPerdu.mp3").toURI().toString());
                        mediaPlayer = new MediaPlayer(hit);          //créer le media player
                        mediaPlayer.play();
                        notifierObservateur(new Message(TypeEnumMessage.HISTORIQUE, "Fin de partie : les deux cases Temple ainsi que leurs trésors ont été inondées."));
                        notifierObservateur(new Message(TypeEnumMessage.FIN_PARTIE, "les deux cases Temple ainsi que leurs trésors ont été inondées."));
                        break;  //  peut-etre problematique
                    }
                }
            }
            //  idem precedent
            if ("Le Palais de Corail".equals(grille.getListTuile().get(i).getNom()) && grille.getListTuile().get(i).getInondation() == TypeEnumInondation.INONDE && caliceOnde == false) {
                for (int j = 0; j < grille.getListTuile().size(); j++) {
                    if ("Le Palais des Marees".equals(grille.getListTuile().get(j).getNom()) && grille.getListTuile().get(j).getInondation() == TypeEnumInondation.INONDE) {
                        Media hit = new Media(new File("src/RessourcesJoueur/JeuPerdu.mp3").toURI().toString());
                        mediaPlayer = new MediaPlayer(hit);          //créer le media player
                        mediaPlayer.play();
                        notifierObservateur(new Message(TypeEnumMessage.HISTORIQUE, "Fin de partie : les deux cases Palais ainsi que leurs trésors ont été inondées."));
                        notifierObservateur(new Message(TypeEnumMessage.FIN_PARTIE, "les deux cases Palais ainsi que leurs trésors ont été inondées."));
                        break;  //  peut-etre problematique
                    }
                }
            }
            //  idem precedent
            if ("Le Jardin des Hurlements".equals(grille.getListTuile().get(i).getNom()) && grille.getListTuile().get(i).getInondation() == TypeEnumInondation.INONDE && statueZephyr == false) {
                for (int j = 0; j < grille.getListTuile().size(); j++) {
                    if ("Le Jardin des Murmures".equals(grille.getListTuile().get(j).getNom()) && grille.getListTuile().get(j).getInondation() == TypeEnumInondation.INONDE) {
                        Media hit = new Media(new File("src/RessourcesJoueur/JeuPerdu.mp3").toURI().toString());
                        mediaPlayer = new MediaPlayer(hit);          //créer le media player
                        mediaPlayer.play();
                        notifierObservateur(new Message(TypeEnumMessage.HISTORIQUE, "Fin de partie : les deux cases Jardin ainsi que leurs trésors ont été inondées."));
                        notifierObservateur(new Message(TypeEnumMessage.FIN_PARTIE, "les deux cases Jardin ainsi que leurs trésors ont été inondées."));
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

    public ArrayList<Personnage> getPerso() {
        return personnages;
    }

    public void setNbAction(double nbAction) {
        if (nbAction != 0) {
            nombreAction = nbAction;
        } else {
            passerJoueurSuivant();
        }
    }

    public ArrayList<CarteRouge> getListCarteRouge() {
        ArrayList<CarteRouge> out = new ArrayList<>();

        final String chemin = System.getProperty("user.dir") + "/src/RessourcesCarteTresor/";

        out.add(new CarteTresor("Lion", TypeEnumTresors.LION, chemin + "Zephyr.png"));
        out.add(new CarteTresor("Lion", TypeEnumTresors.LION, chemin + "Zephyr.png"));
        out.add(new CarteTresor("Lion", TypeEnumTresors.LION, chemin + "Zephyr.png"));
        out.add(new CarteTresor("Lion", TypeEnumTresors.LION, chemin + "Zephyr.png"));
        out.add(new CarteTresor("Lion", TypeEnumTresors.LION, chemin + "Zephyr.png"));
        out.add(new CarteTresor("Lune", TypeEnumTresors.LUNE, chemin + "Pierre.png"));
        out.add(new CarteTresor("Lune", TypeEnumTresors.LUNE, chemin + "Pierre.png"));
        out.add(new CarteTresor("Lune", TypeEnumTresors.LUNE, chemin + "Pierre.png"));
        out.add(new CarteTresor("Lune", TypeEnumTresors.LUNE, chemin + "Pierre.png"));
        out.add(new CarteTresor("Lune", TypeEnumTresors.LUNE, chemin + "Pierre.png"));
        out.add(new CarteTresor("Feu", TypeEnumTresors.FEU, chemin + "Cristal.png"));
        out.add(new CarteTresor("Feu", TypeEnumTresors.FEU, chemin + "Cristal.png"));
        out.add(new CarteTresor("Feu", TypeEnumTresors.FEU, chemin + "Cristal.png"));
        out.add(new CarteTresor("Feu", TypeEnumTresors.FEU, chemin + "Cristal.png"));
        out.add(new CarteTresor("Feu", TypeEnumTresors.FEU, chemin + "Cristal.png"));
        out.add(new CarteTresor("Trophée", TypeEnumTresors.TROPHEE, chemin + "Calice.png"));
        out.add(new CarteTresor("Trophée", TypeEnumTresors.TROPHEE, chemin + "Calice.png"));
        out.add(new CarteTresor("Trophée", TypeEnumTresors.TROPHEE, chemin + "Calice.png"));
        out.add(new CarteTresor("Trophée", TypeEnumTresors.TROPHEE, chemin + "Calice.png"));
        out.add(new CarteTresor("Trophée", TypeEnumTresors.TROPHEE, chemin + "Calice.png"));
        out.add(new CarteMonteeDesEaux("CarteMonteeDesEaux1", chemin + "MonteeDesEaux.png"));
        out.add(new CarteMonteeDesEaux("CarteMonteeDesEaux2", chemin + "MonteeDesEaux.png"));
        out.add(new CarteMonteeDesEaux("CarteMonteeDesEaux3", chemin + "MonteeDesEaux.png"));
        out.add(new CarteAction("Hélicoptere", TypeEnumCarteAction.HELICOPTERE, chemin + "Helicoptere.png"));
        out.add(new CarteAction("Hélicoptere", TypeEnumCarteAction.HELICOPTERE, chemin + "Helicoptere.png"));
        out.add(new CarteAction("Hélicoptere", TypeEnumCarteAction.HELICOPTERE, chemin + "Helicoptere.png"));
        out.add(new CarteAction("Sac", TypeEnumCarteAction.SAC_DE_SABLE, chemin + "SacsDeSable.png"));
        out.add(new CarteAction("Sac", TypeEnumCarteAction.SAC_DE_SABLE, chemin + "SacsDeSable.png"));

        return out;
    }
}
