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
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
    private Image image ;
    private JFrame menu;
    private JButton jouer;
    private JButton quitter;
    private JButton regles;
    
    public Menu() {

        
        
        ImageIcon img= new ImageIcon("src/RessourcesMenu/IleInterdite.jpg");
        JLabel background= new JLabel("", img, JLabel.CENTER);
        
        
        
        
        menu= new JFrame("Menu");
        menu.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        menu.setSize(553, 709);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        menu.setLocation(dim.width/2-menu.getSize().width/2, dim.height/2-menu.getSize().height/2);
        
        

        jouer = new JButton("Jouer");
        jouer.addActionListener(this);
        regles = new JButton("Règles du jeu");
        regles.addActionListener(this);
        quitter = new JButton("Quitter");
        quitter.addActionListener(this);
        
        background.setLayout(new GridLayout(11,3));
        menu.setContentPane(background);
        

          jouer.setFont(new Font(jouer.getFont().getName(), jouer.getFont().getStyle(), (int) (jouer.getFont().getSize()*1.5)));
          regles.setFont(new Font(regles.getFont().getName(), regles.getFont().getStyle(), (int) (regles.getFont().getSize()*1.5)));
          quitter.setFont(new Font(quitter.getFont().getName(), quitter.getFont().getStyle(), (int) (quitter.getFont().getSize()*1.5)));

        for(int i=0; i<33; i++){
            if(i==16 ){
               background.add(jouer); 
            } else if(i==22){
               background.add(regles); 
            } else if(i==28){
               background.add(quitter); 
            } else{
            background.add(new JLabel(""));
            }
        }

        
    
        
        
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
    
            

