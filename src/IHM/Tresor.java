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
import javax.swing.JLabel;

/**
 *
 * @author mariottp
 */
public class Tresor extends JComponent{
    
    TypeEnumTresors type;
    Color couleur;
    JLabel lbT;
    
    Tresor(TypeEnumTresors typeTresor){
        this.type = typeTresor;
        
        setCouleur();
        initLabel();
    }
    
    public void paint(Graphics g){
        
        switch(type)
        {
            case FEU:
                g.setColor(Color.red);
                couleur = Color.red;
            break;
            case LION:
                g.setColor(Color.YELLOW);
                couleur = Color.YELLOW;
            break;
            case LUNE:
                g.setColor(new Color(153, 51, 153));    // Violet
                couleur = new Color(153, 51, 153);
            break;
            case TROPHEE:
                g.setColor(Color.CYAN);
                couleur = Color.CYAN;
            break;
            case AUCUN:
                // Ne pas traiter
            break;
        }
        
        g.fillRect(0, 0, 10, 10);
    }
    
    
    public TypeEnumTresors getTypeTresor(){
        return type;
    }

    public void setCouleur(){
        switch(type)
        {
            case FEU:
                couleur = Color.red;
                break;
            case LION:
                couleur = Color.YELLOW;
                break;
            case LUNE:
                couleur =  new Color(153, 51, 153);
                break;
            case TROPHEE:
                couleur = Color.CYAN;
                break;
            case AUCUN:
                couleur = new Color(255, false);
                break;
            default:
                couleur = new Color(255, false);
                break;
        }
    }
    
    public Color getColor(){
        return couleur;
    }
    
    public void setColorGris(){
        couleur = Color.GRAY;
    }
    
    public void setColorBack(){
        setCouleur();
        initLabel();
    }
    
    public String getNom(){
        switch(type)
        {
            case FEU:
                return "Cristal Ardent";
            case LION:
                return "Statue Zephyr";
            case LUNE:
                return "Pierre Sacree";
            case TROPHEE:
                return "Calice des Ondes";
            case AUCUN:
                return "";
            default:
                return "NA";
        }
    }
    
    public JLabel getLabel(){
        return lbT;
    }
    
    public void initLabel(){
        
         switch(type)
        {
            case FEU:
                lbT = new JLabel("Cristal Ardent");
                lbT.setOpaque(true);
                lbT.setBackground(couleur);
                break;
            case LION:
                lbT = new JLabel("Statue Zephyr");
                lbT.setOpaque(true);
                lbT.setBackground(couleur);
                break;
            case LUNE:
                lbT = new JLabel("Pierre Sacree");
                lbT.setOpaque(true);
                lbT.setBackground(couleur);
                break;
            case TROPHEE:
                lbT = new JLabel("Calice des ondes");
                lbT.setOpaque(true);
                lbT.setBackground(couleur);
                break;
            case AUCUN:
                break;
            default:
                break;
        }
    }
}
