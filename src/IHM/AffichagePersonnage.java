/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Cartes.CarteRouge;
import Enumerations.TypeEnumCouleurPion;
import Enumerations.TypeEnumMessage;
import Enumerations.TypeEnumTresors;
import IleInterdite.Message;
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
public class AffichagePersonnage extends JPanel{
  
    private JPanel bigMainPanel;
    
    private JLabel labelJoueur;
    private JLabel labelTypeJoueur;

    private JPanel bigPion;
    private Pion pion;
    private Personnage perso;
    private Plateau pl;
    
    private JPanel panelMilieu;
    
    boolean dejaDonne = false;
    
    ArrayList<Personnage> listPersoEmplacement = new ArrayList<>();
    
    private JButton buttonDeplacement;
    private JButton buttonAssecher;
    private JButton buttonPasserTour;
    private JButton buttonDonnerCarte;
    private JButton buttonPrendreRelique;
    private JButton buttonCarteSpecial;
    
    public final static String nomButtonDeplacement = "Se Déplacer";
    public final static String nomButtonAssecher = "Assécher";
    public final static String nomAnnulé = "Annuler";
    
    private final static String imgFolder = System.getProperty("user.dir") + "/src/RessourcesPlateau/";
    
    public AffichagePersonnage(Plateau pl, Personnage perso) {
        super(true);
        super.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        this.pl = pl;
        
        labelTypeJoueur = new JLabel();
        bigPion = new JPanel();
        this.perso = perso;
        
        if (perso == null) {
            super.setVisible(false);
            pion = new Pion(TypeEnumCouleurPion.AUCUN);
        } else {
            pion = new Pion(perso.getCouleurPion());
        }    
    
        bigMainPanel = new JPanel(new BorderLayout());
        JPanel panelHaut = new JPanel(new BorderLayout());

        labelJoueur = new JLabel("...");
        labelTypeJoueur = new JLabel("...");
        panelHaut.add(labelJoueur, BorderLayout.WEST);
        panelHaut.add(labelTypeJoueur, BorderLayout.EAST);
        bigPion.add(pion);
        panelHaut.add(bigPion, BorderLayout.CENTER);
        
        panelMilieu = new JPanel(new FlowLayout());
        
        JPanel panelBas = new JPanel(new GridLayout(2,3));
        
        buttonDeplacement = new JButton(nomButtonDeplacement);
        buttonDeplacement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                pl.changeMode(1);
                pl.gamePadClick();
            }
        });

        buttonAssecher = new JButton(nomButtonAssecher);
        buttonAssecher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                pl.changeMode(2);
                pl.gamePadClick();
            }
        });
        
        buttonPasserTour = new JButton("Passer tour");
        buttonPasserTour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                pl.getControleurJeu().passerJoueurSuivant();
            }
        });
        
        buttonDonnerCarte = new JButton("Donner carte");
        buttonPrendreRelique = new JButton("Prendre relique");;
        buttonCarteSpecial = new JButton("Carte Spécial");
        
        buttonPrendreRelique.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                pl.getControleurJeu().recupererTresor(pl.getControleurJeu().getJoueurEntrainDeJouer().getEmplacement());
            }
        });
        
        buttonDonnerCarte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(perso.getEmplacement().getPersonnages().size() != 1)
                {
                    listPersoEmplacement.addAll(perso.getEmplacement().getPersonnages());
                    listPersoEmplacement.remove(perso);
                    VuDefausse vd = new VuDefausse(perso.getCartes(), "Donner carte", 1, listPersoEmplacement,pl.getControleurJeu().getNbActionRestante());
                    vd.setVisible(true);
                    perso.donnerCarteAJoueur(vd.getPersoQuiRecoitCartes(), vd.getSelectedItems());
                    pl.getControleurJeu().setNbAction(vd.getNbActionRestante());
                    listPersoEmplacement.clear();
                    buttonDonnerCarte.setEnabled(false);
                    pl.getControleurJeu().notifierObservateur(new Message(TypeEnumMessage.UPDATE_GUI));
                    //dejaDonne = true;
                }
            }
        });
        
        /**buttonPrendreRelique.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                pl.getControleurJeu().recupererTresor(pl.getControleurJeu().getJoueurEntrainDeJouer().getEmplacement());
            }
        });**/
        
        panelBas.add(buttonDeplacement);
        panelBas.add(buttonAssecher);
        panelBas.add(buttonPasserTour);
        panelBas.add(buttonDonnerCarte);
        panelBas.add(buttonPrendreRelique);
        panelBas.add(buttonCarteSpecial);
        

        bigMainPanel.add(panelHaut, BorderLayout.NORTH);
        bigMainPanel.add(panelMilieu, BorderLayout.CENTER);
        bigMainPanel.add(panelBas, BorderLayout.SOUTH);
        //System.out.println(bigPion.getSize());
        this.add(bigMainPanel);
        
    }
    
    //met à jour les éléments de la fenêtre avec le joueur passé en paramètre
    public void update(Boolean b) {
        buttonAssecher.setEnabled(b);
        buttonDeplacement.setEnabled(b);
        buttonPasserTour.setEnabled(b);
        buttonPrendreRelique.setEnabled(b);
        buttonCarteSpecial.setEnabled(b);
        
        if (b) {
            labelTypeJoueur.setForeground(Color.black);
            labelJoueur.setForeground(Color.black);
            this.setBorder(BorderFactory.createLineBorder(perso.getCouleurPion().getColor(), 10));
            if (perso != null) {
                pion.setCouleur(perso.getCouleurPion());
            }
        } else {
            labelTypeJoueur.setForeground(Color.gray);
            labelJoueur.setForeground(Color.gray);
            this.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
            pion.setCouleur(TypeEnumCouleurPion.AUCUN);
        }
        if (perso != null) {
            //  Si il y a une personne en plus du joueur en train de jouer sur la case
            //  On active la commande pour donner les cartes
            //  Sinon non
            if(perso == pl.getControleurJeu().getJoueurEntrainDeJouer())
            {
                if(!dejaDonne)
                {
                    if(perso.getEmplacement().getPersonnages().size() > 1)
                    {
                        buttonDonnerCarte.setEnabled(true);
                    }
                    else
                    {
                        buttonDonnerCarte.setEnabled(false);
                    }
                }
                else
                {
                    buttonDonnerCarte.setEnabled(false);
                }
            }
            else
            {
                buttonDonnerCarte.setEnabled(false);  
            }
            
            
            //  Si le personnage en train de jouer est sur une case avec un tresor
            //  et qu'il posséde 4 carte du meme type que le tresor
            //  la commande pour prendre le tresor est activee
            if(perso.getEmplacement().getTresor() != TypeEnumTresors.AUCUN)
            {
                int comptTresor =0;
                for(CarteRouge i : perso.getCartes())
                {
                    if(i.getTypeTresor() == perso.getEmplacement().getTresor())
                    {
                        comptTresor++;
                    }
                }
                if(comptTresor == 4)
                {
                    buttonPrendreRelique.setEnabled(true);
                }
            }
            else
            {
                buttonPrendreRelique.setEnabled(false);
            }
            
            panelMilieu.removeAll();
            panelMilieu.revalidate();
            labelJoueur.setText(perso.getNom());
            labelTypeJoueur.setText(perso.getType().toString());

            for (CarteRouge cr : perso.getCartes()) {
                panelMilieu.add(new ImageContainer(cr.getImage(), 0, 0, 50, 80));
            }
            panelMilieu.repaint();
        }
        
    }
    
    public void setButtonDeplacementText(String text) {
        buttonDeplacement.setText(text);
    }
    
    public void setButtonDeplacementEnabled(boolean b) {
        buttonDeplacement.setEnabled(b);
    }
    
    public void setButtonAssecherText(String text) {
        buttonAssecher.setText(text);
    }
    
    public void setButtonAssecherEnabled(boolean b) {
        buttonAssecher.setEnabled(b);
    }
    
    public void setButtonPasserTourText(String text) {
        buttonPasserTour.setText(text);
    }
    
    public void setButtonPasserTourEnabled(boolean b) {
        buttonPasserTour.setEnabled(b);
    }
    
    public void setButtonDonnerCarteText(String text){
        buttonDonnerCarte.setText(text);
    }
    
    public void setButtonDonnerCarteEnabled(boolean b){
        buttonDonnerCarte.setEnabled(b);
    }
    
    
}
