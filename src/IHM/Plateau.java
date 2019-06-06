/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;
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
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.ArrayList;

/**
 *
 * @author mariottp
 */

public class Plateau {

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
    Pion pionJoueurActuel;
    JLabel ActionRestante;
    
    boolean deplacementMode = false; //Devient vrai si le joueur à cliquer sur la case de son emplacement et voit donc les cases sur lesquels il peut aller
    
    final Color waterColor = new Color(0, 153, 255);
    final Color selectColor = new Color(12, 175, 12);
    final Color tuileColor = Color.yellow;
    final Color nonSelectedColor = Color.gray;
    
    private final JFrame window ;
    
    public Plateau(ArrayList<Personnage> persos, ControlleurJeuSecondaire cj) {
        plateau = cj.getGrille();
        this.cj = cj;
        grille = cj.getIle();
        listPion = new ArrayList<Pion>();
        listPerso = persos;
        for (Personnage p : listPerso) {
            listPion.add(new Pion(p));
        }
        
        /** PARTIE SWING **/
        
        
        /** DEFNITION DE LA FENETRE **/
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        // Définit la taille de la fenêtre en pixels
        window.setSize(1500, 900);
        window.setTitle("Ile Interdite");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        
        
        /** AJOUT PANEL PRINCIPAL + PANEL HAUT POUR TITRE FENETRE **/
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel) ;
        
        JPanel panelHaut = new JPanel() ;
        mainPanel.add(panelHaut, BorderLayout.NORTH);
        
        JLabel labelTitre = new JLabel("Ile Interdite") ;
        labelTitre.setForeground(Color.BLUE);
        labelTitre.setFont(new Font(labelTitre.getFont().getName(), labelTitre.getFont().getStyle(), (int) (labelTitre.getFont().getSize() * 1.5)));
        panelHaut.add(labelTitre) ;
        
        /**AJOUT DE LA GRILLE DE JEU AU CENTRE DE LA FENETRE **/
        JPanel panelGrille = new JPanel(new GridLayout(6, 6));
        mainPanel.add(panelGrille, BorderLayout.CENTER);
        
        joueurActuel = new JLabel("");
        pionJoueurActuel = listPion.get(0);
        ActionRestante = new JLabel();
        
        JPanel panelGamePad = new JPanel(new BorderLayout());
        JPanel panelHautGamePad = new JPanel(new GridLayout(1,3));
        panelHautGamePad.add(new JLabel("Tour du joueur "), 0, 0);
        panelHautGamePad.add(joueurActuel, 0, 1);
        panelHautGamePad.add(pionJoueurActuel, 0, 2);
        
        panelGamePad.add(panelHautGamePad, BorderLayout.NORTH);
        mainPanel.add(panelGamePad, BorderLayout.EAST);
        
        
        affecterCase(plateau, listPion, panelGrille);
        updateGamePad();
    }
    
    public void affecterCase(Tuile plateau[][], ArrayList<Pion> listPion, JPanel grille){
        for(int i = 0; i< 6; i++)
        {
            for(int j = 0; j < 6; j++)
            {
                //Creation d'une case
                JPanel pn = new JPanel(new GridLayout(3,1));     
                //bordure de la case
                pn.setBorder(BorderFactory.createLineBorder(Color.BLACK));  
                
                if (plateau[i][j] != null) 
                {
                    //ajout label nom case
                    pn.add(new JLabel(plateau[i][j].getNom()), BorderLayout.CENTER);
                    
                    
                    for(int p = 0; p<listPion.size(); p++)
                    {
                        //si case = point de depart d'un pion -> mettre le pion
                        if(plateau[i][j].getCouleurPion() == listPion.get(p).getCouleurPion())
                        {
                            pn.add(listPion.get(p));
                        }
                    }

                    if(plateau[i][j].getTresor() != TypeEnumTresors.AUCUN)
                    {
                        pn.add(new Tresor());
                    }
                    
                    pn.setBackground(tuileColor);     //background en jaune
                } 
                else 
                {
                    pn.setBackground(waterColor);       //background en bleu
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
                    public void mousePressed(MouseEvent e) {}
                    @Override
                    public void mouseReleased(MouseEvent e) {}
                    @Override
                    public void mouseEntered(MouseEvent e) {}
                    @Override
                    public void mouseExited(MouseEvent e) {}
                });
                
                panel[i][j] = pn;
                grille.add(pn, i, j);        //Ajout de la case a la grille de jeu (panelGrille)
                
            }
        }
    }
    
    //met à jour les informations du gamepad en fonction de l'état du jeu
    private void updateGamePad() {
        joueurActuel.setText(cj.getNomJoueur() + " Avec le pion ");
        pionJoueurActuel = listPion.get(cj.getJoueurNum());
    }
    
    //ce déclenche quand une case sur l'écran est cliquer
    private void panelClick(JPanel jp, Tuile emplacement, int i, int j) {
        System.out.println("panelClick: " + i + ", " + j);
        System.out.println("deplacement = " + deplacementMode);
        if (cj.getJoueurEntrainDeJouer().getEmplacement() == emplacement) {
            if (jp.getBackground() == selectColor) {
                paintNormal();
                deplacementMode = false;
            } else {
                paintNonSelected();
                jp.setBackground(selectColor);
                for (Tuile t : cj.getJoueurEntrainDeJouer().getDeplacements(emplacement)) {
                    JPanel jpa = panel[t.getX()][t.getY()];
                    jpa.setBackground(tuileColor);
                    //System.out.println(t.getNom() + "\t" + t.getX() + "\t" + t.getY());
                }
                deplacementMode = true;
            }
        window.repaint();
        
        } else {
            if (deplacementMode == true && jp.getBackground() == tuileColor) {
                System.out.println("Moving");
                cj.deplacerJoueurEnCour(emplacement);
                int x = cj.getJoueurEntrainDeJouer().getEmplacement().getX();
                int y = cj.getJoueurEntrainDeJouer().getEmplacement().getX();
                panel[x][y].remove(listPion.get(cj.getJoueurNum()));
                panel[i][j].add(listPion.get(cj.getJoueurNum()));
                
                deplacementMode = false;
                paintNormal();
            }
        }    
        updateGamePad();
    }
    
    private void paintNonSelected() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                JPanel jp = panel[i][j];
                if (jp.getBackground() != waterColor) {
                    jp.setBackground(nonSelectedColor);
                }
                
            }
        }
        window.repaint();
    }
    
    private void paintNormal() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                JPanel jp = panel[i][j];
                if (jp.getBackground() != waterColor) {
                    jp.setBackground(tuileColor);
                }
            }
        }
        window.repaint();
    }
    
    public void afficher() {
        this.window.setVisible(true);
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
    
    /*public static void main(String[] args){
        Plateau ihm = new Plateau();
        ihm.afficher();
    }*/
    
}
