/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import javax.swing.JPanel;
import Personnages.*;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 *
 * @author mariottp
 */
public class Pion extends JPanel{
    
    
    Personnage perso;
    
    public Pion(Personnage perso){
        this.perso = perso;
    }
    
    @Override
    public void paintComponent(Graphics g){
        Dimension dim = getSize();
    }
}
