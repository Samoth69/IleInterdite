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
public class Page2 extends JLayeredPane implements ActionListener {

    ReglesDuJeuContainer page4;
    ReglesDuJeuContainer page5;
    ReglesDuJeuContainer page6;
    private JButton suivant;
    private JButton retour;
    private JButton quitter;
    private JFrame pg2;

    public Page2() {
        pg2 = new JFrame();
        pg2.setTitle("Règles du jeu");
        pg2.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        /*regles.setSize(500, 500);*/
        pg2.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        pg2.setLocation(dim.width / 2 - pg2.getSize().width / 2, dim.height / 2 - pg2.getSize().height / 2);

        JPanel mainPanel = new JPanel(new BorderLayout());
        pg2.add(mainPanel);

        JPanel panelHaut = new JPanel();
        mainPanel.add(panelHaut, BorderLayout.NORTH);

        JPanel panelCentre = new JPanel();
        mainPanel.add(panelCentre, BorderLayout.CENTER);

        JPanel panelBas = new JPanel(new GridLayout(2, 3));
        mainPanel.add(panelBas, BorderLayout.SOUTH);

        JLabel labelTitre = new JLabel("Page n°2");
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
        // Instanciation d'une image pour le dragon
        page4 = new ReglesDuJeuContainer(imgFolder + "Page4.jpg", 0, 0, 550, 850);
        this.add(page4, -2000);

        // Instanciation d'une image pour le dragon
        page5 = new ReglesDuJeuContainer(imgFolder + "Page5.jpg", 550, 0, 550, 850);
        this.add(page5, -1000);

        // Instanciation d'une image pour le dragon
        page6 = new ReglesDuJeuContainer(imgFolder + "Page6.jpg", 1100, 0, 550, 850);
        this.add(page6, 0);
        
        panelCentre.add(page4);
        panelCentre.add(page5);
        panelCentre.add(page6);
        
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
        if (this.page4 != null) {
            this.page4.paintComponent(g);
        }
        if (this.page5 != null) {
            this.page5.paintComponent(g);
        }
        if (this.page6 != null) {
            this.page6.paintComponent(g);
        }

    }

    public void afficher() {
        this.pg2.setVisible(true);
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
            Page3 page3 = new Page3();
            page3.afficher();
            this.pg2.setVisible(false);
        }

        if (e.getSource() == retour) {
            Page1 page1 = new Page1();
            page1.afficher();
            this.pg2.setVisible(false);
        }
    }
}
