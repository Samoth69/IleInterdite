/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author laraicha
 */
public class Menu extends JFrame {

    /**
     * @param args the command line arguments
     */
    
    private final JFrame menu;
    private final JButton jouer;
    private final JButton quitter;
    
    public Menu() {
            
        menu = new JFrame();
        menu.setTitle("Menu");
        menu.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        menu.setSize(500, 500);
        /*menu.setExtendedState(JFrame.MAXIMIZED_BOTH);*/
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        menu.setLocation(dim.width/2-menu.getSize().width/2, dim.height/2-menu.getSize().height/2);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        menu.add(mainPanel);
        
        JPanel panelCentre = new JPanel(new GridLayout(4, 3));
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        
        JPanel panelHaut = new JPanel() ;
        mainPanel.add(panelHaut, BorderLayout.NORTH);
        
        JLabel labelTitre = new JLabel("Menu");
        labelTitre.setFont(new Font(labelTitre.getFont().getName(), labelTitre.getFont().getStyle(), (int) (labelTitre.getFont().getSize() * 2.0)));
        panelHaut.add(labelTitre) ;
        
        jouer = new JButton("Jouer");
        quitter = new JButton("Quitter");
        
        panelCentre.add(new JLabel(""));
        panelCentre.add(jouer);
        panelCentre.add(quitter);
    
    
    
    }
    
    public void afficher() {
        this.menu.setVisible(true);
    }
    
     public static void main(String[] args) {
        // TODO code application logic here
        Menu m = new Menu();
        m.afficher();
    }
    
    }
    
            

