/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Cartes.CarteAction;
import Cartes.CarteInondation;
import Cartes.CarteRouge;
import Enumerations.TypeEnumMessage;
import Enumerations.TypeEnumTresors;

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
import Personnages.Personnage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author mariottp
 */
public class VuDefausse extends JDialog {

    private JDialog dialog = this;
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private ArrayList<CarteRouge> carteDuJoueur = new ArrayList<>();  //Array des cartes a disposition du joueur
    private ArrayList<CarteRouge> carteSelectionne = new ArrayList<>(); //Array des cartes selectionnes
    private int nombreDeCarteADel; //nombre de carte à enlever de la main actuel
    private JDialog window;
    private Personnage persoQuiRecoitCarte;
    private double actionRestante = 0;
    private boolean modeDefausse = false;
    private boolean modeActionSpe = false;
    
    
    //carteJoueur: liste des cartes à afficher
    //titre: titre de la fenêtre
    //nombreDeCarteADel: nombre de carte à supprimer
    public VuDefausse(ArrayList<CarteRouge> carteJoueur, String titre, int nombreDeCarteADel){
        initialisation(carteJoueur, titre, nombreDeCarteADel);
        modeDefausse = true;
    }
    
    public VuDefausse(ArrayList<CarteRouge> carteJoueur, String titre){
        modeActionSpe = true;
        
        for(CarteRouge i : carteJoueur)
        {
            if(i instanceof CarteAction)
            {
                carteDuJoueur.add(i);
            }
        }
        
        carteJoueur.clear();
        carteJoueur.addAll(carteDuJoueur);
        carteDuJoueur.clear();
        
        initialisation(carteJoueur, titre, 1);
    }
    
    //Constructeur pour la vu donner carte
    //carteJoueur: liste des cartes à afficher
    //titre: titre de la fenêtre
    //nombreDeCarteADel: nombre de carte à supprimer
    //persos : liste des personnages sur la meme case du joueur en train de jouer
    public VuDefausse(ArrayList<CarteRouge> carteJoueur, String titre, int nombreDeCarteADel, ArrayList<Personnage> persos, double nbAction){
        
        String[] nomDesPersos = new String[persos.size()];
        
        for(int i = 0; i < persos.size(); i++)
        {
            nomDesPersos[i] = persos.get(i).getNom();
        }
        
        JComboBox boxPersos = new JComboBox(nomDesPersos);
        
        for(Personnage i : persos)
        {
            if(i.getNom() == boxPersos.getSelectedItem().toString())
            {
                persoQuiRecoitCarte = i;
            }
        }
        
        
        mainPanel.add(boxPersos, BorderLayout.NORTH);
        
        for(CarteRouge i : carteJoueur)
        {
            if(i.getTypeTresor() != TypeEnumTresors.AUCUN)
            {
                carteDuJoueur.add(i);
            }
        }
        carteJoueur.clear();
        carteJoueur.addAll(carteDuJoueur);
        carteDuJoueur.clear();
        
        actionRestante = nbAction;
        
        initialisation(carteJoueur, titre, nombreDeCarteADel);
    }
    
    //carteJoueur: liste des cartes à afficher
    //titre: titre de la fenêtre
    //nombreDeCarteADel: nombre de carte à supprimer
    private void initialisation(ArrayList<CarteRouge> carteJoueur, String titre, int nombreDeCarteADel) {
        this.window = this;
        this.nombreDeCarteADel = nombreDeCarteADel;
        //indique que ceci est un dialogue et va attendre que la fenêtre soit fermer AVANT de continuer le code.
        this.setModal(true);
        //action à faire quand on ferme la fenêtre (on indique de ne rien faire car la fermeture est géré plus bas)
        this.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle(titre);
        
        //panel principal
        
        this.add(mainPanel);

        
        
        
        //grid layout qui contient les cartes
        JPanel grilleCarte = new JPanel(new GridLayout(1, carteJoueur.size()));
        grilleCarte.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mainPanel.add(grilleCarte, BorderLayout.CENTER);

        int size = 0; //taille de la fenêtre (sera additionné par la boucle for en dessous)
        
        for (int i = 0; i < carteJoueur.size(); i++) {
            JPanel pn = new JPanel();
            
            //JLabel lb = new JLabel(carteJoueur.get(i).getDescription());
            ImageContainer ic = new ImageContainer(carteJoueur.get(i).getImage(), 0, 0, 190, 300);
            size += 205;
            
            final CarteRouge carte = carteJoueur.get(i);
            
            ic.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent arg0) {
                    if(modeDefausse)
                    {
                        if (pn.getBackground() != Color.red) 
                        {
                            pn.setBackground(Color.red);
                            carteSelectionne.add(carte);
                        }
                        else 
                        {
                            pn.setBackground(UIManager.getColor("Panel.background"));   //couleur par defaut
                            carteSelectionne.remove(carte);
                        } 
                    }
                    else
                    {
                        if(actionRestante > 0)
                        {
                            if (pn.getBackground() != Color.red) 
                            {
                                pn.setBackground(Color.red);
                                carteSelectionne.add(carte);
                                actionRestante--;
                            }
                            else 
                            {
                                pn.setBackground(UIManager.getColor("Panel.background"));   //couleur par defaut
                                carteSelectionne.remove(carte);
                                actionRestante++;
                            } 
                        }
                        else
                        {
                            if(pn.getBackground() == Color.red)
                            {
                                pn.setBackground(UIManager.getColor("Panel.background"));   //couleur par defaut
                                carteSelectionne.remove(carte);
                                actionRestante++;
                            }
                        }
                    }
                    
                }
                @Override
                public void mousePressed(MouseEvent arg0) {}
                @Override
                public void mouseReleased(MouseEvent arg0) {}
                @Override
                public void mouseEntered(MouseEvent arg0) {}
                @Override
                public void mouseExited(MouseEvent arg0) {}
            });

            pn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            pn.add(ic);
            grilleCarte.add(pn);
        }

        JButton terminer = new JButton("Terminer");
        terminer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifAvantFermeture();
               
            }

        });
        
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}

            @Override
            public void windowClosing(WindowEvent e) {
                if(modeDefausse)
                {
                    if(verifAvantFermeture()) {
                        window.dispose();
                    } 
                }
                else
                {
                    window.dispose();
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {}

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {}

            @Override
            public void windowDeactivated(WindowEvent e) {}
        });

        mainPanel.add(terminer, BorderLayout.SOUTH);
        this.setSize(size, 380);
        
        //permet de mettre la fenêtre au milieu de l'écran
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }
    
    //est appellé avant la fermeture de la fenêtre
    private boolean verifAvantFermeture() {
        if (carteSelectionne.size() >= nombreDeCarteADel) {
            dialog.setVisible(false);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner au moins " + nombreDeCarteADel + " carte(s)");
            return false;
        }
    }

    public ArrayList<CarteRouge> getSelectedItems() {
        return carteSelectionne;
    }
    
    public Personnage getPersoQuiRecoitCartes(){
        return persoQuiRecoitCarte;
    }
    
    public double getNbActionRestante(){
        return actionRestante;
    }
}
