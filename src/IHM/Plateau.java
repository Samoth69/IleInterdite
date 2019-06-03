/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;
import Enumerations.TypeEnumTresors;
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

    Explorateur explo;
    Messager testP;
    Ingenieur inge;
    Grille grille;
    Pion pion, pion2, pion3;
    ArrayList<Pion> listPion;
    Tuile plateau[][] = new Tuile[6][6];
    private final JFrame window ;
    
    
    public Plateau() {
        /** INITIALISATION **/
        
        explo = new Explorateur("NomExplorateur1", grille);
        inge = new Ingenieur("NomIngenieur1", grille);
        testP = new Messager("testP", grille);       //test
        grille = new Grille(explo, inge, testP);        //test
        
        pion = new Pion(testP);                 //test
        pion2 = new Pion(explo);
        pion3 = new Pion(inge);
        
        listPion = new ArrayList<Pion>();
        
        plateau = grille.getTuiles();
        
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
        
        listPion.add(pion);
        listPion.add(pion2);
        listPion.add(pion3);
        
        affecterCase(plateau, listPion, panelGrille);
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
                    
                    pn.setBackground(Color.yellow);     //background en jaune
                } 
                else 
                {
                    pn.setBackground(new Color(0, 153, 255));       //background en bleu
                }
                
                pn.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        
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
                
                grille.add(pn, i, j);        //Ajout de la case a la grille de jeu (panelGrille)
            }
        }
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
    
    public static void main(String[] args){
        Plateau ihm = new Plateau();
        ihm.afficher();
    }
    
}
