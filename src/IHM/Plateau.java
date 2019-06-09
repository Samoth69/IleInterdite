/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Enumerations.TypeEnumAction;
import Enumerations.TypeEnumInondation;
import Enumerations.TypeEnumTresors;
import IleInterdite.ControlleurJeuSecondaire;
import IleInterdite.Grille;
import IleInterdite.Message;
import IleInterdite.Observateur;
import Personnages.*;
import IleInterdite.Tuile;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

/**
 *
 * @author mariottp
 */
public class Plateau implements Observateur {

    //Explorateur explo;
    //Messager testP;
    //Ingenieur inge;
    //Grille grille;
    //Pion pion, pion2, pion3;
    ArrayList<Personnage> listPerso;
    ArrayList<Pion> listPion;
    Tuile plateau[][] = new Tuile[6][6];
    JPanel panel[][] = new JPanel[6][6];

    ControlleurJeuSecondaire cj;
    Grille grille;

    JLabel joueurActuel;
    JLabel ActionRestante;

    JButton buttonDeplacement;
    JButton buttonAssecher;

    //boolean deplacementMode = false; //Devient vrai si le joueur à cliquer sur la case de son emplacement et voit donc les cases sur lesquels il peut aller
    //indique le "mode" de l'interface cad, comment elle doit afficher la grille en fonction du bouton cliquer par l'utilisateur
    int mode = 0; //0: aucun, 1: deplacement, 2: assecher
    int oldMode = 0; //permet de détecter les changement dans la variable mode

    final Color emptyColor = new Color(255, 255, 255); //couleur case vide
    final Color tuileMouilee = new Color(52, 152, 219);
    final Color tuileInondee = new Color(86, 101, 115);
    final Color selectColor = new Color(12, 175, 12);
    final Color tuileColor = new Color(243, 156, 18);
    final Color nonSelectedColor = Color.gray;

    final Color echelleDeath = Color.white;
    final Color echelleBleu5 = new Color(27, 79, 114);
    final Color echelleBleu4 = new Color(40, 116, 166);
    final Color echelleBleu3 = new Color(46, 134, 193);
    final Color echelleBleu2 = new Color(52, 152, 219);
    final Color echetteText = Color.white;
    final Color echetteRed = Color.red;

    final String nomButtonDeplacement = "Se Déplacer";
    final String nomButtonAssecher = "Assécher";
    final String nomAnnulé = "Annuler";

    private final JFrame window;
    private JLabel[] niveauEau;
    
    private JPanel contenantNiveauEauMain;
    private JPanel contenantNiveauEauGauche;
    private JPanel contenantNiveauEauDroite;
    
    private JButton augmenterniveauEau;
    
    private final int max = 9; //taille frise inondation
    private int niveauEaucompteur = max;

    public Plateau(ArrayList<Personnage> persos, ControlleurJeuSecondaire cj) {
        plateau = cj.getGrille();
        this.cj = cj;
        grille = cj.getIle();
        listPion = new ArrayList<Pion>();
        listPerso = persos;
        for (Personnage p : listPerso) {
            listPion.add(new Pion(p));
        }

        /**
         * PARTIE SWING *
         */
        /**
         * DEFNITION DE LA FENETRE *
         */
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        // Définit la taille de la fenêtre en pixels
        window.setSize(1500, 900);
        window.setTitle("Ile Interdite");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width / 2 - window.getSize().width / 2, dim.height / 2 - window.getSize().height / 2);

        /**
         * AJOUT PANEL PRINCIPAL + PANEL HAUT POUR TITRE FENETRE *
         */
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel);

        JPanel panelHaut = new JPanel();
        mainPanel.add(panelHaut, BorderLayout.NORTH);
        
        /*********************************** NIVEAU EAU ***************************************/
        contenantNiveauEauMain = new JPanel(new BorderLayout()); //menu principal qui sera ajouté au mainPanel
        contenantNiveauEauDroite = new JPanel(new GridLayout(10, 1)); //menu avec les chiffres indiquant le nombre de carte inondation à piocher
        contenantNiveauEauDroite.setPreferredSize(new Dimension(25,mainPanel.getHeight()));
        contenantNiveauEauGauche = new JPanel(new GridLayout(10, 1)); //niveau de l'eau
        contenantNiveauEauGauche.setPreferredSize(new Dimension(90,mainPanel.getHeight()));
        
        //MENU GAUCHE
        niveauEau = new JLabel[max + 1];
        for(int i = 0; i <= max; i++){
            System.out.println(i);
            System.out.println(max - i);
            System.out.println();
            niveauEau[max - i]=new JLabel(Integer.toString(i), SwingConstants.CENTER);
            //niveauEau[max - i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            niveauEau[max - i].setPreferredSize(new Dimension(150, contenantNiveauEauGauche.getHeight()/12));
        }
        for (int i = 0; i <= max; i++) {
            contenantNiveauEauGauche.add(niveauEau[i]);
        }
        
        niveauEau[max].setText("Novice");
        niveauEau[max - 1].setText("Normal");
        niveauEau[max - 2].setText("Elite");
        niveauEau[max - 3].setText("Légendaire");
        niveauEau[0].setText("Dead");
        augmenterniveauEau=new JButton("Augmenter Niveau Eau");
        augmenterniveauEau.setPreferredSize(new Dimension(150, contenantNiveauEauGauche.getHeight()/12));
        //décommenté pour voir le bouton
        //contenantNiveauEauGauche.add(augmenterniveauEau);
        
        augmenterniveauEau.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    niveauEau[niveauEaucompteur].setBackground(Color.CYAN);
                    niveauEau[niveauEaucompteur].setOpaque(true);
                    niveauEaucompteur--;
                }
        });
        
        //MENU DROITE
        JLabel num2 = new JLabel("2", SwingConstants.RIGHT); 
        JLabel num3 = new JLabel("3", SwingConstants.RIGHT); 
        JLabel num4 = new JLabel("4", SwingConstants.RIGHT); 
        JLabel num5 = new JLabel("5", SwingConstants.RIGHT); 
        
        for (int i = 0; i <= max; i++) {
            switch(i) {
                case 8:
                    //num2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    num2.setPreferredSize(new Dimension(90, contenantNiveauEauGauche.getHeight()/12));
                    num2.setFont(new Font(num2.getFont().getName(), Font.PLAIN, 35)); //on garde la police utilisé par l'objet                    
                    contenantNiveauEauDroite.add(num2);
                    break;
                case 5:
                    //num3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    num3.setPreferredSize(new Dimension(90, contenantNiveauEauGauche.getHeight()/12));
                    num3.setFont(new Font(num3.getFont().getName(), Font.PLAIN, 35)); //on garde la police utilisé par l'objet           
                    contenantNiveauEauDroite.add(num3);
                    break;
                case 3:
                    //num4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    num4.setPreferredSize(new Dimension(90, contenantNiveauEauGauche.getHeight()/12));
                    num4.setFont(new Font(num4.getFont().getName(), Font.PLAIN, 35)); //on garde la police utilisé par l'objet           
                    contenantNiveauEauDroite.add(num4);
                    break;
                case 1:
                    //num5.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    num5.setPreferredSize(new Dimension(90, contenantNiveauEauGauche.getHeight()/12));
                    num5.setFont(new Font(num5.getFont().getName(), Font.PLAIN, 35)); //on garde la police utilisé par l'objet           
                    contenantNiveauEauDroite.add(num5);
                    break;
                default:
                    JLabel numEmpty = new JLabel("");
                    //numEmpty.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    numEmpty.setPreferredSize(new Dimension(90, contenantNiveauEauGauche.getHeight()/12));
                    contenantNiveauEauDroite.add(numEmpty);
                    break;
            }
        }
        
        //MENU PRINCPALE
        contenantNiveauEauMain.add(contenantNiveauEauGauche, BorderLayout.WEST);
        contenantNiveauEauMain.add(contenantNiveauEauDroite, BorderLayout.EAST);
        
        mainPanel.add(contenantNiveauEauMain, BorderLayout.WEST);
      /******************************************************************************************************/
        JLabel labelTitre = new JLabel("Ile Interdite");
        labelTitre.setForeground(Color.BLUE);
        labelTitre.setFont(new Font(labelTitre.getFont().getName(), labelTitre.getFont().getStyle(), (int) (labelTitre.getFont().getSize() * 1.5)));
        panelHaut.add(labelTitre);

        /**
         * AJOUT DE LA GRILLE DE JEU AU CENTRE DE LA FENETRE *
         */
        JPanel panelGrille = new JPanel(new GridLayout(6, 6));
        mainPanel.add(panelGrille, BorderLayout.CENTER);

        buttonDeplacement = new JButton(nomButtonDeplacement);
        buttonDeplacement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                oldMode = mode;
                mode = 1;
                gamePadClick();
            }
        });

        buttonAssecher = new JButton(nomButtonAssecher);
        buttonAssecher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                oldMode = mode;
                mode = 2;
                gamePadClick();
            }
        });

        joueurActuel = new JLabel("");
        ActionRestante = new JLabel();

        JPanel panelGamePad = new JPanel(new BorderLayout());
        JPanel panelHautGamePad = new JPanel(new GridLayout(3, 2));
        panelHautGamePad.add(new JLabel("Tour du joueur "));
        panelHautGamePad.add(joueurActuel);
        panelHautGamePad.add(buttonDeplacement);
        panelHautGamePad.add(buttonAssecher);
        panelHautGamePad.add(new JLabel("Action restante:"));
        panelHautGamePad.add(ActionRestante);

        panelGamePad.add(panelHautGamePad, BorderLayout.NORTH);
        mainPanel.add(panelGamePad, BorderLayout.EAST);


        affecterCase(plateau, listPion, panelGrille);
        updateGamePad();
    }
    
    public class customGraphic extends JComponent {
        
    }

    public void affecterCase(Tuile plateau[][], ArrayList<Pion> listPion, JPanel grille) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                //Creation d'une case
                JPanel pn = new JPanel(new GridLayout(3, 1));
                //bordure de la case
                pn.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                if (plateau[i][j] != null) {
                    //ajout label nom case
                    pn.add(new JLabel(plateau[i][j].getNom()), BorderLayout.CENTER);

                    for (int p = 0; p < listPion.size(); p++) {
                        //si case = point de depart d'un pion -> mettre le pion
                        if (plateau[i][j].getCouleurPion() == listPion.get(p).getCouleurPion()) {
                            pn.add(listPion.get(p));
                        }
                    }

                    if (plateau[i][j].getTresor() != TypeEnumTresors.AUCUN) {
                        pn.add(new Tresor());
                    }
                } else {
                    pn.setBackground(emptyColor);       //background en bleu
                }

                final JPanel xjp = pn;
                final Tuile xt = plateau[i][j];
                final int xi = i;
                final int xj = j;

                pn.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        panelClick(xjp, xt, xi, xj);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                });

                panel[i][j] = pn;
                grille.add(pn, i, j);        //Ajout de la case a la grille de jeu (panelGrille)

            }
        }
        paintNormal();
    }

    //met à jour les informations du gamepad en fonction de l'état du jeu
    private void updateGamePad() {
        joueurActuel.setText(cj.getNomJoueur());
    }

    //est appeller quand une action est fini
    private void actionFinished() {
        paintNormal();
        mode = 0;
        updateGamePad();
        buttonAssecher.setText(nomButtonAssecher);
        buttonDeplacement.setText(nomButtonDeplacement);
        buttonAssecher.setEnabled(true);
        buttonDeplacement.setEnabled(true);
        window.repaint();
    }

    private void gamePadClick() {
        //System.out.println("deplacement = " + mode + " OLD:" + oldMode);

        //si jamais l'ancien et le mode actuel sont égaux (donc potentiellement, l'utilisateur appuie deux fois sur "se deplacer")
        //on remet le mode à 0 afin d'annuler action et de redéssiner le plateau normalement
        if (oldMode == mode) {
            mode = 0;
        }

        switch (mode) {
            case 0: //normal
                //System.out.println("coucou");
                actionFinished();
                break;
            case 1: //se deplacer
                paintNonSelected();
                buttonAssecher.setEnabled(false);
                buttonDeplacement.setEnabled(true);
                buttonDeplacement.setText(nomAnnulé);

                for (Tuile t : cj.getJoueurEntrainDeJouer().getDeplacements()) {
                    JPanel jpa = panel[t.getX()][t.getY()];
                    jpa.setBackground(tuileColor);
                    //System.out.println(t.getNom() + "\t" + t.getX() + "\t" + t.getY());
                }
                break;
            case 2:
                paintNonSelected();
                buttonAssecher.setEnabled(true);
                buttonDeplacement.setEnabled(false);
                buttonAssecher.setText(nomAnnulé);

                for (Tuile t : cj.getJoueurEntrainDeJouer().getTuileQuiPeutSecher()) {
                    JPanel jpa = panel[t.getX()][t.getY()];
                    jpa.setBackground(tuileColor);
                    //System.out.println(t.getNom() + "\t" + t.getX() + "\t" + t.getY());
                }
                break;
        }
    }

    //ce déclenche quand une case sur l'écran est cliquer
    private void panelClick(JPanel jp, Tuile emplacement, int i, int j) {
        System.out.println("panelClick: " + i + ", " + j);
        System.out.println("deplacement = " + mode);
        if (jp.getBackground() != nonSelectedColor) {
            switch (mode) {
                case 1: //se deplacer
                    System.out.println("Moving");
                    cj.deplacerJoueurEnCour(emplacement);
                    int x = cj.getJoueurEntrainDeJouer().getEmplacement().getX();
                    int y = cj.getJoueurEntrainDeJouer().getEmplacement().getX();
                    panel[x][y].remove(listPion.get(cj.getJoueurNum()));
                    panel[i][j].add(listPion.get(cj.getJoueurNum()));

                    break;
                case 2:
                    System.out.println("Assechement");
                    cj.assecher(emplacement);
                    break;
            }
            actionFinished();
        }

    }

    //tout le plateau devient gris
    private void paintNonSelected() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                JPanel jp = panel[i][j];
                if (jp.getBackground() != emptyColor) {
                    jp.setBackground(nonSelectedColor);
                }

            }
        }
        window.repaint();
    }

    //tout le plateau retourne à ça couleur "normal"
    private void paintNormal() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                JPanel jp = panel[i][j];
                if (jp.getBackground() != emptyColor) {
                    switch (plateau[i][j].getInondation()) {
                        case SEC:
                            jp.setBackground(tuileColor);     //background en jaune
                            break;
                        case MOUILLE:
                            jp.setBackground(tuileMouilee);
                            break;
                        case INONDE:
                            jp.setBackground(tuileInondee);
                            break;
                    }
                }
            }
        }
        window.repaint();
    }

    public void afficher() {
        this.window.setVisible(true);
    }

    @Override
    public void traiterMessage(Message m) {
        System.out.println("MESSAGE");
        switch (m.getMessageType()) {
            case ACTION:
                switch (m.getActionType()) {
                    case PIOCHER_CARTE_MONTEE_DES_EAUX:

                        break;
                    case PIOCHER_DEUX_CARTE_TRESOR:

                        break;
                }
                break;
            case CARTE:

                break;
            case JOUEUR_SUIVANT:

                break;
        }
    }

}
