/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

/**
 *
 * @author laraicha
 */
        
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
        
public class Joueurs extends JFrame implements ActionListener {

    /**
     * @param args the command line arguments
     */
    
    private JFrame joueurs;
    private JButton retour;
    private JButton lancer;
    private JButton quitter;
    private JTextField pseudo1;
    private JTextField pseudo2;
    private JTextField pseudo3;
    private JTextField pseudo4;
    private JComboBox role1;
    private JComboBox role2;
    private JComboBox role3;
    private JComboBox role4;
    private final String [] role11 = {"Explorateur", "Ingénieur", "Navigateur", "Pilote", "Plongeur"};
    private final String [] role22 = {"Explorateur", "Ingénieur", "Navigateur", "Pilote", "Plongeur"};
    private final String [] role33 = {"Explorateur", "Ingénieur", "Navigateur", "Pilote", "Plongeur"};
    private final String [] role44 = {"Explorateur", "Ingénieur", "Navigateur", "Pilote", "Plongeur"};
    
    public Joueurs() {

    /**
     * @param args the command line arguments
     */
    
        joueurs = new JFrame();
        joueurs.setTitle("Joueurs");
        joueurs.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        joueurs.setSize(1000, 500);
        /*menu.setExtendedState(JFrame.MAXIMIZED_BOTH);*/
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        joueurs.setLocation(dim.width/2-joueurs.getSize().width/2, dim.height/2-joueurs.getSize().height/2);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        joueurs.add(mainPanel);
        
        JPanel panelCentre = new JPanel(new GridLayout(13, 7));
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        
        JPanel panelBas = new JPanel(new GridLayout(1, 6));
        mainPanel.add(panelBas, BorderLayout.SOUTH);
        
        JPanel panelHaut = new JPanel();
        mainPanel.add(panelHaut, BorderLayout.NORTH);
        
        JLabel labelTitre = new JLabel("Joueurs");
        labelTitre.setFont(new Font(labelTitre.getFont().getName(), labelTitre.getFont().getStyle(), (int) (labelTitre.getFont().getSize() * 2.0)));
        panelHaut.add(labelTitre) ;
        
        retour = new JButton("Retour");
        retour.addActionListener(this);
        lancer = new JButton("Lancer");
        lancer.addActionListener(this);
        quitter = new JButton("Quitter");
        quitter.addActionListener(this);
        
        panelBas.add(retour);
        panelBas.add(new JLabel(""));
        panelBas.add(lancer);
        panelBas.add(new JLabel(""));
        panelBas.add(quitter);
        
        pseudo1 = new JTextField();        
        pseudo2 = new JTextField();        
        pseudo3 = new JTextField();    
        pseudo4 = new JTextField();
        
        JLabel labeljoueur1 = new JLabel(" Joueur n°1", SwingConstants.CENTER);
        JLabel labeljoueurPseudo1 = new JLabel(" Pseudonyme : ", SwingConstants.LEFT);
        
        JLabel labeljoueur2 = new JLabel(" Joueur n°2", SwingConstants.CENTER);
        JLabel labeljoueurPseudo2 = new JLabel(" Pseudonyme : ", SwingConstants.LEFT);
        
        JLabel labeljoueur3 = new JLabel(" Joueur n°3", SwingConstants.CENTER);
        JLabel labeljoueurPseudo3 = new JLabel(" Pseudonyme : ", SwingConstants.LEFT);     
        
        JLabel labeljoueur4 = new JLabel(" Joueur n°4", SwingConstants.CENTER);
        JLabel labeljoueurPseudo4 = new JLabel(" Pseudonyme : ", SwingConstants.LEFT);
        
        JLabel labeljoueurRole1 = new JLabel(" Rôle", SwingConstants.LEFT);
        JLabel labeljoueurRole2 = new JLabel(" Rôle", SwingConstants.LEFT);
        JLabel labeljoueurRole3 = new JLabel(" Rôle", SwingConstants.LEFT);
        JLabel labeljoueurRole4 = new JLabel(" Rôle", SwingConstants.LEFT);
        
        role1 = new JComboBox(role11);
        role1.setSelectedIndex(0);
        
        role2 = new JComboBox(role22);
        role2.setSelectedIndex(1);
        
        role3 = new JComboBox(role33);
        role3.setSelectedIndex(2);
        
        role4 = new JComboBox(role44);
        role4.setSelectedIndex(3);
        
        for (int i=1; i<91; i++) {
            if (i == 9) {
                panelCentre.add(labeljoueur1);
            } else if (i == 13) {
                panelCentre.add(labeljoueur2);
            } else if (i == 22) {
                panelCentre.add(labeljoueurPseudo1);
            } else if (i == 23) {
                panelCentre.add(pseudo1);
            } else if (i == 27) {
                panelCentre.add(labeljoueurPseudo2);
            } else if (i == 28) {
                panelCentre.add(pseudo2);
            } else if (i == 29) {
                panelCentre.add(labeljoueurRole1);
            } else if (i == 30) {
                panelCentre.add(role1);
            } else if (i == 34) {
                panelCentre.add(labeljoueurRole2);
            } else if (i == 35) {
                panelCentre.add(role2);
            } else if (i == 44) {
                panelCentre.add(labeljoueur3);
            } else if (i == 48) {
                panelCentre.add(labeljoueur4);
            } else if (i == 57) {
                panelCentre.add(labeljoueurPseudo3);
            } else if (i == 58) {
                panelCentre.add(pseudo3);
            } else if (i == 62) {
                panelCentre.add(labeljoueurPseudo4);
            } else if (i == 63) {
                panelCentre.add(pseudo4);
            } else if (i == 64) {
                panelCentre.add(labeljoueurRole3);
            } else if (i == 65) {
                panelCentre.add(role3);
            } else if (i == 69) {
                panelCentre.add(labeljoueurRole4);
            } else if (i == 70) {
                panelCentre.add(role4);
            } else {
                panelCentre.add(getCellule(i));
            }
    }
        
        }
    
    public void afficher() {
        this.joueurs.setVisible(true);
    }
     
    public static void main(String[] args) {
        Joueurs j = new Joueurs();
        j.afficher();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == retour) {
            int rep = JOptionPane.showConfirmDialog(null,"Etes-vous sûr(e) de vouloir retourner au menu ?", "Message de confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);                   
            if (rep == JOptionPane.YES_OPTION) {
                this.joueurs.setVisible(false);
                Menu m = new Menu();
                m.afficher();   
        }
        }
        
        if (e.getSource() == quitter) {
            int rep = JOptionPane.showConfirmDialog(null,"Etes-vous sûr(e) de vouloir quitter le jeu ?", "Message de confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);                   
            if (rep == JOptionPane.YES_OPTION) {
                 System.exit(0);
        }
    }
         if (e.getSource() == lancer) {
                this.joueurs.setVisible(false);
                Plateau p = new Plateau();
                p.afficher();   
        }
    
}
    
    private JPanel getCellule(int i) {
        JPanel panelCellule = new JPanel();
        int numLigne = (int) (i+4)/4 ;
        return panelCellule ;
    }
}


