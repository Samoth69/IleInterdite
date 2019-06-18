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
public class Page3 extends JLayeredPane implements ActionListener {

    ReglesDuJeuContainer page7;
    ReglesDuJeuContainer page8;
    private JButton retour;
    private JButton quitter;
    private JFrame pg3;

    public Page3() {
        pg3 = new JFrame();
        pg3.setTitle("Règles du jeu");
        pg3.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        /*regles.setSize(500, 500);*/
        pg3.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        pg3.setLocation(dim.width / 2 - pg3.getSize().width / 2, dim.height / 2 - pg3.getSize().height / 2);

        JPanel mainPanel = new JPanel(new BorderLayout());
        pg3.add(mainPanel);

        JPanel panelHaut = new JPanel();
        mainPanel.add(panelHaut, BorderLayout.NORTH);

        JPanel panelCentre = new JPanel();
        mainPanel.add(panelCentre, BorderLayout.CENTER);

        JPanel panelBas = new JPanel(new GridLayout(2, 2));
        mainPanel.add(panelBas, BorderLayout.SOUTH);

        JLabel labelTitre = new JLabel("Page n°3");
        labelTitre.setFont(new Font(labelTitre.getFont().getName(), labelTitre.getFont().getStyle(), (int) (labelTitre.getFont().getSize() * 2.0)));
        panelHaut.add(labelTitre);

        retour = new JButton("Retour");
        retour.addActionListener(this);
        quitter = new JButton("Quitter");
        quitter.addActionListener(this);

        panelBas.add(new JLabel(""));
        panelBas.add(new JLabel(""));
        panelBas.add(new JLabel(""));
        panelBas.add(retour);
        panelBas.add(new JLabel(""));
        panelBas.add(quitter);
        // Récupération du chemin vers le dossier contenant les images
        String imgFolder = System.getProperty("user.dir") + "/src/RessourcesRegles/";

        // Instanciation d'une image pour le jardin
        // Remarque : les positions et les tailles sont en dur !
        // Dans l'idéal, il faudrait recevoir la taille de la fenêtre et 
        // faire quelque chose de proportionnel.
        // Instanciation d'une image pour le dragon
        page7 = new ReglesDuJeuContainer(imgFolder + "Page7.jpg", 0, 0, 550, 850);
        this.add(page7, 0);

        // Instanciation d'une image pour le dragon
        page8 = new ReglesDuJeuContainer(imgFolder + "Page8.jpg", 550, 0, 550, 850);
        this.add(page8, 0);
        
        panelCentre.add(page7);
        panelCentre.add(page8);
        
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
        if (this.page7 != null) {
            this.page7.paintComponent(g);
        }
        if (this.page8 != null) {
            this.page8.paintComponent(g);
        }

    }

    public void afficher() {
        this.pg3.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quitter) {
            int rep = JOptionPane.showConfirmDialog(null, "Etes-vous sûr(e) de vouloir quitter ?", "Message de confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (rep == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }

        if (e.getSource() == retour) {
            Page2 page2 = new Page2();
            page2.afficher();
            this.pg3.setVisible(false);
        }
    }
}
