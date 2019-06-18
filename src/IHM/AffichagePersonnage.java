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

    private JPanel bigPion;
    private Pion pion;
    private Personnage perso;
    
    public AffichagePersonnage(Boolean small) {
        super(true);
        super.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        pseudoJoueur = new JLabel();
        bigPion = new JPanel();
        pion = new Pion(TypeEnumCouleurPion.AUCUN);
        
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
            bigMainPanel = new JPanel(new BorderLayout());
            JPanel panelHaut = new JPanel(new BorderLayout());

            panelHaut.add(new JLabel("Joueur: "), BorderLayout.WEST);
            panelHaut.add(pseudoJoueur, BorderLayout.EAST);
            bigPion.add(pion);
            panelHaut.add(bigPion, BorderLayout.SOUTH);

            bigMainPanel.add(panelHaut, BorderLayout.NORTH);

            this.add(bigMainPanel);
        }
    }
    
    //met à jour les éléments de la fenêtre avec le joueur passé en paramètre
    public void update(Personnage perso, TypeEnumCouleurPion p) {
        pseudoJoueur.setText(perso.getNom());
        pion.setCouleur(p);
    }
}
