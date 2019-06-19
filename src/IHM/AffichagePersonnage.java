/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Cartes.CarteRouge;
import Enumerations.TypeEnumCouleurPion;
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
    
    private JPanel panelMilieu;
    
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
        if(perso.getEmplacement().getPersonnages().size() == 1)
        {
            buttonDonnerCarte.setEnabled(false);
        }
        else
        {
            buttonDonnerCarte.setEnabled(true);
        }
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
                    ArrayList<Personnage> listPersoEmplacement = perso.getEmplacement().getPersonnages();
                    listPersoEmplacement.remove(perso);
                    VuDefausse vd = new VuDefausse(perso.getCartes(), "Donner carte", 1, listPersoEmplacement);
                    vd.setVisible(true);
                    perso.donnerCarteAJoueur(vd.getPersoQuiRecoitCartes(), vd.getSelectedItems());
                }
            }
        });
        
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
        if (b) {
            labelTypeJoueur.setForeground(Color.black);
            labelJoueur.setForeground(Color.black);
            this.setBorder(BorderFactory.createLineBorder(perso.getCouleurPion().getColor(), 2));
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
            panelMilieu.removeAll();
            panelMilieu.revalidate();
            labelJoueur.setText(perso.getNom());
            labelTypeJoueur.setText(perso.getType().toString());

            for (CarteRouge cr : perso.getCartes()) {
                panelMilieu.add(new ImageContainer(cr.getImage(), 0, 0, 55, 100));
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
