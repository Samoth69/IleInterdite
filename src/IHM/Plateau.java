/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;
import IleInterdite.Grille;
import Personnages.*;
import IleInterdite.Tuile;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mariottp
 */

public class Plateau {

    Explorateur explo, testP;
    Ingenieur inge;
    Grille grille;
    Pion pion;
    Tuile plateau[][] = new Tuile[6][6];
    private final JFrame window ;
    
    
    public Plateau() {
        /** INITIALISATION **/
        
        explo = new Explorateur("NomExplorateur1", grille);
        inge = new Ingenieur("NomIngenieur1", grille);
        testP = new Explorateur("testP", grille);       //test
        grille = new Grille(explo, inge, testP);        //test
        
        pion = new Pion(testP);                 //test
        
        plateau = grille.getTuiles();
        
        /** PARTIE SWING **/
        
        
        /** DEFNITION DE LA FENETRE **/
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        // Définit la taille de la fenêtre en pixels
        window.setSize(1500, 900);
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
        
        for(int i = 0; i< 6; i++)
        {
            for(int j = 0; j < 6; j++)
            {
                JPanel pn = new JPanel(new GridLayout(2,1));        //Creation d'une case
                pn.setBorder(BorderFactory.createLineBorder(Color.BLACK));  //bordure de la case
                
                if (plateau[i][j] != null) 
                {
                    //ajout label nom case
                    pn.add(new JLabel(plateau[i][j].getNom()));
                    
                    //si case = point de depart d'un pion -> mettre le pion
                    if(plateau[i][j].getCouleurPion() == pion.getCouleurPion())
                    {
                        pn.add(pion);
                    }
                    
                    pn.setBackground(Color.yellow);     //background en jaune
                } 
                else 
                {
                    pn.setBackground(Color.blue);       //background en bleu
                }
                
                panelGrille.add(pn);        //Ajout de la case a la grille de jeu (panelGrille)
            }
        }
    }
    
    public void afficher() {
        this.window.setVisible(true);
    }
    
    public static void main(String[] args){
        Plateau ihm = new Plateau();
        ihm.afficher();
    }
    
}
