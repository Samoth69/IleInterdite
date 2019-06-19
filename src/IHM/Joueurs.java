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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author laraicha
 */
public class Joueurs extends JFrame implements ActionListener {

    //ATTRIBUTS
    //JFrame et JButton
    private JFrame joueur;
    private JButton retour;
    private JButton jouer;
    private JButton quitter;

    //JTextField
    private JTextField pseudo1;
    private JTextField pseudo2;
    private JTextField pseudo3;
    private JTextField pseudo4;

    //JComboBox
    private JComboBox role1;
    private JComboBox role2;
    private JComboBox role3;
    private JComboBox role4;
    private JComboBox nbJoueurs;
    private JComboBox niveauDepart;

    //Listes
    private ArrayList<JTextField> listPseudo;
    private ArrayList<JComboBox> listRole;
    private final Integer[] nombreJoueur = {2, 3, 4};
    private final String[] nomRoles = {"Explorateur", "Ingénieur", "Navigateur", "Pilote", "Plongeur", "Aléatoire"};
    private final String[] niveauEau = {"Novice", "Normal", "Elite", "Légendaire"};

    public Joueurs() {
        ImageIcon img = new ImageIcon("src/RessourcesJoueur/FondJoueur.jpg");
        JLabel panelcenter = new JLabel("", img, JLabel.CENTER);

        joueur = new JFrame("Joueur");
        joueur.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
      //  joueur.setSize(775, 330);
        joueur.setSize(800, 455);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        joueur.setLocation(dim.width / 2 - joueur.getSize().width / 2, dim.height / 2 - joueur.getSize().height / 2);

      //  background.setLayout(new GridLayout(9, 4, 5, 5));
         panelcenter.setLayout(new BorderLayout());
        
         JPanel background = new JPanel(new GridLayout(9, 4, 5, 5));
         panelcenter.add(background,BorderLayout.CENTER);
         background.setOpaque(false);
         
         JPanel panelHaut = new JPanel();
         panelcenter.add(panelHaut, BorderLayout.NORTH);
         panelHaut.setOpaque(false);
         
         JPanel panelBas = new JPanel(new GridLayout(1, 6));
         panelcenter.add(panelBas, BorderLayout.SOUTH);
         panelBas.setOpaque(false);
         
        joueur.setContentPane(panelcenter);

        retour = new JButton("Retour");
        retour.addActionListener(this);
        jouer = new JButton("Jouer");
        jouer.addActionListener(this);
        quitter = new JButton("Quitter");
        quitter.addActionListener(this);

        pseudo1 = new JTextField();
        pseudo2 = new JTextField();
        pseudo3 = new JTextField();
        pseudo4 = new JTextField();
        
        pseudo1.setFont(new Font(pseudo1.getFont().getName(), pseudo1.getFont().getStyle(), (int) (pseudo1.getFont().getSize() * 1.5)));
        pseudo2.setFont(new Font(pseudo2.getFont().getName(), pseudo2.getFont().getStyle(), (int) (pseudo2.getFont().getSize() * 1.5)));
        pseudo3.setFont(new Font(pseudo3.getFont().getName(), pseudo3.getFont().getStyle(), (int) (pseudo3.getFont().getSize() * 1.5)));
        pseudo4.setFont(new Font(pseudo4.getFont().getName(), pseudo4.getFont().getStyle(), (int) (pseudo4.getFont().getSize() * 1.5)));

        listPseudo = new ArrayList<>();
        listPseudo.add(pseudo1);
        listPseudo.add(pseudo2);
        listPseudo.add(pseudo3);
        listPseudo.add(pseudo4);
        
        JLabel labelTitre = new JLabel("Joueurs") ;
        labelTitre.setFont(new Font(labelTitre.getFont().getName(), labelTitre.getFont().getStyle(), (int) (labelTitre.getFont().getSize() * 2.0)));
        panelHaut.add(labelTitre);
        labelTitre.setForeground(Color.WHITE);
        
        JLabel labeljoueur1 = new JLabel(" Joueur n°1 : ", SwingConstants.LEFT);
        labeljoueur1.setFont(new Font(jouer.getFont().getName(), jouer.getFont().getStyle(), (int) (jouer.getFont().getSize() * 1.5)));

        JLabel choisirnbjoueur = new JLabel(" Nombre de joueurs :", SwingConstants.LEFT);
        choisirnbjoueur.setFont(new Font(jouer.getFont().getName(), jouer.getFont().getStyle(), (int) (jouer.getFont().getSize() * 1.5)));

        JLabel labeljoueur2 = new JLabel(" Joueur n°2 : ", SwingConstants.LEFT);
        labeljoueur2.setFont(new Font(jouer.getFont().getName(), jouer.getFont().getStyle(), (int) (jouer.getFont().getSize() * 1.5)));

        JLabel labeljoueur3 = new JLabel(" Joueur n°3 : ", SwingConstants.LEFT);
        labeljoueur3.setFont(new Font(jouer.getFont().getName(), jouer.getFont().getStyle(), (int) (jouer.getFont().getSize() * 1.5)));
        
        JLabel labeljoueurPseudo3 = new JLabel(" Pseudonyme : ", SwingConstants.LEFT);
        labeljoueurPseudo3.setFont(new Font(jouer.getFont().getName(), jouer.getFont().getStyle(), (int) (jouer.getFont().getSize() * 1.5)));

        JLabel labeljoueur4 = new JLabel(" Joueur n°4 : ", SwingConstants.LEFT);              
        labeljoueur4.setFont(new Font(jouer.getFont().getName(), jouer.getFont().getStyle(), (int) (jouer.getFont().getSize() * 1.5)));

        JLabel labeljoueurPseudo4 = new JLabel(" Pseudonyme : ", SwingConstants.LEFT);
        labeljoueurPseudo4.setFont(new Font(jouer.getFont().getName(), jouer.getFont().getStyle(), (int) (jouer.getFont().getSize() * 1.5)));

        JLabel labeljoueurRole1 = new JLabel("Rôle :", SwingConstants.CENTER);
        labeljoueurRole1.setFont(new Font(jouer.getFont().getName(), jouer.getFont().getStyle(), (int) (jouer.getFont().getSize() * 1.5)));
        JLabel labeljoueurRole2 = new JLabel("Rôle :", SwingConstants.CENTER);
        labeljoueurRole2.setFont(new Font(jouer.getFont().getName(), jouer.getFont().getStyle(), (int) (jouer.getFont().getSize() * 1.5)));
        JLabel labeljoueurRole3 = new JLabel("Rôle :", SwingConstants.CENTER);
        labeljoueurRole3.setFont(new Font(jouer.getFont().getName(), jouer.getFont().getStyle(), (int) (jouer.getFont().getSize() * 1.5)));
        JLabel labeljoueurRole4 = new JLabel("Rôle :", SwingConstants.CENTER);
        labeljoueurRole4.setFont(new Font(jouer.getFont().getName(), jouer.getFont().getStyle(), (int) (jouer.getFont().getSize() * 1.5)));

        JLabel labeldifficulte = new JLabel(" Difficulté : ");
        labeldifficulte.setFont(new Font(jouer.getFont().getName(), jouer.getFont().getStyle(), (int) (jouer.getFont().getSize() * 1.5)));

        role1 = new JComboBox(nomRoles);
        role1.setSelectedIndex(0);
        role1.setBackground(Color.WHITE);
        role1.setFont(new Font(role1.getFont().getName(), role1.getFont().getStyle(), (int) (role1.getFont().getSize() * 1.5)));

        role2 = new JComboBox(nomRoles);
        role2.setSelectedIndex(1);
        role2.setBackground(Color.WHITE);
        role2.setFont(new Font(role2.getFont().getName(), role2.getFont().getStyle(), (int) (role2.getFont().getSize() * 1.5)));

        role3 = new JComboBox(nomRoles);
        role3.setSelectedIndex(2);
        role3.setFont(new Font(role3.getFont().getName(), role3.getFont().getStyle(), (int) (role3.getFont().getSize() * 1.5)));

        role4 = new JComboBox(nomRoles);
        role4.setSelectedIndex(3);
        role4.setFont(new Font(role4.getFont().getName(), role4.getFont().getStyle(), (int) (role4.getFont().getSize() * 1.5)));

        niveauDepart = new JComboBox(niveauEau);
        niveauDepart.setBackground(Color.WHITE);
        niveauDepart.setFont(new Font(niveauDepart.getFont().getName(), niveauDepart.getFont().getStyle(), (int) (niveauDepart.getFont().getSize() * 1.5)));

        listRole = new ArrayList<>();
        listRole.add(role1);
        listRole.add(role2);
        listRole.add(role3);
        listRole.add(role4);

        //ACTION DU JCOMBOX NB JOUEURS
        nbJoueurs = new JComboBox(nombreJoueur);
        nbJoueurs.setBackground(Color.WHITE);
        nbJoueurs.setFont(new Font(nbJoueurs.getFont().getName(), nbJoueurs.getFont().getStyle(), (int) (nbJoueurs.getFont().getSize() * 1.5)));

        nbJoueurs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch ((int) nbJoueurs.getSelectedItem()) {
                    case 2:
                        labeldifficulte.setForeground(Color.WHITE);
                        choisirnbjoueur.setForeground(Color.WHITE);
                        labeljoueur1.setForeground(Color.WHITE);
                        labeljoueur2.setForeground(Color.WHITE);
                        labeljoueur3.setForeground(Color.WHITE);
                        labeljoueur4.setForeground(Color.WHITE);
                        labeljoueurPseudo4.setForeground(Color.WHITE);
                        labeljoueurPseudo3.setForeground(Color.WHITE);
                        labeljoueurPseudo4.setForeground(Color.WHITE);
                        labeljoueurRole1.setForeground(Color.WHITE);
                        labeljoueurRole2.setForeground(Color.WHITE);
                        labeljoueurRole3.setForeground(Color.WHITE);
                        labeljoueurRole4.setForeground(Color.WHITE);
                        pseudo3.setEnabled(false);
                        pseudo3.setBackground(new Color(224, 224, 224));
                        pseudo4.setEnabled(false);
                        pseudo4.setBackground(new Color(224, 224, 224));
                        role3.setEnabled(false);
                        role4.setEnabled(false);
                        pseudo3.setText("");
                        pseudo4.setText("");
                        break;
                    case 3:
                        labeldifficulte.setForeground(Color.WHITE);
                        choisirnbjoueur.setForeground(Color.WHITE);
                        labeljoueur1.setForeground(Color.WHITE);
                        labeljoueur2.setForeground(Color.WHITE);
                        labeljoueur3.setForeground(Color.WHITE);
                        labeljoueur4.setForeground(Color.WHITE);
                        labeljoueurPseudo3.setForeground(Color.WHITE);
                        labeljoueurPseudo4.setForeground(Color.WHITE);
                        labeljoueurRole1.setForeground(Color.WHITE);
                        labeljoueurRole2.setForeground(Color.WHITE);
                        labeljoueurRole3.setForeground(Color.WHITE);
                        labeljoueurRole4.setForeground(Color.WHITE);
                        pseudo3.setEnabled(true);
                        pseudo3.setBackground(Color.WHITE);
                        pseudo4.setEnabled(false);
                        pseudo4.setBackground(new Color(224, 224, 224));
                        role3.setEnabled(true);
                        role3.setBackground(Color.WHITE);
                        role4.setEnabled(false);
                        role4.setBackground(new Color(224, 224, 224));
                        pseudo4.setText("");
                        break;
                    case 4:
                        labeljoueur3.setForeground(Color.WHITE);
                        labeljoueur4.setForeground(Color.WHITE);
                        labeljoueurPseudo3.setForeground(Color.WHITE);
                        labeljoueurPseudo4.setForeground(Color.WHITE);
                        labeljoueurRole3.setForeground(Color.WHITE);
                        labeljoueurRole4.setForeground(Color.WHITE);
                        pseudo3.setEnabled(true);
                        pseudo3.setBackground(Color.WHITE);
                        pseudo4.setEnabled(true);
                        pseudo4.setBackground(Color.WHITE);
                        role3.setEnabled(true);
                        role3.setBackground(Color.WHITE);
                        role4.setEnabled(true);
                        role4.setBackground(Color.WHITE);
                        break;
                }
            }
        });

        nbJoueurs.setSelectedIndex(0);
        
        background.add(new JLabel(" "));
        background.add(new JLabel(" "));
        background.add(new JLabel(" "));
        background.add(new JLabel(" "));
        background.add(labeldifficulte);
        background.add(niveauDepart);
        for (int i = 0; i < 6; i++) {
            background.add(new JLabel(" "));
        }
        
        background.add(choisirnbjoueur);
        background.add(nbJoueurs);
        background.add(new JLabel(" "));
        background.add(new JLabel(" "));
        background.add(labeljoueur1);
        background.add(pseudo1);
        background.add(labeljoueurRole1);
        background.add(role1);
        background.add(labeljoueur2);
        background.add(pseudo2);
        background.add(labeljoueurRole2);
        background.add(role2);
        background.add(labeljoueur3);
        background.add(pseudo3);
        background.add(labeljoueurRole3);
        background.add(role3);
        background.add(labeljoueur4);
        background.add(pseudo4);
        background.add(labeljoueurRole4);
        background.add(role4);
        panelBas.add(retour);
        panelBas.add(new JLabel(" "));
        panelBas.add(jouer);
        panelBas.add(new JLabel(" "));
        panelBas.add(quitter);
    }

    //METHODES
    //Affiche la fenetre
    public void afficher() {
        this.joueur.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == retour) {
            int rep = JOptionPane.showConfirmDialog(null, "Etes-vous sûr(e) de vouloir retourner au menu ?", "Message de confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (rep == JOptionPane.YES_OPTION) {
                this.joueur.setVisible(false);
                notifierObservateur(new Message(TypeEnumMenuPrincipal.MENU_PRINCIPAL));
            }
        }

        if (e.getSource() == quitter) {
            int rep = JOptionPane.showConfirmDialog(null, "Etes-vous sûr(e) de vouloir quitter le jeu ?", "Message de confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (rep == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }

        if (e.getSource() == jouer) {
            ArrayList<String> usedRole = new ArrayList<>();
            for (int i = 0; i < (int) nbJoueurs.getSelectedItem(); i++) {
                if (usedRole.contains((String) listRole.get(i).getSelectedItem())) {
                    JOptionPane.showMessageDialog(null, "Chaque joueur doit avoir un rôle différent", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return; //quitte la fonction
                }
                if ((String) listRole.get(i).getSelectedItem() != nomRoles[nomRoles.length - 1]) {
                    usedRole.add((String) listRole.get(i).getSelectedItem());
                }
            }
            for (int i = 0; i < (int) nbJoueurs.getSelectedItem(); i++) {
                if (listPseudo.get(i).getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Le champ joueur ne peut pas être vide", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return; //quitte la fonction
                }
            }

            this.joueur.setVisible(false);
            Message msg = new Message(TypeEnumMenuPrincipal.MENU_JOUER);
            ArrayList<String> infos = new ArrayList<>();
            switch ((String) niveauDepart.getSelectedItem()) {
                case "Novice":
                    infos.add("0");
                    break;
                case "Normal":
                    infos.add("1");
                    break;
                case "Elite":
                    infos.add("2");
                    break;
                case "Légendaire":
                    infos.add("3");
                    break;
            }
            infos.add(String.valueOf(nbJoueurs.getSelectedItem()));
            infos.add(pseudo1.getText());
            infos.add((String) role1.getSelectedItem());
            infos.add(pseudo2.getText());
            infos.add((String) role2.getSelectedItem());

            if ((int) nbJoueurs.getSelectedItem() >= 3) {
                infos.add(pseudo3.getText());
                infos.add((String) role3.getSelectedItem());
            }
            if ((int) nbJoueurs.getSelectedItem() >= 4) {
                infos.add(pseudo4.getText());
                infos.add((String) role4.getSelectedItem());
            }
            msg.setAdditionnal(infos);
            notifierObservateur(msg);
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
