/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Cartes.CarteInondation;
import Cartes.CarteRouge;
import Enumerations.TypeEnumMessage;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

import IleInterdite.Message;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.UIManager;
import IleInterdite.Observe;
import IleInterdite.Observateur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JDialog;

/**
 *
 * @author mariottp
 */
public class VuDefausse extends JDialog {

    private JDialog dialog = this;
    private JPanel mainPanel;
    private ArrayList<CarteRouge> carteDuJoueur = new ArrayList<>();  //Array des cartes a disposition du joueur
    private ArrayList<CarteRouge> carteSelectionne = new ArrayList<>(); //Array des cartes selectionnes

    public VuDefausse(ArrayList<CarteRouge> carteJoueur, String titre) {
        //indique que ceci est un dialogue et va attendre que la fenêtre soit fermer AVANT de continuer le code.
        this.setModal(true);
        //action à faire quand on ferme la fenêtre
        this.setDefaultCloseOperation(javax.swing.JFrame.HIDE_ON_CLOSE);
        this.setTitle(titre);
        // Définit la taille de la fenêtre en pixels
        this.setSize(600, 200);

        //permet de mettre la fenêtre au milieu de l'écran
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        mainPanel = new JPanel(new BorderLayout());

        this.add(mainPanel);

        JPanel grilleCarte = new JPanel(new GridLayout(1, carteJoueur.size()));
        grilleCarte.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        for (int i = 0; i < carteJoueur.size(); i++) {
            JPanel pn = new JPanel(new BorderLayout());
            JLabel lb = new JLabel(carteJoueur.get(i).getDescription());

            final CarteRouge carte = carteJoueur.get(i);
            
            lb.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent arg0) {
                    if (pn.getBackground() != Color.red) {
                        pn.setBackground(Color.red);
                        carteSelectionne.add(carte);
                    } else {
                        pn.setBackground(UIManager.getColor("Panel.background"));   //couleur par defaut
                        carteSelectionne.remove(carte);
                    }
                }

                @Override
                public void mousePressed(MouseEvent arg0) {

                }

                @Override
                public void mouseReleased(MouseEvent arg0) {

                }

                @Override
                public void mouseEntered(MouseEvent arg0) {
                }

                @Override
                public void mouseExited(MouseEvent arg0) {
                }
            });

            pn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            pn.add(lb);
            grilleCarte.add(pn);
        }

        JButton terminer = new JButton("Terminer");
        terminer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }

        });

        mainPanel.add(grilleCarte, BorderLayout.CENTER);
        mainPanel.add(terminer, BorderLayout.SOUTH);
        this.add(mainPanel);
    }

    public ArrayList<CarteRouge> getSelectedItems() {
        return carteSelectionne;
    }
}
