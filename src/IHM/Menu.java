/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Enumerations.TypeEnumMenuPrincipal;
import IleInterdite.Message;
import IleInterdite.Observateur;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author laraicha
 */
public class Menu extends JFrame implements ActionListener {

    /**
     * @param args the command line arguments
     */
    
    private JFrame menu;
    private JButton jouer;
    private JButton quitter;
    private JButton regles;
    
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
        
        JPanel panelCentre = new JPanel(new GridLayout(5, 3));
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        
        JPanel panelHaut = new JPanel() ;
        mainPanel.add(panelHaut, BorderLayout.NORTH);
        
        JLabel labelTitre = new JLabel("Menu");
        labelTitre.setFont(new Font(labelTitre.getFont().getName(), labelTitre.getFont().getStyle(), (int) (labelTitre.getFont().getSize() * 2.0)));
        panelHaut.add(labelTitre) ;
        
        jouer = new JButton("Jouer");
        jouer.addActionListener(this);
        regles = new JButton("Règles du jeu");
        regles.addActionListener(this);
        quitter = new JButton("Quitter");
        quitter.addActionListener(this);
        
        panelCentre.add(new JLabel(""));
        panelCentre.add(jouer);
        panelCentre.add(regles);
        panelCentre.add(quitter);  
    
    }
    
    public void afficher() {
        this.menu.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jouer) {
            this.menu.setVisible(false);
            notifierObservateur(new Message(TypeEnumMenuPrincipal.MENU_JOUEURS));
        }
        
        if(e.getSource() == regles) {
                try {
                    Desktop.getDesktop().open(new File(System.getProperty("user.dir") + "/src/RessourcesRegles/regles.pdf"));
                } catch (IOException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                notifierObservateur(new Message(TypeEnumMenuPrincipal.MENU_REGLES));
        }
          
        if(e.getSource() == quitter) {
            int rep = JOptionPane.showConfirmDialog(null,"Etes-vous sûr(e) de vouloir quitter le jeu ?", "Message de confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);                   
            if (rep == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
     
    }
    
    private Observateur observateur;    
    public void addObservateur(Observateur o) {
        this.observateur = o;
    }
    
    public void notifierObservateur(Message m) {
        if (observateur != null) {
            observateur.traiterMessage(m);
        }
    } 
}
    
            

