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
    
  
    public void paint(Graphics g){
        Dimension dim = getSize();
        
        g.setColor(Color.green);
        g.fillOval(0, 0, 10, 10);
    }
    
    public TypeEnumCouleurPion getCouleurPion(){
        return perso.getCouleurPion();
    }
}
