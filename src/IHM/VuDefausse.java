/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Cartes.CarteRouge;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import IleInterdite.ControlleurJeuSecondaire;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mariottp
 */
public class VuDefausse {
    
    private JFrame window;
    private ControlleurJeuSecondaire cj;
    private ArrayList<CarteRouge> carteDuJoueur;
    
    VuDefausse(){
        this.cj = cj;
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        // Définit la taille de la fenêtre en pixels
        window.setSize(400, 600);
        window.setTitle("Defaussez une carte");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        
        //get les cartes en mains du joueur
        carteDuJoueur = cj.getJoueurEntrainDeJouer().getCartes();
        
        JPanel grilleCarte = new JPanel(new GridLayout(1, carteDuJoueur.size()));
        
        for(int i = 0; i < carteDuJoueur.size(); i++)
        {
            grilleCarte.add(new JLabel(""));
        }
        window.add(grilleCarte);
    }

    public void afficher(){
        this.window.setVisible(true);
    }
    
    public static void main(String[] args) {
        new VuDefausse().afficher();
    }
    
}
