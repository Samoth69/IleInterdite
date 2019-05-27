/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Enumerations.TypeEnumCouleurPion;
import Personnages.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author mariottp
 */
public class Pion extends JComponent{
    
    
    Personnage perso;
    
    public Pion(Personnage perso){
        this.perso = perso;
    }
    
    //Fonction appelé par defaut pour dessiner le pion
    public void paint(Graphics g){
        Dimension dim = getSize();
        
        switch(perso.getCouleurPion())
        {
            case BLEU:
                g.setColor(Color.GREEN);
            break;
            case JAUNE:
                g.setColor(Color.YELLOW);
            break;
            case ORANGE:
                g.setColor(Color.ORANGE);
            break;
            case ROUGE:
                g.setColor(Color.RED);
            break;
            case VERT:
                g.setColor(Color.GREEN);
            break;
            case VIOLET:
                g.setColor(Color.MAGENTA);
            break;
            default:
                g.setColor(Color.GRAY);
            break;
        }
        
        g.fillOval((int)dim.getWidth()/2, 0, 30, 30);
    }
    
    //getteur pour la couleur du pion en fonction du personnage
    public TypeEnumCouleurPion getCouleurPion(){
        return perso.getCouleurPion();
    }
}
