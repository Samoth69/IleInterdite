/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Enumerations.TypeEnumMenuPrincipal;
import IleInterdite.Message;
import IleInterdite.Observateur;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author laraicha
 */
public class Menu extends JFrame implements ActionListener {

    /**
     * @param args the command line arguments
     */
    
    //ATTRIBUTS
    private JFrame menu;
    private JButton jouer;
    private JButton quitter;
    private JButton regles;

    //CONSTRUCTEUR
    public Menu() {
        
        // Fond avec image
        ImageIcon img = new ImageIcon("src/RessourcesMenu/FondMenu.jpg");
        JLabel background = new JLabel("", img, JLabel.CENTER);
        
        //Creation de la fenetre
        menu = new JFrame("Menu");
        menu.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        menu.setSize(553, 709);
        
        //dimension fenetre
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        menu.setLocation(dim.width / 2 - menu.getSize().width / 2, dim.height / 2 - menu.getSize().height / 2);
        
        //Construction bouton jouer/regles/quitter
        jouer = new JButton("Jouer");
        jouer.addActionListener(this);
        regles = new JButton("Règles du jeu");
        regles.addActionListener(this);
        quitter = new JButton("Quitter");
        quitter.addActionListener(this);
        
        //grille de 11 lignes, 3 colonnes
        background.setLayout(new GridLayout(11, 3));
        menu.setContentPane(background);
        
        //Augmentation de la taille du texte des boutons
        jouer.setFont(new Font(jouer.getFont().getName(), jouer.getFont().getStyle(), (int) (jouer.getFont().getSize() * 1.5)));
        regles.setFont(new Font(regles.getFont().getName(), regles.getFont().getStyle(), (int) (regles.getFont().getSize() * 1.5)));
        quitter.setFont(new Font(quitter.getFont().getName(), quitter.getFont().getStyle(), (int) (quitter.getFont().getSize() * 1.5)));
        
        //Mise en place des boutons dans des cases du gridlayout
        for (int i = 0; i < 33; i++) { // boucle 33 fois car il y a 33 cases
            if (i == 16) {
                background.add(jouer);  // bouton jouer à la 16eme case
            } else if (i == 22) {
                background.add(regles);  //bouton regles à la 22eme case
            } else if (i == 28) {
                background.add(quitter);   //bouton quitter à la 28eme case
            } else {
                background.add(new JLabel(""));// pour le reste des cases, du vide
            }
        }

    }

    //METHODES
    
    //Affiche le menu
    public void afficher() {
        this.menu.setVisible(true);
    }
    
    //Action des boutons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jouer) {               //Si clique sur jouer
            this.menu.setVisible(false);               //fenetre invisible
            notifierObservateur(new Message(TypeEnumMenuPrincipal.MENU_JOUEURS)); //notif observateur de l'action choisi
        }

        if (e.getSource() == regles) {  //si clique sur regles
            try {
                Desktop.getDesktop().open(new File(System.getProperty("user.dir") + "/src/RessourcesMenu/Regles.pdf")); //Ouvre un pdf contenant les regles du jeu
            } catch (IOException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            notifierObservateur(new Message(TypeEnumMenuPrincipal.MENU_REGLES)); //notif observateur de l'action choisi
        }

        if (e.getSource() == quitter) { // si clique sur quitter, message de confirmation
            int rep = JOptionPane.showConfirmDialog(null, "Etes-vous sûr(e) de vouloir quitter le jeu ?", "Message de confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
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
