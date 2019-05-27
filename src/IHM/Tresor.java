/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author mariottp
 */
public class Tresor extends JComponent{
    
    Tresor(){
        
    }
    
    public void paint(Graphics g){
        g.setColor(Color.red);
        
        g.fillRect(0, 0, 10, 10);
    }
    
    
}
