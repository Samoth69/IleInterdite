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
public class AffichageCarteRouge extends JPanel{

    private boolean small = true;
    private Personnage perso;
    
    private JPanel mainPanelSmall;
    private JPanel mainPanelBig;
    
    private JPanel smallPanelGauche;
    private JPanel smallPanelDroite;
    
    private JPanel smallPanelDroiteCarte;
    
    public AffichageCarteRouge(Personnage perso) {
        super(new BorderLayout(), true);
        this.perso = perso;
        
        mainPanelSmall = new JPanel(new BorderLayout());
        mainPanelBig = new JPanel();
        
        smallPanelGauche = new JPanel();
        smallPanelDroite = new JPanel();
        
        mainPanelSmall.add(smallPanelGauche, BorderLayout.EAST);
        mainPanelSmall.add(smallPanelDroite, BorderLayout.WEST);
        
        draw();
    }
    
    //définie si l'objet doit ce désigner en mode réduit ou grand (en fonction du joueur en cour)
    //si small == true, on désigne l'objet en mode réduit
    //si small == false, on désigne l'objet en mode grand
    public void setSmall(boolean b) {
        small = b;
    }
    
    //redesigne cette interface
    public void redraw() {
        draw();
    }
    
    private void draw() {
        smallPanelGauche.add(new JLabel(perso.getNom()));
        smallPanelDroite.setLayout(new GridLayout(perso.getCartes().size(), 0));
        for (CarteRouge cr : perso.getCartes()) {
            smallPanelDroite.add(new JLabel(cr.getNom()));
        }
        
        this.removeAll();
        if (small) {
            this.add(mainPanelSmall);
        } else {
            this.add(mainPanelBig);
        }
    }
}
