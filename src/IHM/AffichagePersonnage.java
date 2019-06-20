/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Cartes.CarteAction;
import Cartes.CarteRouge;
import Cartes.CarteTresor;
import Enumerations.TypeEnumCarteAction;
import Enumerations.TypeEnumCouleurPion;
import Enumerations.TypeEnumInondation;
import Enumerations.TypeEnumMessage;
import Enumerations.TypeEnumTresors;
import IleInterdite.Message;
import IleInterdite.Tuile;
import Personnages.Personnage;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

/**
 *
 * @author violentt
 */
public class AffichagePersonnage extends JPanel {

    //ATTRIBUTS
    
    //fenetre principal
    private JPanel bigMainPanel;

    //label du joueur et type joueur
    private JLabel labelJoueur;
    private JLabel labelTypeJoueur;

    //pion, perso et plateau
    private JPanel bigPion;
    private Pion pion;
    private Personnage perso;
    private Plateau pl;

    private JPanel panelMilieu;

    boolean dejaDonne = false;
    
    //liste des personnages, carte tresor et carte actio,
    private ArrayList<Personnage> listPersoEmplacement = new ArrayList<>();
    private ArrayList<CarteRouge> carteTresorDuJoueur = new ArrayList<>();
    private ArrayList<CarteRouge> carteActionDuJoueur = new ArrayList<>();
    
    //Bouton du gamepad
    private JButton buttonDeplacement; //deplace le perso en fontion de son role
    private JButton buttonAssecher;    //asseche en fonction du role
    private JButton buttonPasserTour;   //passe au joueur suivant
    private JButton buttonDonnerCarte;  //donne une carte à un joueur de la meme case
    private JButton buttonPrendreRelique; //defausse 4 cartes correspondant à la relique, prend la relique
    private JButton buttonCarteSpecial; //joue une carte action special: helicoptère et sac de sable
    
    //definie le nom des bouton
    public final static String nomButtonDeplacement = "Se Déplacer";
    public final static String nomButtonAssecher = "Assécher";
    public final static String nomAnnulé = "Annuler";

    private final static String imgFolder = System.getProperty("user.dir") + "/src/RessourcesPlateau/";

    //CONSTRUCTEUR
    public AffichagePersonnage(Plateau pl, Personnage perso) {
        super(true); //double buffered
        super.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); //bordure
        
        //plateau
        this.pl = pl;
        
        
        labelTypeJoueur = new JLabel(); //label type joueuer
        bigPion = new JPanel(); //fenetre bigpion
        this.perso = perso; //Personnage
        
        //pion du perso
        if (perso == null) { //si perso est null
            super.setVisible(false);   //pion invisible
            pion = new Pion(TypeEnumCouleurPion.AUCUN); //type aucun
        } else {//sinon
            pion = new Pion(perso);  //Creer le pion
        }
        
        bigMainPanel = new JPanel(new BorderLayout());//creation d une grosse fenetre principal en borderlayout (NORTH,SOUTH,EAST..)
        JPanel panelHaut = new JPanel(new BorderLayout()); //panel haut en borderlayout

        labelJoueur = new JLabel("..."); //label joueur modifié plus tard
        labelTypeJoueur = new JLabel("..."); //label type(role) joueur modifié plus tard
        panelHaut.add(labelJoueur, BorderLayout.WEST);  //placement label joueuer à l ouest du panel haut
        panelHaut.add(labelTypeJoueur, BorderLayout.EAST);//placement du labeltypejoueur à l est du panel haut
        bigPion.add(pion);  //ajoute le pion à la fenetre bigpion
        panelHaut.add(bigPion, BorderLayout.CENTER);//ajoute la fenetre bigpion au centre du panelhaut

        panelMilieu = new JPanel(new FlowLayout());//panel contenant les cartes

        JPanel panelBas = new JPanel(new GridLayout(2, 3));//panel bas en grille de 2 lignes 3 colonnes
        
        //action du bouton deplacement
        buttonDeplacement = new JButton(nomButtonDeplacement);
        buttonDeplacement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                pl.changeMode(1); //change le mode en deplacement (0: aucun, 1: deplacement, 2: assecher, 3: Deplacement carte helicoptere)
                pl.gamePadClick();//utilisé par les boutons déplacer et assécher afin de changer l'affichage du plateau
            }
        });
        
        //action du bouton assecher
        buttonAssecher = new JButton(nomButtonAssecher);
        buttonAssecher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                pl.changeMode(2);//change le mode en assecher (0: aucun, 1: deplacement, 2: assecher, 3: Deplacement carte helicoptere)
                pl.gamePadClick();//utilisé par les boutons déplacer et assécher afin de changer l'affichage du plateau
            }
        });
        
        //action du bouton passer tour
        buttonPasserTour = new JButton("Passer tour");
        buttonPasserTour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                pl.getControleurJeu().passerJoueurSuivant();// passe au joueur suivant
            }
        });
        
        //definie le nom des bouton donner carte, prendre relique et carte special
        buttonDonnerCarte = new JButton("Donner carte");
        buttonPrendreRelique = new JButton("Prendre relique");;
        buttonCarteSpecial = new JButton("Carte Spécial");
        
        //action du bouton prendre relique
        buttonPrendreRelique.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) { //prend la relique à partir de l emplacement du joueurcourant
                pl.getControleurJeu().recupererTresor(pl.getControleurJeu().getJoueurEntrainDeJouer().getEmplacement(), false);
            }
        });
        
        //action du bouton donner carte
        buttonDonnerCarte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (perso.getEmplacement().getPersonnages().size() != 1) {
                    listPersoEmplacement.addAll(perso.getEmplacement().getPersonnages());//ajoute les personnage de l emplacement dans cette liste
                    listPersoEmplacement.remove(perso);                                    //sauf celui qui lance la commande
                    VuDefausse vd = new VuDefausse(perso.getCartes(), "Donner carte", 1, listPersoEmplacement, pl.getControleurJeu().getNbActionRestante());
                    vd.setVisible(true); //afficher les cartes à donner
                    perso.donnerCarteAJoueur(vd.getPersoQuiRecoitCartes(), vd.getSelectedItems()); //donner les cartes selectionner
                    pl.getControleurJeu().setNbAction(vd.getNbActionRestante()); //change le nombre d action restante
                    listPersoEmplacement.clear(); //vide la liste
                    buttonDonnerCarte.setEnabled(false); 
                    pl.getControleurJeu().notifierObservateur(new Message(TypeEnumMessage.UPDATE_GUI)); //met à jour l interface
                    //dejaDonne = true;
                }
            }
        });
        
        //action du bouton action spécial
        buttonCarteSpecial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                carteActionDuJoueur.clear();

                for (CarteRouge i : perso.getCartes()) {
                    if (i instanceof CarteAction) {
                        carteActionDuJoueur.add(i);
                    }
                }

                if (!carteActionDuJoueur.isEmpty()) {
                    VuDefausse vd1 = new VuDefausse(carteActionDuJoueur, "utiliser carte"); //idem à donner carte
                    vd1.setVisible(true);
               
                    if(vd1.getSelectedItems().get(0).getTypeCarteAction() == TypeEnumCarteAction.HELICOPTERE)
                    {
                        pl.changeMode(3);//change le mode en deplcement carte helicop (0: aucun, 1: deplacement, 2: assecher, 3: Deplacement carte helicoptere)
                        pl.gamePadClick();
                        perso.removeCarte(vd1.getSelectedItems().get(0));
                    }
                    else
                    {
                        ArrayList<Tuile> verifCarteAAssecher = new ArrayList<>();
                        for(Tuile t : pl.getControleurJeu().getListeCarte())
                        {
                            if(t.getInondation() == TypeEnumInondation.MOUILLE)
                            {
                                verifCarteAAssecher.add(t);
                            }
                        }
                        if(!verifCarteAAssecher.isEmpty())
                        {
                            pl.changeMode(4);
                            pl.gamePadClick();
                            perso.removeCarte(vd1.getSelectedItems().get(0));
                        }
                        verifCarteAAssecher.clear();
                        
                    }
                    
                    pl.getControleurJeu().notifierObservateur(new Message(TypeEnumMessage.UPDATE_GUI));
                }
            }
        });
        //ajoute au panel bas les boutons
        panelBas.add(buttonDeplacement);
        panelBas.add(buttonAssecher);
        panelBas.add(buttonPasserTour);
        panelBas.add(buttonDonnerCarte);
        panelBas.add(buttonPrendreRelique);
        panelBas.add(buttonCarteSpecial);

        bigMainPanel.add(panelHaut, BorderLayout.NORTH); //panelhaut au nord du bigpanel
        bigMainPanel.add(panelMilieu, BorderLayout.CENTER); //panelmilieu au centre du bigpanel
        bigMainPanel.add(panelBas, BorderLayout.SOUTH); // Panelbas au sud du big panel
        //System.out.println(bigPion.getSize());
        this.add(bigMainPanel);

    }
    
    //METHODES

    //met à jour les éléments de la fenêtre avec le joueur passé en paramètre
    //Permet à l'utilisateur d'identifier rapidemment la joueur qui joue
    //identifier: en grisant les autres joueur; colorant (avec bordure epaisse) le joueur actuel
    public void update(Boolean b) {
        //desactivie ou active les fenetre en fonction du boolean b
        buttonAssecher.setEnabled(b);
        buttonDeplacement.setEnabled(b);
        buttonPasserTour.setEnabled(b);
        buttonPrendreRelique.setEnabled(b);
        buttonCarteSpecial.setEnabled(b);

        if (b) { // si b= true
            labelTypeJoueur.setForeground(Color.black);   //labeljoueur et type joueur en noir
            labelJoueur.setForeground(Color.black);
            this.setBorder(BorderFactory.createLineBorder(perso.getCouleurPion().getColor(), 10)); //bordure epaisse de la couleur du joueur qui joue
            if (perso != null) {
                pion.setCouleur(perso.getCouleurPion());
            }
        } else {
            labelTypeJoueur.setForeground(Color.gray); //labeljoueur et typejoueur en gris
            labelJoueur.setForeground(Color.gray);
            this.setBorder(BorderFactory.createLineBorder(Color.gray, 2)); //bordure fine des joueurs qui ne jouent pas
            pion.setCouleur(TypeEnumCouleurPion.AUCUN);
        }

        carteTresorDuJoueur.clear();

        if (perso != null) {
            //  Si il y a une personne en plus du joueur en train de jouer sur la case
            //  On active la commande pour donner les cartes
            //  Sinon non
            for (CarteRouge i : perso.getCartes()) {
                if (i instanceof CarteTresor) {
                    carteTresorDuJoueur.add(i);
                }
            }
            if (!carteTresorDuJoueur.isEmpty()) {
                if (perso == pl.getControleurJeu().getJoueurEntrainDeJouer()) {
                    if (!dejaDonne) {
                        if (perso.getEmplacement().getPersonnages().size() > 1) {
                            buttonDonnerCarte.setEnabled(true);
                        } else {
                            buttonDonnerCarte.setEnabled(false);
                        }
                    } else {
                        buttonDonnerCarte.setEnabled(false);
                    }
                } else {
                    buttonDonnerCarte.setEnabled(false);
                }
            } else {
                buttonDonnerCarte.setEnabled(false);
            }

            //  Si le personnage en train de jouer est sur une case avec un tresor
            //  et qu'il posséde 4 carte du meme type que le tresor
            //  la commande pour prendre le tresor est activee
            if (perso.getEmplacement().getTresor() != TypeEnumTresors.AUCUN) {
                int comptTresor = 0;
                for (CarteRouge i : perso.getCartes()) {
                    if (i.getTypeTresor() == perso.getEmplacement().getTresor()) {
                        comptTresor++;
                    }
                }
                if (comptTresor == 4) {
                    buttonPrendreRelique.setEnabled(true);
                } else {
                    buttonPrendreRelique.setEnabled(false);
                }
            } else {
                buttonPrendreRelique.setEnabled(false);
            }

            carteActionDuJoueur.clear();

            //-------------------------meme principe
            for (CarteRouge i : perso.getCartes()) {
                if (i instanceof CarteAction) {
                    carteActionDuJoueur.add(i);
                }
            }
            
                if(carteActionDuJoueur.isEmpty())
                {
                    buttonCarteSpecial.setEnabled(false);
                } else {
                    buttonCarteSpecial.setEnabled(true);
                }
            
            
            panelMilieu.removeAll();
            panelMilieu.revalidate();
            labelJoueur.setText(perso.getNom());
            labelTypeJoueur.setText(perso.getType().toString());

            for (CarteRouge cr : perso.getCartes()) {
                panelMilieu.add(new ImageContainer(cr.getImage(), 0, 0, 50, 80)); //update des cartes en main
            }
            panelMilieu.repaint();
        }

    }

    //setter du texte du bouton deplacement
    public void setButtonDeplacementText(String text) {
        buttonDeplacement.setText(text);
    }
    
    //active/desactive le bonton deplacement
    public void setButtonDeplacementEnabled(boolean b) {
        buttonDeplacement.setEnabled(b);
    }
    //setter du texte du bouton assecher
    public void setButtonAssecherText(String text) {
        buttonAssecher.setText(text);
    }
    //active/desactive le bouton assecher
    public void setButtonAssecherEnabled(boolean b) {
        buttonAssecher.setEnabled(b);
    }
    
    //texte du bouton passer tour
    public void setButtonPasserTourText(String text) {
        buttonPasserTour.setText(text);
    }
    
    //active/desactive ce bouton
    public void setButtonPasserTourEnabled(boolean b) {
        buttonPasserTour.setEnabled(b);
    }
    
    //texte du bouton donner carte
    public void setButtonDonnerCarteText(String text) {
        buttonDonnerCarte.setText(text);
    }

    //active/desactive le bouton donner carte
    public void setButtonDonnerCarteEnabled(boolean b) {
        buttonDonnerCarte.setEnabled(b);
    }
    
    public void setBtCarteActionEnabled(boolean b){
        buttonCarteSpecial.setEnabled(b);
    }
    
}
