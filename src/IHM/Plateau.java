/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Cartes.CarteInondation;
import Enumerations.TypeEnumTresors;
import IleInterdite.ControleurJeuSecondaire;
import IleInterdite.Grille;
import IleInterdite.Message;
import IleInterdite.Observateur;
import Personnages.*;
import IleInterdite.Tuile;
import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.border.Border;

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
    private int mode = 0; //0: aucun, 1: deplacement, 2: assecher
    private int oldMode = 0; //permet de détecter les changement dans la variable mode

    private final static Color emptyColor = new Color(255, 255, 255); //couleur case vide
    private final static Color tuileMouilee = new Color(52, 152, 219);
    private final static Color tuileInondee = emptyColor;
    private final static Color selectColor = new Color(12, 175, 12);
    private final static Color tuileColor = new Color(243, 156, 18);
    private final static Color nonSelectedColor = Color.gray;

    private final static Color echelleDeath = Color.white;
    private final static Color echelleBleu5 = new Color(27, 79, 114);
    private final static Color echelleBleu4 = new Color(40, 116, 166);
    private final static Color echelleBleu3 = new Color(46, 134, 193);
    private final static Color echelleBleu2 = new Color(52, 152, 219);
    private final static Color echelleMarqueur = Color.yellow;
    private final static Color echetteText = Color.white;
    private final static Color echetteRed = Color.red;

    private final JFrame window;
    private JLabel niveauEau[];
    private JLabel niveauEau2[];
    
    private JPanel contenantNiveauEauMain;
    private JPanel contenantNiveauEauGauche;
    private JPanel contenantNiveauEauDroite;
    
    private AffichagePersonnage affichagePerso1;
    private AffichagePersonnage affichagePerso2;
    private AffichagePersonnage affichagePerso3;
    private AffichagePersonnage affichagePerso4;
    
    private final static String nomButtonDeplacement = AffichagePersonnage.nomButtonDeplacement;
    private final static String nomButtonAssecher = AffichagePersonnage.nomButtonAssecher;
    private final static String nomAnnulé = AffichagePersonnage.nomAnnulé;
    
    //objet liste
    private JList listBasGamePad = new JList();
    //liste contenant l'historique du jeu
    private ArrayList<String> historiqueAction = new ArrayList<>();
    
    private final int max = 9; //taille frise inondation
    //private int niveauEaucompteur = max;

    public Plateau(ArrayList<Personnage> persos, ControleurJeuSecondaire cj) {
        plateau = cj.getGrille();
        this.cj = cj;
        grille = cj.getIle();
        listPion = new ArrayList<Pion>();
        listPerso = persos;
        for (Personnage p : listPerso) {
            listPion.add(new Pion(p));
        }
        
        for(int i = 0; i < grille.getListTuile().size(); i++)
        {
            if(grille.getListTuile().get(i).getTresor() != TypeEnumTresors.AUCUN)
            {
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
            //System.out.println(i);
            //System.out.println(max - i);
            //System.out.println();
            niveauEau[max - i]=new JLabel(/*Integer.toString(i)*/"", SwingConstants.CENTER);
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
        
        //MENU DROITE
        JLabel num2 = new JLabel("2", SwingConstants.RIGHT); 
        JLabel num3 = new JLabel("3", SwingConstants.RIGHT); 
        JLabel num4 = new JLabel("4", SwingConstants.RIGHT); 
        JLabel num5 = new JLabel("5", SwingConstants.RIGHT); 
        
        niveauEau2 = new JLabel[max + 1];
        
        for (int i = 0; i <= max; i++) {
            switch(i) {
                case 8:
                    //num2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    num2.setPreferredSize(new Dimension(90, contenantNiveauEauGauche.getHeight()/12));
                    num2.setFont(new Font(num2.getFont().getName(), Font.PLAIN, 35)); //on garde la police utilisé par l'objet                    
                    contenantNiveauEauDroite.add(num2);
                    niveauEau2[8] = num2;
                    break;
                case 5:
                    //num3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    num3.setPreferredSize(new Dimension(90, contenantNiveauEauGauche.getHeight()/12));
                    num3.setFont(new Font(num3.getFont().getName(), Font.PLAIN, 35)); //on garde la police utilisé par l'objet           
                    contenantNiveauEauDroite.add(num3);
                    niveauEau2[5] = num3;
                    break;
                case 3:
                    //num4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    num4.setPreferredSize(new Dimension(90, contenantNiveauEauGauche.getHeight()/12));
                    num4.setFont(new Font(num4.getFont().getName(), Font.PLAIN, 35)); //on garde la police utilisé par l'objet           
                    contenantNiveauEauDroite.add(num4);
                    niveauEau2[3] = num4;
                    break;
                case 1:
                    //num5.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    //num5.setBackground(Color.red);
                    num5.setPreferredSize(new Dimension(90, contenantNiveauEauGauche.getHeight()/12));
                    num5.setFont(new Font(num5.getFont().getName(), Font.PLAIN, 35)); //on garde la police utilisé par l'objet    
                    
                    contenantNiveauEauDroite.add(num5);
                    niveauEau2[1] = num5;
                    break;
                default:
                    JLabel numEmpty = new JLabel("");
                    //numEmpty.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    numEmpty.setPreferredSize(new Dimension(90, contenantNiveauEauGauche.getHeight()/12));
                    
                    contenantNiveauEauDroite.add(numEmpty);
                    niveauEau2[i] = numEmpty;
                    break;
            }
        }
        
        //MENU PRINCPALE
        contenantNiveauEauMain.add(contenantNiveauEauGauche, BorderLayout.WEST);
        contenantNiveauEauMain.add(contenantNiveauEauDroite, BorderLayout.EAST);
        
        contenantNiveauEauMain.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        mainPanel.add(contenantNiveauEauMain, BorderLayout.WEST);
        
        ColoriserNiveauEau();
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
        
        JButton jb = new JButton("Augmenter niveau eau");
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cj.augmenterNiveauEau();
                System.out.println(cj.getNombreCarteInondationAPiocher());
            }
        });
        
        joueurActuel = new JLabel("");
        ActionRestante = new JLabel();

        panelGamePad = new JPanel(new BorderLayout());
        JPanel panelHautGamePad = new JPanel(new GridLayout(0, 2)); 
        panelHautGamePad.add(new JLabel("Action restante:"));
        panelHautGamePad.add(ActionRestante);
        
        panelHautGamePad.add(jb);

        panelGamePad.add(panelHautGamePad, BorderLayout.NORTH);
  
        JPanel panelMilieuGamePad = new JPanel(new GridLayout(0,cj.getNombreJoueurDansPartie()));
        
        affichagePerso1 = new AffichagePersonnage(this, listPerso.get(0));
        affichagePerso2 = new AffichagePersonnage(this, listPerso.get(1));
        panelMilieuGamePad.add(affichagePerso1);
        panelMilieuGamePad.add(affichagePerso2);
        
        if (cj.getNombreJoueurDansPartie() > 2) {
            affichagePerso3 = new AffichagePersonnage(this, listPerso.get(2));
            panelMilieuGamePad.add(affichagePerso3);
        } else {
            affichagePerso3 = new AffichagePersonnage(this, null);
        }
        if (cj.getNombreJoueurDansPartie() > 3) {
            affichagePerso4 = new AffichagePersonnage(this, listPerso.get(3));
            panelMilieuGamePad.add(affichagePerso4);
        } else {
            affichagePerso4 = new AffichagePersonnage(this, null);
        }

        mainPanel.add(panelMilieuGamePad, BorderLayout.SOUTH);
        
        JPanel panelGamePadBas = new JPanel();
        panelGamePadBas.setLayout(new BoxLayout(panelGamePadBas, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(listBasGamePad);
        scrollPane.setPreferredSize(new Dimension(scrollPane.getWidth(), 150));

        panelGamePadBas.add(new JLabel("Historique:", SwingConstants.CENTER));
        panelGamePadBas.add(scrollPane, BorderLayout.SOUTH);
        panelGamePad.add(panelGamePadBas, BorderLayout.SOUTH);
        mainPanel.add(panelGamePad, BorderLayout.EAST);

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

                        for(String k : listTresor.keySet())
                        {
                            if(plateau[i][j].getNom() == k)
                            {
                                pn.add(listTresor.get(k));
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
        ActionRestante.setText(Integer.toString((int)cj.getNbActionRestante()));
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
    
    public ControleurJeuSecondaire getControleurJeu()  {
        return cj;
    }

    //est appeller quand une action est fini
    private void actionFinished() {
        paintNormal();
        mode = 0;
        updateGamePad();
        setBtAssecherText(nomButtonAssecher);
        setBtDeplacementText(nomButtonDeplacement);
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
                //System.out.println("coucou");
                actionFinished();
                break;
            case 1: //se deplacer
                paintNonSelected();
                setBtAssecherEnabled(false);
                setBtDeplacementEnabled(true);
                setBtPasserJoueurEnabled(false);
                setBtDeplacementText(nomAnnulé);

                for (Tuile t : cj.getJoueurEntrainDeJouer().getDeplacements()) {
                    JPanel jpa = panel[t.getX()][t.getY()];
                    jpa.setBackground(tuileColor);
                    //System.out.println(t.getNom() + "\t" + t.getX() + "\t" + t.getY());
                }
                break;
            case 2:
                paintNonSelected();
                
                setBtAssecherEnabled(true);
                setBtDeplacementEnabled(false);
                setBtPasserJoueurEnabled(false);
                setBtAssecherText(nomAnnulé);

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
        //System.out.println("panelClick: " + i + ", " + j);
        //System.out.println("deplacement = " + mode);
        if (jp.getBackground() != nonSelectedColor) {
            switch (mode) {
                case 1: //se deplacer
                    //System.out.println("Moving");
                    int x = cj.getJoueurEntrainDeJouer().getEmplacement().getX();
                    int y = cj.getJoueurEntrainDeJouer().getEmplacement().getY();
                    panel[x][y].remove(listPion.get(cj.getJoueurNum()));
                    panel[i][j].add(listPion.get(cj.getJoueurNum()));
                    cj.deplacerJoueurEnCour(emplacement);
                    break;
                case 2:
                    //System.out.println("Assechement");
                    cj.assecher(emplacement);
                    break;
            }
            actionFinished();
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
    
    private void setBtDonnerCarteEnabled(boolean b) {
        switch (cj.getJoueurNum()) {
            case 0:
                affichagePerso1.setButtonDonnerCarteEnabled(b);
                break;
            case 1:
                affichagePerso2.setButtonDonnerCarteEnabled(b);
                break;
            case 2:
                affichagePerso3.setButtonDonnerCarteEnabled(b);
                break;
            case 3:
                affichagePerso4.setButtonDonnerCarteEnabled(b);
                break;
        }
    }
    
    private void ColoriserNiveauEau() {
        for (int i = max; i >= 0; i--) {
            niveauEau[i].setOpaque(true);
            niveauEau[i].setForeground(echetteText);
            niveauEau2[i].setOpaque(true);
            niveauEau2[i].setForeground(echetteText);
            if (i == max || i == max-1) {
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
                ColoriserNiveauEau();
                break;
            case PIOCHE_CARTE_INONDATION:
                ArrayList t = new ArrayList<>();
                t.add("Carte Inondation piocher:");
                for (CarteInondation ci : (ArrayList<CarteInondation>)m.getAdditionnal()) {
                    t.add(" - " + ci.getNom());
                }
                t.add("\n");
                ajouterMessageHistorique(t);
                paintNormal();
                break;
            case HISTORIQUE:
                if(!m.getMessage().isEmpty())
                {
                    ajouterMessageHistorique(m.getMessage());
                }
                else
                {
                    if(!m.getAdditionnal().isEmpty())
                    {
                        ajouterMessageHistorique(m.getAdditionnal());
                    }
                }
                break;
            case NOUVEAU_TOUR:
                ajouterMessageHistorique("\n");
                ajouterMessageHistorique("Nouveau tour");
                break;
            case UPDATE_GUI:
                updateGamePad();
                break;
            case FIN_PARTIE:
                System.out.println("Fin partie : ");
                break;
            case PARTIE_GAGNE:
                System.out.println("Fin partie : "+m.getMessage());
                break;
            case RM_TRESOR:
                
                for(String i : listTresor.keySet())
                {
                    if(m.getEmplacementJoueur().getNom() == i)
                    {
                        listTresor.get(i).setVisible(false);
                    }
                }
                
                break;
        }
    }

}
