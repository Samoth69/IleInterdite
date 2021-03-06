/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Cartes.CarteInondation;
import Enumerations.TypeEnumInondation;
import Enumerations.TypeEnumMessage;
import Enumerations.TypeEnumTresors;
import IleInterdite.ControleurJeuSecondaire;
import IleInterdite.Grille;
import IleInterdite.Message;
import IleInterdite.Observateur;
import Personnages.*;
import IleInterdite.Tuile;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;

/**
 *
 * @author mariottp
 */
public class Plateau implements Observateur {

    //ATTRIBUTS
    private ArrayList<Personnage> listPerso;
    private ArrayList<Pion> listPion;
    private Tuile plateau[][] = new Tuile[6][6];
    private JPanel panel[][] = new JPanel[6][6];
    private HashMap<String, Tresor> listTresor = new HashMap<>();

    private ControleurJeuSecondaire cj;
    private Grille grille;

    private JLabel joueurActuel;
    private JLabel ActionRestante;

    private JPanel panelGamePad;

    //boolean deplacementMode = false; //Devient vrai si le joueur à cliquer sur la case de son emplacement et voit donc les cases sur lesquels il peut aller
    //indique le "mode" de l'interface cad, comment elle doit afficher la grille en fonction du bouton cliquer par l'utilisateur
    private int mode = 0; //0: aucun, 1: deplacement, 2: assecher, 3: Deplacement carte helicoptere
    private int oldMode = 0; //permet de détecter les changement dans la variable mode

    //couleur des tuiles
    private final static Color emptyColor = new Color(255, 255, 255); //couleur case vide
    private final static Color tuileMouilee = new Color(52, 152, 219);
    private final static Color tuileInondee = emptyColor;
    private final static Color selectColor = new Color(12, 175, 12);
    private final static Color tuileColor = new Color(243, 156, 18);
    private final static Color nonSelectedColor = Color.gray;

    //Marqueur sur le niveau d'eau
    private final static Color echelleDeath = Color.white;
    private final static Color echelleBleu5 = new Color(27, 79, 114);
    private final static Color echelleBleu4 = new Color(40, 116, 166);
    private final static Color echelleBleu3 = new Color(46, 134, 193);
    private final static Color echelleBleu2 = new Color(52, 152, 219);
    private final static Color echelleMarqueur = Color.yellow;
    private final static Color echetteText = Color.white;
    private final static Color echetteRed = Color.red;

    //Fenetre et label
    private final JFrame window;
    private JLabel niveauEau[];
    private JLabel niveauEau2[];

    private JPanel contenantNiveauEauMain;
    private JPanel contenantNiveauEauGauche;
    private JPanel contenantNiveauEauDroite;

    //affichage perso => pion, nom , role, carte
    private AffichagePersonnage affichagePerso1;
    private AffichagePersonnage affichagePerso2;
    private AffichagePersonnage affichagePerso3;
    private AffichagePersonnage affichagePerso4;

    private final static String nomButtonDeplacement = AffichagePersonnage.nomButtonDeplacement;
    private final static String nomButtonAssecher = AffichagePersonnage.nomButtonAssecher;
    private final static String nomAnnulé = AffichagePersonnage.nomAnnulé;
    private final static String nomActionSpecialNavigateur = AffichagePersonnage.nomActionSpecialNavigateur;

    private final static String cheminCalice = System.getProperty("user.dir") + "/src/RessourcesTresors/calice.png";
    private final static String cheminCaliceNoir = System.getProperty("user.dir") + "/src/RessourcesTresors/calice-black.png";
    private final static String cheminCristal = System.getProperty("user.dir") + "/src/RessourcesTresors/cristal.png";
    private final static String cheminCristalNoir = System.getProperty("user.dir") + "/src/RessourcesTresors/cristal-black.png";
    private final static String cheminPierre = System.getProperty("user.dir") + "/src/RessourcesTresors/pierre.png";
    private final static String cheminPierreNoir = System.getProperty("user.dir") + "/src/RessourcesTresors/pierre-black.png";
    private final static String cheminZephyr = System.getProperty("user.dir") + "/src/RessourcesTresors/zephyr.png";
    private final static String cheminZephyrNoir = System.getProperty("user.dir") + "/src/RessourcesTresors/zephyr-black.png";

    private ImageContainer imgCalice = new ImageContainer(cheminCaliceNoir, 0, 0, 70, 100);
    private ImageContainer imgCristal = new ImageContainer(cheminCristalNoir, 0, 0, 70, 100);
    private ImageContainer imgPierre = new ImageContainer(cheminPierreNoir, 0, 0, 70, 100);
    private ImageContainer imgZephyr = new ImageContainer(cheminZephyrNoir, 0, 0, 70, 100);

    //objet liste
    private JList listBasGamePad = new JList();
    //liste contenant l'historique du jeu
    private ArrayList<String> historiqueAction = new ArrayList<>();

    private final int max = 9; //taille frise inondation
    //private int niveauEaucompteur = max;
    
    //private MediaPlayer mediaPlayer;
    
    private Personnage selectedPerso; //variable utilisé pour indiqué le personnage qui à été sélectionner par le navigateur et pour le déplacer sur la carte

    //CONSTRUCTEUR
    public Plateau(ArrayList<Personnage> persos, ControleurJeuSecondaire cj) {
        //initialisation plateau
        plateau = cj.getGrille();
        this.cj = cj;
        grille = cj.getIle();
        listPion = new ArrayList<Pion>();
        listPerso = persos;
        for (Personnage p : listPerso) { //Attribution pion/role
            listPion.add(new Pion(p));
        }

        for (int i = 0; i < grille.getListTuile().size(); i++) {
            if (grille.getListTuile().get(i).getTresor() != TypeEnumTresors.AUCUN) {
                listTresor.put(grille.getListTuile().get(i).getNom(), new Tresor(grille.getListTuile().get(i).getTresor()));
            }
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
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setTitle("Ile interdite");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width / 2 - window.getSize().width / 2, dim.height / 2 - window.getSize().height / 2);

        /**
         * AJOUT PANEL PRINCIPAL + PANEL HAUT POUR TITRE FENETRE *
         */
        JPanel BorderLayoutPrincipal = new JPanel(new BorderLayout());
        window.add(BorderLayoutPrincipal);

        JPanel panelHaut = new JPanel();
        BorderLayoutPrincipal.add(panelHaut, BorderLayout.NORTH);

        /**
         * ********************************* NIVEAU EAU
         * **************************************
         */
        contenantNiveauEauMain = new JPanel(new BorderLayout()); //menu principal qui sera ajouté au mainPanel
        contenantNiveauEauDroite = new JPanel(new GridLayout(10, 1)); //menu avec les chiffres indiquant le nombre de carte inondation à piocher
        contenantNiveauEauDroite.setPreferredSize(new Dimension(25, BorderLayoutPrincipal.getHeight()));
        contenantNiveauEauGauche = new JPanel(new GridLayout(10, 1)); //niveau de l'eau
        contenantNiveauEauGauche.setPreferredSize(new Dimension(90, BorderLayoutPrincipal.getHeight()));

        //MENU GAUCHE
        niveauEau = new JLabel[max + 1];
        for (int i = 0; i <= max; i++) {
            //System.out.println(i);
            //System.out.println(max - i);
            //System.out.println();
            niveauEau[max - i] = new JLabel(/*Integer.toString(i)*/"", SwingConstants.CENTER);
            //niveauEau[max - i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            niveauEau[max - i].setPreferredSize(new Dimension(150, contenantNiveauEauGauche.getHeight() / 12));
        }
        for (int i = 0; i <= max; i++) {
            contenantNiveauEauGauche.add(niveauEau[i]);
        }

        niveauEau[max].setText("Novice");
        niveauEau[max - 1].setText("Normal");
        niveauEau[max - 2].setText("Elite");
        niveauEau[max - 3].setText("Légendaire");
        niveauEau[0].setText("Dead");

        //MENU DROITE
        JLabel num2 = new JLabel("2", SwingConstants.RIGHT);
        JLabel num3 = new JLabel("3", SwingConstants.RIGHT);
        JLabel num4 = new JLabel("4", SwingConstants.RIGHT);
        JLabel num5 = new JLabel("5", SwingConstants.RIGHT);

        niveauEau2 = new JLabel[max + 1];

        for (int i = 0; i <= max; i++) {
            switch (i) {
                case 8:
                    //num2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    num2.setPreferredSize(new Dimension(90, contenantNiveauEauGauche.getHeight() / 12));
                    num2.setFont(new Font(num2.getFont().getName(), Font.PLAIN, 35)); //on garde la police utilisé par l'objet                    
                    contenantNiveauEauDroite.add(num2);
                    niveauEau2[8] = num2;
                    break;
                case 5:
                    //num3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    num3.setPreferredSize(new Dimension(90, contenantNiveauEauGauche.getHeight() / 12));
                    num3.setFont(new Font(num3.getFont().getName(), Font.PLAIN, 35)); //on garde la police utilisé par l'objet           
                    contenantNiveauEauDroite.add(num3);
                    niveauEau2[5] = num3;
                    break;
                case 3:
                    //num4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    num4.setPreferredSize(new Dimension(90, contenantNiveauEauGauche.getHeight() / 12));
                    num4.setFont(new Font(num4.getFont().getName(), Font.PLAIN, 35)); //on garde la police utilisé par l'objet           
                    contenantNiveauEauDroite.add(num4);
                    niveauEau2[3] = num4;
                    break;
                case 1:
                    //num5.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    //num5.setBackground(Color.red);
                    num5.setPreferredSize(new Dimension(90, contenantNiveauEauGauche.getHeight() / 12));
                    num5.setFont(new Font(num5.getFont().getName(), Font.PLAIN, 35)); //on garde la police utilisé par l'objet    

                    contenantNiveauEauDroite.add(num5);
                    niveauEau2[1] = num5;
                    break;
                default:
                    JLabel numEmpty = new JLabel("");
                    //numEmpty.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    numEmpty.setPreferredSize(new Dimension(90, contenantNiveauEauGauche.getHeight() / 12));

                    contenantNiveauEauDroite.add(numEmpty);
                    niveauEau2[i] = numEmpty;
                    break;
            }
        }

        //MENU PRINCPALE
        contenantNiveauEauMain.add(contenantNiveauEauGauche, BorderLayout.WEST);
        contenantNiveauEauMain.add(contenantNiveauEauDroite, BorderLayout.EAST);

        contenantNiveauEauMain.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        BorderLayoutPrincipal.add(contenantNiveauEauMain, BorderLayout.WEST);

        ColoriserNiveauEau();
        /**
         * ***************************************************************************************************
         */
        //JLabel labelTitre = new JLabel("Ile Interdite");
        //labelTitre.setForeground(Color.BLUE);
        //labelTitre.setFont(new Font(labelTitre.getFont().getName(), labelTitre.getFont().getStyle(), (int) (labelTitre.getFont().getSize() * 1.5)));
        //panelHaut.add(labelTitre);

        /**
         * AJOUT DE LA GRILLE DE JEU AU CENTRE DE LA FENETRE *
         */
        JPanel panelGrille = new JPanel(new GridLayout(6, 6));
        BorderLayoutPrincipal.add(panelGrille, BorderLayout.CENTER);

        JButton jb = new JButton("Augmenter niveau eau");
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cj.augmenterNiveauEau();
                System.out.println(cj.getNombreCarteInondationAPiocher());
            }
        });

        //*****PANELGAMEPAD *********************
        joueurActuel = new JLabel("", SwingConstants.CENTER);
        ActionRestante = new JLabel("", SwingConstants.CENTER);
        panelGamePad = new JPanel(new BorderLayout());

        JPanel panelTresHaut = new JPanel();
        BoxLayout bl = new BoxLayout(panelTresHaut, BoxLayout.PAGE_AXIS);
        panelTresHaut.setLayout(bl);
        JLabel labelpion = new JLabel("Tour du joueur :", SwingConstants.CENTER);
        labelpion.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        Font font1 = new Font("Arial", Font.BOLD, 25);
        labelpion.setFont(font1);
        //JPanel panelHautGamePad = new JPanel(new GridLayout(2,1)); 
        JLabel labelAction = new JLabel("Action(s) restante(s):", SwingConstants.CENTER);
        labelAction.setFont(font1);
        labelAction.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        Font font2 = new Font("Arial", Font.BOLD, 100);
        ActionRestante.setFont(font2);
        ActionRestante.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panelTresHaut.add(labelpion, Component.CENTER_ALIGNMENT);
        joueurActuel.setFont(font1);
        joueurActuel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panelTresHaut.add(joueurActuel);
        panelTresHaut.add(labelAction, Component.CENTER_ALIGNMENT);
        panelTresHaut.add(ActionRestante, Component.CENTER_ALIGNMENT);
        panelGamePad.add(panelTresHaut, BorderLayout.NORTH);

        //panelGamePad.add(panelHautGamePad, BorderLayout.CENTER); 
        //******************************************************************
        JPanel panelBottomGamePad = new JPanel(new GridLayout(0, cj.getNombreJoueurDansPartie()));

        affichagePerso1 = new AffichagePersonnage(this, listPerso.get(0));
        affichagePerso2 = new AffichagePersonnage(this, listPerso.get(1));
        panelBottomGamePad.add(affichagePerso1);
        panelBottomGamePad.add(affichagePerso2);

        if (cj.getNombreJoueurDansPartie() > 2) {
            affichagePerso3 = new AffichagePersonnage(this, listPerso.get(2));
            panelBottomGamePad.add(affichagePerso3);
        } else {
            affichagePerso3 = new AffichagePersonnage(this, null);
        }
        if (cj.getNombreJoueurDansPartie() > 3) {
            affichagePerso4 = new AffichagePersonnage(this, listPerso.get(3));
            panelBottomGamePad.add(affichagePerso4);
        } else {
            affichagePerso4 = new AffichagePersonnage(this, null);
        }

        BorderLayoutPrincipal.add(panelBottomGamePad, BorderLayout.SOUTH);

        JPanel panelGamePadBas = new JPanel();
        panelGamePadBas.setLayout(new BoxLayout(panelGamePadBas, BoxLayout.Y_AXIS));

        JPanel panelTresor = new JPanel(new GridLayout(2, 2));
        panelTresor.add(imgCalice);
        panelTresor.add(imgCristal);
        panelTresor.add(imgPierre);
        panelTresor.add(imgZephyr);

        panelGamePadBas.add(panelTresor);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(listBasGamePad);
        scrollPane.setPreferredSize(new Dimension(/*scrollPane.getWidth()*/300, 150));

        panelGamePadBas.add(new JLabel("Historique:", SwingConstants.CENTER));
        panelGamePadBas.add(scrollPane);
        panelGamePad.add(panelGamePadBas, BorderLayout.SOUTH);

        BorderLayoutPrincipal.add(panelGamePad, BorderLayout.EAST);

        affecterCase(plateau, listPion, panelGrille);
        updateGamePad();
    }

    public void affecterCase(Tuile plateau[][], ArrayList<Pion> listPion, JPanel grille) {
        int nbTresor = 0;

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

                        for (String k : listTresor.keySet()) {
                            if (plateau[i][j].getNom() == k) {
                                //JLabel labT = new JLabel(listTresor.get(k).getNom());
                                //labT.setOpaque(true);
                                //labT.setBackground(listTresor.get(k).getCouleur());
                                //pn.add(labT);
                                pn.add(listTresor.get(k).getLabel());
                            }
                        }

                        //pn.add(new Tresor(plateau[i][j].getTresor()));
                    }
                } else {
                    pn.setBackground(emptyColor);       //background en blanc
                }

                if (pn.getBackground() != emptyColor) {
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
                }
                panel[i][j] = pn;
                grille.add(pn, i, j);        //Ajout de la case a la grille de jeu (panelGrille)
            }
        }
        paintNormal();
    }

    //met à jour les informations du gamepad en fonction de l'état du jeu
    private void updateGamePad() {
        joueurActuel.setText(cj.getNomJoueur());
        ActionRestante.setText(Integer.toString((int) cj.getNbActionRestante()));
        listBasGamePad.setListData(historiqueAction.toArray());

        switch (cj.getJoueurNum()) {
            case 0:
                affichagePerso1.update(true);
                affichagePerso2.update(false);
                affichagePerso3.update(false);
                affichagePerso4.update(false);
                break;
            case 1:
                affichagePerso1.update(false);
                affichagePerso2.update(true);
                affichagePerso3.update(false);
                affichagePerso4.update(false);
                break;
            case 2:
                affichagePerso1.update(false);
                affichagePerso2.update(false);
                affichagePerso3.update(true);
                affichagePerso4.update(false);
                break;
            case 3:
                affichagePerso1.update(false);
                affichagePerso2.update(false);
                affichagePerso3.update(false);
                affichagePerso4.update(true);
                break;
        }
    }

    //indique l'état de l'interface plateau.
    public void changeMode(int mode) {
        oldMode = this.mode;
        this.mode = mode;
    }

    public ControleurJeuSecondaire getControleurJeu() {
        return cj;
    }

    //est appeller quand une action est fini
    private void actionFinished() {
        paintNormal();
        mode = 0;
        updateGamePad();
        setBtAssecherText(nomButtonAssecher);
        setBtDeplacementText(nomButtonDeplacement);
        setBtActionSpecialText(nomActionSpecialNavigateur);
        setBtAssecherEnabled(true);
        setBtDeplacementEnabled(true);
        setBtPasserJoueurEnabled(true);
        window.repaint();
    }

    //utilisé par les boutons déplacer et assécher afin de changer l'affichage du plateau
    public void gamePadClick() {
        //System.out.println("deplacement = " + mode + " OLD:" + oldMode);

        //si jamais l'ancien et le mode actuel sont égaux (donc potentiellement, l'utilisateur appuie deux fois sur "se deplacer")
        //on remet le mode à 0 afin d'annuler action et de redéssiner le plateau normalement
        if (oldMode == mode) {
            mode = 0;
        }

        switch (mode) {
            case 0: //normal
                actionFinished();
                break;
            case 1: //se deplacer
                paintNonSelected();
                setBtAssecherEnabled(false);
                setBtDeplacementEnabled(true);
                setBtPasserJoueurEnabled(false);
                setBtCarteActionEnabled(false);
                setBtDeplacementText(nomAnnulé);//Desactive tous les boutons

                for (Tuile t : cj.getJoueurEntrainDeJouer().getDeplacements()) {
                    JPanel jpa = panel[t.getX()][t.getY()];
                    jpa.setBackground(tuileColor);
                }
                break;
            case 2:
                paintNonSelected();

                setBtAssecherEnabled(true);
                setBtDeplacementEnabled(false);
                setBtPasserJoueurEnabled(false);
                setBtCarteActionEnabled(false);
                setBtAssecherText(nomAnnulé);//Desactive tous les boutons

                for (Tuile t : cj.getJoueurEntrainDeJouer().getTuileQuiPeutSecher()) {
                    JPanel jpa = panel[t.getX()][t.getY()];
                    jpa.setBackground(tuileColor);
                }
                break;

            case 3: //Se deplacer avec une carte action helicoptere         
                paintNonSelected();
                setBtAssecherEnabled(false);
                setBtDeplacementEnabled(false);
                setBtPasserJoueurEnabled(false);
                setBtCarteActionEnabled(false);//Desactive tous les boutons

                //  Affiche toute les case non inondé, donc praticable
                for (Tuile t : grille.getListTuile()) {
                    if (t.getInondation() != TypeEnumInondation.INONDE) {
                        JPanel jpa = panel[t.getX()][t.getY()];
                        jpa.setBackground(tuileColor);
                    }
                }
                //  Notifie l'observateur de l'action
                cj.notifierObservateur(new Message(TypeEnumMessage.HISTORIQUE, "Carte Helicoptere utilisée"));
                break;

            case 4: //Assecher avec une carte action sac de sable
                paintNonSelected();
                setBtAssecherEnabled(false);
                setBtDeplacementEnabled(false);
                setBtPasserJoueurEnabled(false);   //Desactive tous les boutons

                //Affiche toute les case mouille, donc sechable
                for (Tuile t : grille.getListTuile()) {
                    if (t.getInondation() == TypeEnumInondation.MOUILLE) {
                        JPanel jpa = panel[t.getX()][t.getY()];
                        jpa.setBackground(tuileColor);
                    }
                }
                //  Notifie l'observateur de l'action
                cj.notifierObservateur(new Message(TypeEnumMessage.HISTORIQUE, "Carte Sac de Sable utilisée"));
            case 5: //Si un  perseo est dans l'eau et doit sortir
                paintNonSelected();
                setBtAssecherEnabled(false);
                setBtDeplacementEnabled(false);
                setBtPasserJoueurEnabled(false);
                setBtCarteActionEnabled(false);//Desactive tous les boutons

                for (Tuile t : cj.getJoueurEntrainDeJouer().getDeplacements()) {
                    JPanel jpa = panel[t.getX()][t.getY()];
                    jpa.setBackground(tuileColor);
                }
                break;
            case 6:
                final ArrayList<String> arpseudos = new ArrayList<>();
                for (Personnage p : cj.getPerso()) {
                    arpseudos.add(p.getNom());
                }
                arpseudos.remove(cj.getJoueurEntrainDeJouer().getNom());

                final JComboBox<String> combo = new JComboBox<>(arpseudos.toArray(new String[0]));

                String[] options = {"OK", "Cancel"};

                String title = "Sélectionner un joueur";
                int selection = JOptionPane.showOptionDialog(null, combo, title,
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options,
                        options[0]);

                if (selection == 0) {
                    System.out.println("selection is: " + options[selection]);

                    paintNonSelected();
                    setBtAssecherEnabled(false);
                    setBtDeplacementEnabled(false);
                    setBtPasserJoueurEnabled(false);
                    //setBtAssecherText(nomAnnulé);   //Desactive tous les boutons
                    setBtActionSpecialText(nomAnnulé);

                    //Affiche toute les case mouille, donc sechable
                    selectedPerso = cj.getJoueur((String) combo.getSelectedItem());
                    int px = selectedPerso.getEmplacement().getX();
                    int py = selectedPerso.getEmplacement().getY();

                    for (int x = -2; x <= 2; x++) {
                        for (int y = -2; y <= 2; y++) {
                            if (px + x <= 5 && px + x >= 0 && py + y <= 5 && py + y >= 0) {
                                if (!((x == -2 && y == -2) || (x == -2 && y == 2) || (x == 2 && y == -2) || (x == 2 && y == 2) ||
                                        (x == -2 && y == -1) || (x == -2 && y == 1) || (x == 2 && y == -1) || (x == 2 && y == 1) ||
                                        (x == -1 && y == -2) || (x == -1 && y == 2) || (x == 1 && y == -2) || (x == 1 && y == 2))) {
                                    if (plateau[px + x][py + y] != null) {
                                        JPanel jpa = panel[px + x][py + y];
                                        jpa.setBackground(tuileColor);
                                    }
                                }
                            }
                        }
                    }

                } else {
                    actionFinished();
                }
                break;
        }
    }

    //ce déclenche quand une case sur l'écran est cliquer
    private void panelClick(JPanel jp, Tuile emplacement, int i, int j) {
        //System.out.println("panelClick: " + i + ", " + j);
        //System.out.println("deplacement = " + mode);
        if (jp.getBackground() != nonSelectedColor && emplacement.getInondation() != TypeEnumInondation.INONDE) {
            int x, y;
            switch (mode) {
                case 1: //se deplacer
                    //System.out.println("Moving");
                    x = cj.getJoueurEntrainDeJouer().getEmplacement().getX();
                    y = cj.getJoueurEntrainDeJouer().getEmplacement().getY();
                    panel[x][y].remove(listPion.get(cj.getJoueurNum()));
                    panel[i][j].add(listPion.get(cj.getJoueurNum()));
                    cj.deplacerJoueurEnCours(emplacement);
                    break;
                case 2:
                    //System.out.println("Assechement");
                    cj.assecher(emplacement);
                    break;
                case 3: //Se deplacer avec une carte action helicoptere
                    x = cj.getJoueurEntrainDeJouer().getEmplacement().getX();
                    y = cj.getJoueurEntrainDeJouer().getEmplacement().getY();
                    panel[x][y].remove(listPion.get(cj.getJoueurNum()));
                    panel[i][j].add(listPion.get(cj.getJoueurNum()));
                    cj.deplacerJoueurEnCours(emplacement);
                    cj.setNbAction(cj.getNbActionRestante() + 1);//Car utiliser une carte action ne coute pas de point d'action
                    break;
                case 4: //Assecher avece une carte action
                    cj.assecher(emplacement);
                    cj.setNbAction(cj.getNbActionRestante() + 1);//Car utiliser une carte action ne coute pas de point d'action
                    break;
                case 5:
                    x = cj.getJoueurEntrainDeJouer().getEmplacement().getX();
                    y = cj.getJoueurEntrainDeJouer().getEmplacement().getY();
                    panel[x][y].remove(listPion.get(cj.getJoueurNum()));
                    panel[i][j].add(listPion.get(cj.getJoueurNum()));
                    cj.deplacerJoueurEnCours(emplacement);
                    break;
                case 6:
                    cj.deplacerJoueur(selectedPerso, emplacement);
                    cj.decrementAction();
                    updatePion();
                    paintNormal();
                    break;
            }
            actionFinished();
        }

    }

    //met à jour les emplacements des pions
    public void updatePion() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                for (Pion p : listPion) {
                    panel[i][j].remove(p);
                }
            }
        }
        for (Pion p : listPion) {
            int x = p.getPerso().getEmplacement().getX();
            int y = p.getPerso().getEmplacement().getY();

            panel[x][y].add(p);
        }

    }

    //  BOUTON ASSECHER
    private void setBtAssecherText(String t) {
        switch (cj.getJoueurNum()) {
            case 0:
                affichagePerso1.setButtonAssecherText(t);
                break;
            case 1:
                affichagePerso2.setButtonAssecherText(t);
                break;
            case 2:
                affichagePerso3.setButtonAssecherText(t);
                break;
            case 3:
                affichagePerso4.setButtonAssecherText(t);
                break;
        }
    }

    private void setBtAssecherEnabled(boolean b) {
        switch (cj.getJoueurNum()) {
            case 0:
                affichagePerso1.setButtonAssecherEnabled(b);
                break;
            case 1:
                affichagePerso2.setButtonAssecherEnabled(b);
                break;
            case 2:
                affichagePerso3.setButtonAssecherEnabled(b);
                break;
            case 3:
                affichagePerso4.setButtonAssecherEnabled(b);
                break;
        }
    }

    //  BOUTON DEPLACEMENT
    private void setBtDeplacementText(String t) {
        switch (cj.getJoueurNum()) {
            case 0:
                affichagePerso1.setButtonDeplacementText(t);
                break;
            case 1:
                affichagePerso2.setButtonDeplacementText(t);
                break;
            case 2:
                affichagePerso3.setButtonDeplacementText(t);
                break;
            case 3:
                affichagePerso4.setButtonDeplacementText(t);
                break;
        }
    }

    private void setBtDeplacementEnabled(boolean b) {
        switch (cj.getJoueurNum()) {
            case 0:
                affichagePerso1.setButtonDeplacementEnabled(b);
                break;
            case 1:
                affichagePerso2.setButtonDeplacementEnabled(b);
                break;
            case 2:
                affichagePerso3.setButtonDeplacementEnabled(b);
                break;
            case 3:
                affichagePerso4.setButtonDeplacementEnabled(b);
                break;
        }
    }

    //  BOUTON PASSER JOUEUR
    private void setBtPasserJoueurEnabled(boolean b) {
        switch (cj.getJoueurNum()) {
            case 0:
                affichagePerso1.setButtonPasserTourEnabled(b);
                break;
            case 1:
                affichagePerso2.setButtonPasserTourEnabled(b);
                break;
            case 2:
                affichagePerso3.setButtonPasserTourEnabled(b);
                break;
            case 3:
                affichagePerso4.setButtonPasserTourEnabled(b);
                break;
        }
    }

    //  BOUTON DONNER CARTE
    private void setBtDonnerCarteText(String text) {
        switch (cj.getJoueurNum()) {
            case 0:
                affichagePerso1.setButtonDonnerCarteText(text);
                break;
            case 1:
                affichagePerso2.setButtonDonnerCarteText(text);
                break;
            case 2:
                affichagePerso3.setButtonDonnerCarteText(text);
                break;
            case 3:
                affichagePerso4.setButtonDonnerCarteText(text);
                break;
        }
    }

    private void setBtCarteActionEnabled(boolean b) {
        switch (cj.getJoueurNum()) {
            case 0:
                affichagePerso1.setBtCarteActionEnabled(b);
                break;
            case 1:
                affichagePerso2.setBtCarteActionEnabled(b);
                break;
            case 2:
                affichagePerso3.setBtCarteActionEnabled(b);
                break;
            case 3:
                affichagePerso4.setBtCarteActionEnabled(b);
                break;
        }
    }

    //  BOUTON DONNER CARTE
    private void setBtActionSpecialText(String text) {
        switch (cj.getJoueurNum()) {
            case 0:
                affichagePerso1.setBtActionSpecialText(text);
                break;
            case 1:
                affichagePerso2.setBtActionSpecialText(text);
                break;
            case 2:
                affichagePerso3.setBtActionSpecialText(text);
                break;
            case 3:
                affichagePerso4.setBtActionSpecialText(text);
                break;
        }
    }

    private void setBtDonnerCarteParDefaut() {
        affichagePerso1.setButtonDonnerCarteEnabled(false);
        affichagePerso2.setButtonDonnerCarteEnabled(false);
        affichagePerso3.setButtonDonnerCarteEnabled(false);
        affichagePerso4.setButtonDonnerCarteEnabled(false);
    }

    private void setBtDonnerCarteEnabled() {
        switch (cj.getJoueurNum()) {
            case 0:
                affichagePerso1.setButtonDonnerCarteEnabled(true);
                break;
            case 1:
                affichagePerso2.setButtonDonnerCarteEnabled(true);
                break;
            case 2:
                affichagePerso3.setButtonDonnerCarteEnabled(true);
                break;
            case 3:
                affichagePerso4.setButtonDonnerCarteEnabled(true);
                break;
        }
    }

    private void ColoriserNiveauEau() {
        for (int i = max; i >= 0; i--) {
            niveauEau[i].setOpaque(true);
            niveauEau[i].setForeground(echetteText);
            niveauEau2[i].setOpaque(true);
            niveauEau2[i].setForeground(echetteText);
            if (i == max || i == max - 1) {
                niveauEau[i].setBackground(echelleBleu2);
                niveauEau2[i].setBackground(echelleBleu2);
            } else if (i <= max - 2 && i >= max - 4) {
                niveauEau[i].setBackground(echelleBleu3);
                niveauEau2[i].setBackground(echelleBleu3);
            } else if (i <= max - 5 && i >= max - 6) {
                niveauEau[i].setBackground(echelleBleu4);
                niveauEau2[i].setBackground(echelleBleu4);
            } else if (i <= max - 7 && i >= max - 8) {
                niveauEau[i].setBackground(echelleBleu5);
                niveauEau2[i].setBackground(echelleBleu5);
            } else if (i == max - 9) {
                niveauEau[i].setBackground(echelleDeath);
                niveauEau[i].setForeground(echetteRed);
                niveauEau2[i].setBackground(echelleDeath);
                niveauEau2[i].setForeground(echetteRed);
            }
        }
        niveauEau[max - cj.getNiveauEau()].setBackground(echelleMarqueur);
        niveauEau[max - cj.getNiveauEau()].setForeground(Color.black);
        niveauEau2[max - cj.getNiveauEau()].setBackground(echelleMarqueur);
        niveauEau2[max - cj.getNiveauEau()].setForeground(Color.black);
    }

    //tout le plateau devient gris
    private void paintNonSelected() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                JPanel jp = panel[i][j];
                if (jp.getBackground() != emptyColor) {
                    jp.setBackground(nonSelectedColor);
                }
                if (plateau[i][j] != null && listTresor.get(plateau[i][j].getNom()) != null) {
                    listTresor.get(plateau[i][j].getNom()).setColorGris();
                }
            }
        }
        for(String i : listTresor.keySet())
        {
            listTresor.get(i).getLabel().setBackground(emptyColor);
        }
        window.repaint();
    }

    //tout le plateau retourne à ça couleur "normal"
    private void paintNormal() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                JPanel jp = panel[i][j];
                if (plateau[i][j] != null && jp.getBackground() != emptyColor) {
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
                    if (listTresor.get(plateau[i][j].getNom()) != null) {
                        listTresor.get(plateau[i][j].getNom()).setColorBack();
                    }
                }
            }
        }
        for(String i : listTresor.keySet())
        {
            listTresor.get(i).setColorBack();
        }
        window.repaint();
    }

    //ajoute un message à l'historique de partie ET met à jour l'interface (pour afficher les majs
    public void ajouterMessageHistorique(String text) {
        historiqueAction.add(text);
        updateGamePad();
    }

    public void ajouterMessageHistorique(ArrayList<String> text) {
        for (String t : text) {
            historiqueAction.add(t);
        }
        updateGamePad();
    }

    public void afficher() {
        updateGamePad();
        paintNormal();
        this.window.setVisible(true);
    }

    @Override
    public void traiterMessage(Message m) {
        //System.out.println("MESSAGE");
        switch (m.getMessageType()) {
            case JOUEUR_SUIVANT:
                ajouterMessageHistorique("Passage au joueur suivant");
                updateGamePad();
                //System.out.println(cj.getJoueurNum());
                break;
            case CHANGEMENT_NIVEAU_EAU:
                //Media hit = new Media(new File("src/RessourcesJoueur/NiveauEauSon.mp3").toURI().toString());
                //mediaPlayer = new MediaPlayer(hit);          //créer le media player
                //mediaPlayer.play();
                ColoriserNiveauEau();
                break;
            case PIOCHE_CARTE_INONDATION:
                ArrayList t = new ArrayList<>();
                t.add("Carte Inondation piochée : ");
                for (CarteInondation ci : (ArrayList<CarteInondation>) m.getAdditionnal()) {
                    t.add(" - " + ci.getNom());
                }
                t.add("\n");
                ajouterMessageHistorique(t);
                paintNormal();
                break;
            case HISTORIQUE:
                if (!m.getMessage().isEmpty()) {
                    ajouterMessageHistorique(m.getMessage());
                } else {
                    if (!m.getAdditionnal().isEmpty()) {
                        ajouterMessageHistorique(m.getAdditionnal());
                    }
                }
                break;
            case NOUVEAU_TOUR:
                updateGamePad();
                ajouterMessageHistorique("\n");
                ajouterMessageHistorique("Nouveau tour");
                break;
            case UPDATE_GUI:
                updateGamePad();
                paintNormal();
                break;
            case FIN_PARTIE:
                JOptionPane.showMessageDialog(null, "Partie perdue : " + m.getMessage(), "Fin de partie", JOptionPane.ERROR_MESSAGE);
                window.setVisible(false);
                System.exit(0);
                break;
            case PARTIE_GAGNE:
                JOptionPane.showMessageDialog(null, "Partie gagnée !", "Fin de partie", JOptionPane.INFORMATION_MESSAGE);
                window.setVisible(false);
                System.exit(0);
                break;
            case RM_TRESOR:
                //Media hite = new Media(new File("src/RessourcesJoueur/tresorGagne.mp3").toURI().toString());
                //mediaPlayer = new MediaPlayer(hite);          //créer le media player
                //mediaPlayer.play();
                for (String i : listTresor.keySet()) {
                    if (m.getEmplacementJoueur().getNom().equals(i)) {
                        listTresor.get(i).getLabel().setVisible(false);
                    }
                }
                if (cj.getTresorCaliceOnde()) {
                    imgCalice.setImage(cheminCalice);
                }
                if (cj.getTresorCristalArdent()) {
                    imgCristal.setImage(cheminCristal);
                }
                if (cj.getTresorPierreSacre()) {
                    imgPierre.setImage(cheminPierre);
                }
                if (cj.getTresorStatueZephyr()) {
                    imgZephyr.setImage(cheminZephyr);
                }
                updateGamePad();
                window.repaint();
                break;
            case PERSO_DANS_EAU:
                changeMode(5);
                gamePadClick();
        }
    }

}
