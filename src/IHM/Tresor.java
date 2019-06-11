/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import Enumerations.TypeEnumTresors;

/**
 *
 * @author mariottp
 */
public class Tresor extends JComponent{
    
    TypeEnumTresors type;
    
    Tresor(TypeEnumTresors typeTresor){
        this.type = typeTresor;
    }
    
    public void paint(Graphics g){
        
        switch(type)
        {
            case FEU:
                g.setColor(Color.red);
            break;
            case LION:
                g.setColor(Color.YELLOW);
            break;
            case LUNE:
                g.setColor(new Color(153, 51, 153));    // Violet
            break;
            case TROPHEE:
                g.setColor(Color.CYAN);
            break;
            case AUCUN:
                // Ne pas traiter
            break;
        }
        
        g.fillRect(0, 0, 10, 10);
    }
    
    
}
