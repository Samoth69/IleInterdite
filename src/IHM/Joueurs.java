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
        
import Enumerations.TypeEnumMessage;
import IleInterdite.Message;
import IleInterdite.Observateur;
import com.sun.tools.javac.util.StringUtils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
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
    private JComboBox nbJoueurs;
    
    private ArrayList<JTextField> listPseudo;
    private ArrayList<JComboBox> listRole;
    private final Integer[] nombreJoueur = {2, 3, 4};
    private final String[] nomRoles = {"Explorateur", "Ingénieur", "Navigateur", "Pilote", "Plongeur"};
    
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
        
        listPseudo = new ArrayList<>();
        listPseudo.add(pseudo1);
        listPseudo.add(pseudo2);
        listPseudo.add(pseudo3);
        listPseudo.add(pseudo4);
        
        JLabel labeljoueur1 = new JLabel(" Joueur n°1", SwingConstants.CENTER);
        JLabel labeljoueurPseudo1 = new JLabel(" Pseudonyme : ", SwingConstants.LEFT);
        
        JLabel labeljoueur2 = new JLabel(" Joueur n°2", SwingConstants.CENTER);
        JLabel labeljoueurPseudo2 = new JLabel(" Pseudonyme : ", SwingConstants.LEFT);
        
        JLabel labeljoueur3 = new JLabel(" Joueur n°3", SwingConstants.CENTER);
        JLabel labeljoueurPseudo3 = new JLabel(" Pseudonyme : ", SwingConstants.LEFT);     
        
        JLabel labeljoueur4 = new JLabel(" Joueur n°4", SwingConstants.CENTER);
        JLabel labeljoueurPseudo4 = new JLabel(" Pseudonyme : ", SwingConstants.LEFT);
        
        JLabel labeljoueurRole1 = new JLabel(" Rôle:", SwingConstants.LEFT);
        JLabel labeljoueurRole2 = new JLabel(" Rôle:", SwingConstants.LEFT);
        JLabel labeljoueurRole3 = new JLabel(" Rôle:", SwingConstants.LEFT);
        JLabel labeljoueurRole4 = new JLabel(" Rôle:", SwingConstants.LEFT);
        
        role1 = new JComboBox(nomRoles);
        role1.setSelectedIndex(0);
        
        role2 = new JComboBox(nomRoles);
        role2.setSelectedIndex(1);
        
        role3 = new JComboBox(nomRoles);
        role3.setSelectedIndex(2);
        
        role4 = new JComboBox(nomRoles);
        role4.setSelectedIndex(3);
        
        listRole = new ArrayList<>();
        listRole.add(role1);
        listRole.add(role2);
        listRole.add(role3);
        listRole.add(role4);
        
        
        nbJoueurs = new JComboBox(nombreJoueur);
        nbJoueurs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch ((int)nbJoueurs.getSelectedItem()) {
                    case 2:
                        labeljoueur3.setForeground(Color.GRAY);
                        labeljoueur4.setForeground(Color.GRAY);
                        labeljoueurPseudo3.setForeground(Color.GRAY);
                        labeljoueurPseudo4.setForeground(Color.GRAY);
                        labeljoueurRole3.setForeground(Color.GRAY);
                        labeljoueurRole4.setForeground(Color.GRAY);
                        pseudo3.setEnabled(false);
                        pseudo4.setEnabled(false);
                        role3.setEnabled(false);
                        role4.setEnabled(false);
                        pseudo3.setText("");
                        pseudo4.setText("");
                        break;
                    case 3:
                        labeljoueur3.setForeground(Color.BLACK);
                        labeljoueur4.setForeground(Color.GRAY);
                        labeljoueurPseudo3.setForeground(Color.BLACK);
                        labeljoueurPseudo4.setForeground(Color.GRAY);
                        labeljoueurRole3.setForeground(Color.BLACK);
                        labeljoueurRole4.setForeground(Color.GRAY);
                        pseudo3.setEnabled(true);
                        pseudo4.setEnabled(false);
                        role3.setEnabled(true);
                        role4.setEnabled(false);
                        pseudo4.setText("");
                        break;
                    case 4:
                        labeljoueur3.setForeground(Color.BLACK);
                        labeljoueur4.setForeground(Color.BLACK);
                        labeljoueurPseudo3.setForeground(Color.BLACK);
                        labeljoueurPseudo4.setForeground(Color.BLACK);
                        labeljoueurRole3.setForeground(Color.BLACK);
                        labeljoueurRole4.setForeground(Color.BLACK);
                        pseudo3.setEnabled(true);
                        pseudo4.setEnabled(true);
                        role3.setEnabled(true);
                        role4.setEnabled(true);
                        break;
                }
            }
        });
        
        nbJoueurs.setSelectedIndex(0);
        
        
        for (int i=1; i<91; i++) {
            switch (i) {
                case 9:
                    panelCentre.add(labeljoueur1);
                    break;
                case 11:
                    panelCentre.add(new JLabel("Nombre de joueurs:"));
                    break;
                case 13:
                    panelCentre.add(labeljoueur2);
                    break;
                case 18:
                    panelCentre.add(nbJoueurs);
                    break;
                case 22:
                    panelCentre.add(labeljoueurPseudo1);
                    break;
                case 23:
                    panelCentre.add(pseudo1);
                    break;
                case 27:
                    panelCentre.add(labeljoueurPseudo2);
                    break;
                case 28:
                    panelCentre.add(pseudo2);
                    break;
                case 29:
                    panelCentre.add(labeljoueurRole1);
                    break;
                case 30:
                    panelCentre.add(role1);
                    break;
                case 34:
                    panelCentre.add(labeljoueurRole2);
                    break;
                case 35:
                    panelCentre.add(role2);
                    break;
                case 44:
                    panelCentre.add(labeljoueur3);
                    break;
                case 48:
                    panelCentre.add(labeljoueur4);
                    break;
                case 57:
                    panelCentre.add(labeljoueurPseudo3);
                    break;
                case 58:
                    panelCentre.add(pseudo3);
                    break;
                case 62:
                    panelCentre.add(labeljoueurPseudo4);
                    break;
                case 63:
                    panelCentre.add(pseudo4);
                    break;
                case 64:
                    panelCentre.add(labeljoueurRole3);
                    break;
                case 65:
                    panelCentre.add(role3);
                    break;
                case 69:
                    panelCentre.add(labeljoueurRole4);
                    break;
                case 70:
                    panelCentre.add(role4);
                    break;
                default:
                    panelCentre.add(getCellule(i));
                    break;
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
                notifierObservateur(new Message(TypeEnumMessage.MENU_PRINCIPAL));
            }
        }
        
        if (e.getSource() == quitter) {
            int rep = JOptionPane.showConfirmDialog(null,"Etes-vous sûr(e) de vouloir quitter le jeu ?", "Message de confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);                   
            if (rep == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
        
        if (e.getSource() == lancer) {
            ArrayList<String> usedRole = new ArrayList<>();
            for (int i = 0; i < (int)nbJoueurs.getSelectedItem(); i++) {
                if (usedRole.contains((String)listRole.get(i).getSelectedItem())) {
                    JOptionPane.showMessageDialog(null, "Chaque joueur doit avoir un rôle différent");
                    return; //quitte la fonction
                }
                usedRole.add((String)listRole.get(i).getSelectedItem());
            }
            for (int i = 0; i < (int)nbJoueurs.getSelectedItem(); i++) {
                if (listPseudo.get(i).getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Le pseudo d'un joueur ne peux pas être vide");
                    return; //quitte la fonction
                }
            }
            
            
            this.joueurs.setVisible(false);
            Message msg = new Message(TypeEnumMessage.MENU_JOUER);
            ArrayList<String> infos = new ArrayList<>();
            infos.add(String.valueOf(nbJoueurs.getSelectedItem()));
            infos.add(pseudo1.getText());
            infos.add((String)role1.getSelectedItem());
            infos.add(pseudo2.getText());
            infos.add((String)role2.getSelectedItem());
            
            if ((int)nbJoueurs.getSelectedItem() >= 3) {
                infos.add(pseudo3.getText());
                infos.add((String)role3.getSelectedItem());
            }
            if ((int)nbJoueurs.getSelectedItem() >= 4) {
                infos.add(pseudo4.getText());
                infos.add((String)role4.getSelectedItem());
            }
            msg.setAdditionnal(infos);
            notifierObservateur(msg);
        }
    
    }
    
    private JPanel getCellule(int i) {
        JPanel panelCellule = new JPanel();
        int numLigne = (int) (i+4)/4 ;
        return panelCellule ;
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


