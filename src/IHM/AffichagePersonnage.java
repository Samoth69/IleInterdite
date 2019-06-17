/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Cartes.CarteRouge;
import Personnages.Personnage;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

/**
 *
 * @author violentt
 */
public class AffichagePersonnage extends JPanel{
  
    private JPanel smallMainPanel;
    private JPanel bigMainPanel;
    
    private JPanel smallPanelGauche;
    private JPanel smallPanelDroite;
    
    private JPanel smallPanelDroiteCarte;
    
    private JLabel pseudoJoueur;
    private Personnage perso;
    
    public AffichagePersonnage(Boolean small) {
        super(new BorderLayout(), true);
        pseudoJoueur = new JLabel();
        
        if (small) {
            smallMainPanel = new JPanel(new BorderLayout());
            
            smallPanelGauche = new JPanel();
            smallPanelDroite = new JPanel();
            
            smallMainPanel.add(smallPanelGauche, BorderLayout.EAST);
            smallMainPanel.add(smallPanelDroite, BorderLayout.WEST);
            
            smallPanelGauche.add(pseudoJoueur);
            smallPanelDroite.setLayout(new GridLayout(10, 1));
            for (int i = 0; i < 10; i++) {
                smallPanelDroite.add(new JLabel("-"));
            }
            
            this.add(smallMainPanel);
        } else {
            bigMainPanel = new JPanel();
            
            this.add(bigMainPanel);
        }
    }
    
    //met à jour les éléments de la fenêtre avec le joueur passé en paramètre
    public void update(Personnage perso) {
        pseudoJoueur.setText(perso.getNom());
    }
}
