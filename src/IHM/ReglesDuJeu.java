/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import IleInterdite.Message;
import IleInterdite.Observateur;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

/**
 *
 * @author laraicha
 */
public class ReglesDuJeu extends JFrame implements ActionListener {

  private JFrame regles;
  private JButton suivant;
  private JButton retour;
  private JButton quitter;
  private JScrollBar images;
  
   public ReglesDuJeu() {
            
    regles = new JFrame();
    regles.setTitle("Règles du jeu");
    regles.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    /*regles.setSize(500, 500);*/
    regles.setExtendedState(JFrame.MAXIMIZED_BOTH);
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    regles.setLocation(dim.width/2-regles.getSize().width/2, dim.height/2-regles.getSize().height/2);
    
    JPanel mainPanel = new JPanel(new BorderLayout());
    regles.add(mainPanel);
    
    JPanel panelHaut = new JPanel() ;
    mainPanel.add(panelHaut, BorderLayout.NORTH);
    
    JPanel panelCentre = new JPanel();
    mainPanel.add(panelCentre, BorderLayout.CENTER);
    
    JPanel panelDroit = new JPanel() ;
    mainPanel.add(panelDroit, BorderLayout.EAST);
    
    JPanel panelGauche = new JPanel() ;
    mainPanel.add(panelGauche, BorderLayout.WEST);
    
    JPanel panelBas = new JPanel(new GridLayout(2, 6));
    mainPanel.add(panelBas, BorderLayout.SOUTH);
    
    JLabel labelTitre = new JLabel("Règles du jeu");
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
    
    images = new JScrollBar();
    
    ImageIcon image = new ImageIcon("C:\\Users\\laraicha\\Downloads\\Page1.jpg");
    JLabel label = new JLabel("", image, JLabel.CENTER);
    /*label.setBounds(0, 0, this.getWidth(), this.getHeight());*/
    /*label.setPreferredSize(dim);*/
    panelCentre.add(label);
    panelDroit.add(images);
   
}
   
    public void afficher() {
        this.regles.setVisible(true);
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

    @Override
    public void actionPerformed(ActionEvent e) {       
        if(e.getSource() == quitter) {
            int rep = JOptionPane.showConfirmDialog(null,"Etes-vous sûr(e) de vouloir quitter ?", "Message de confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);                   
            if (rep == JOptionPane.YES_OPTION) {
            System.exit(0);
            }
        }
        
    }
   
   }
