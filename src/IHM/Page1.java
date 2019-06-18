/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author laraicha
 */
public class Page1 extends JLayeredPane implements ActionListener {

    ReglesDuJeuContainer page1;
    ReglesDuJeuContainer page2;
    ReglesDuJeuContainer page3;
    private JButton suivant;
    private JButton retour;
    private JButton quitter;
    private JFrame pg1;

    public Page1() {
        pg1 = new JFrame();
        pg1.setTitle("Règles du jeu");
        pg1.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        /*regles.setSize(500, 500);*/
        pg1.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        pg1.setLocation(dim.width / 2 - pg1.getSize().width / 2, dim.height / 2 - pg1.getSize().height / 2);

        JPanel mainPanel = new JPanel(new BorderLayout());
        pg1.add(mainPanel);

        JPanel panelHaut = new JPanel();
        mainPanel.add(panelHaut, BorderLayout.NORTH);

        JPanel panelCentre = new JPanel();
        mainPanel.add(panelCentre, BorderLayout.CENTER);

        JPanel panelBas = new JPanel(new GridLayout(2, 3));
        mainPanel.add(panelBas, BorderLayout.SOUTH);

        JLabel labelTitre = new JLabel("Page n°1");
        labelTitre.setFont(new Font(labelTitre.getFont().getName(), labelTitre.getFont().getStyle(), (int) (labelTitre.getFont().getSize() * 2.0)));
        panelHaut.add(labelTitre);

        retour = new JButton("Retour");
        retour.addActionListener(this);
        suivant = new JButton("Suivant");
        suivant.addActionListener(this);
        quitter = new JButton("Quitter");
        quitter.addActionListener(this);

        panelBas.add(new JLabel(""));
        panelBas.add(new JLabel(""));
        panelBas.add(new JLabel(""));
        panelBas.add(retour);
        panelBas.add(suivant);
        panelBas.add(quitter);
        // Récupération du chemin vers le dossier contenant les images
        String imgFolder = System.getProperty("user.dir") + "/src/RessourcesRegles/";

        // Instanciation d'une image pour le jardin
        // Remarque : les positions et les tailles sont en dur !
        // Dans l'idéal, il faudrait recevoir la taille de la fenêtre et 
        // faire quelque chose de proportionnel.
        page1 = new ReglesDuJeuContainer(imgFolder + "Page1.jpg", 0, 0, 550, 850);
        this.add(page1, -2000);

        // Instanciation d'une image pour la statue
        page2 = new ReglesDuJeuContainer(imgFolder + "Page2.jpg", 550, 0, 550, 850);
        this.add(page2, -1000);

        // Instanciation d'une image pour le dragon
        page3 = new ReglesDuJeuContainer(imgFolder + "Page3.jpg", 1100, 0, 550, 850);
        this.add(page3, 0);
        
        panelCentre.add(page1);
        panelCentre.add(page2);
        panelCentre.add(page3);
        
        // Repaint : déclenche la méthode paintComponent
        this.repaint();        
    }

    @Override
    /**
     * paintComponent permet de gérer l'affichage / la mise à jour des images, à
     * condition que le paintComponent de chaque objet soit appelé avec le même
     * contexte graphique (Graphics)
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.page1 != null) {
            this.page1.paintComponent(g);
        }
        if (this.page2 != null) {
            this.page2.paintComponent(g);
        }
        if (this.page3 != null) {
            this.page3.paintComponent(g);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quitter) {
            int rep = JOptionPane.showConfirmDialog(null, "Etes-vous sûr(e) de vouloir quitter ?", "Message de confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (rep == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
        if (e.getSource() == suivant) {
            Page2 page2 = new Page2();
            page2.afficher();
            this.pg1.setVisible(false);
        }

        if (e.getSource() == retour) {
            int rep = JOptionPane.showConfirmDialog(null, "Etes-vous sûr(e) de vouloir revenir au menu ?", "Message de confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (rep == JOptionPane.YES_OPTION) {
                Menu menu = new Menu();
                menu.afficher();
                this.pg1.setVisible(false);
            }
        }
    }

    public void afficher() {
        this.pg1.setVisible(true);
    }
}
