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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mariottp
 */

public class IHMsimple {

    Explorateur explo;
    Ingenieur inge;
    Grille grille;
    Tuile plateau[][] = new Tuile[6][6];
    private final JFrame window ;
    
    
    public IHMsimple() {
        /** INITIALISATION **/
        explo = new Explorateur("NomExplorateur1", grille);
        inge = new Ingenieur("NomIngenieur1", grille);
        grille = new Grille(explo, inge);
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
        JPanel panelCentre = new JPanel(new GridLayout(6, 6));
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        
        for(int i = 0; i< 6; i++)
        {
            for(int j = 0; j < 6; j++)
            {
                if(plateau[i][j] == null)
                {
                    panelCentre.add(getCellule(i, plateau[i][j]));
                }
                else
                {
                    panelCentre.add(getCellule(i, plateau[i][j]), new JLabel(plateau[i][j].getNom()));
                }
            }
        }
    }
        
    private JPanel getCellule(int i, Tuile tuile) {
        int numLigne = (int) (i+4)/4 ;
        int numCouleur = (i-numLigne+1) % 4 + 1;
        JPanel panelCellule = new JPanel();
        if(tuile == null)
        {
            panelCellule.setBackground(new Color(0, 0, 254));
            return panelCellule ;
        }
        else
        {
            panelCellule.setBackground(new Color(255, 254, 0));
            return panelCellule;
        }
    }
    
    public void afficher() {
        this.window.setVisible(true);
    }
    
    public static void main(String[] args){
        IHMsimple ihm = new IHMsimple();
        ihm.afficher();
    }
    
}
