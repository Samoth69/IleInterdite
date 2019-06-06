/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Cartes.CarteAction;
import Cartes.CarteRouge;
import Enumerations.TypeEnumCarteAction;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import IleInterdite.ControlleurJeuSecondaire;
import IleInterdite.Grille;
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
import javax.swing.JButton;


/**
 *
 * @author mariottp
 */
public class VuDefausse implements Observe{
    
    private JFrame window;
    private ControlleurJeuSecondaire cj;
    private ArrayList<CarteRouge> carteDuJoueur;  //Array des cartes a disposition du joueur
    private ArrayList<CarteRouge> carteSelectionne; //Array des cartes selectionnes
    private Observateur o;
    
    
    VuDefausse(/**ControlleurJeuSecondaire cj  A DECOMMENTER**/){
        this.cj = cj;
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        // Définit la taille de la fenêtre en pixels
        window.setSize(600, 200);
        window.setTitle("Defaussez une carte");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        
        //get les cartes en mains du joueur
        //carteDuJoueur = cj.getJoueurEntrainDeJouer().getCartes();     A DECOMMENTER
        carteDuJoueur = new ArrayList<CarteRouge>();
        CarteAction le1 = new CarteAction("le1", TypeEnumCarteAction.HELICOPTERE);
        CarteAction le2 = new CarteAction("le2", TypeEnumCarteAction.SAC_DE_SABLE);
        carteDuJoueur.add(le1);
        carteDuJoueur.add(le2);
        
        JPanel grilleCarte = new JPanel(new GridLayout(2, carteDuJoueur.size()));
        grilleCarte.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
        
        for(int i = 0; i < carteDuJoueur.size(); i++)
        {
            JPanel pn = new JPanel(new BorderLayout());
            JLabel lb = new JLabel(carteDuJoueur.get(i).getDescription());
            
            lb.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent arg0) {
                    if(pn.getBackground() != Color.red)
                    {
                        pn.setBackground(Color.red);
                    }
                    else
                    {
                        pn.setBackground(UIManager.getColor("Panel.background"));   //couleur par defaut
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
        
        terminer.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                for(int i = 0; i < carteDuJoueur.size(); i++)
                {
                    if(grilleCarte.getComponent(i).getBackground() == Color.red)
                    {
                        carteSelectionne.add(carteDuJoueur.get(i));
                    }
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
        
        
        grilleCarte.add(terminer);
        
        window.add(grilleCarte);
    }

    public void afficher(){
        this.window.setVisible(true);
    }
    
    public void addObservateur(Observateur o){
        this.o = o;
    }
    
    public void notifierObservateur(Message m){
            if (o != null) {
            o.traiterMessage(m);
        }
    }
    
    public static void main(String[] args) {
        new VuDefausse().afficher();
    }
    
}
