/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Cartes.CarteAction;
import Cartes.CarteInondation;
import Cartes.CarteRouge;
import Cartes.CarteTresor;
import Enumerations.TypeEnumCarteAction;
import Enumerations.TypeEnumMessage;
import Enumerations.TypeEnumTresors;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import IleInterdite.ControlleurJeuSecondaire;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;


/**
 *
 * @author mariottp
 */
public class VuDefausse implements Observe{
    
    private final JFrame window = new JFrame();
    private JPanel mainPanel;
    private ArrayList<CarteRouge> carteDuJoueur = new ArrayList<CarteRouge>();  //Array des cartes a disposition du joueur
    private ArrayList<CarteInondation> carteInondation = new ArrayList<CarteInondation>();  //Array des cartes inondées a afficher
    private ArrayList<String> carteSelectionne = new ArrayList<String>(); //Array des cartes selectionnes
    private Observateur o;
    
    VuDefausse(ArrayList carte){
        window.setDefaultCloseOperation(javax.swing.JFrame.HIDE_ON_CLOSE);
        window.setTitle("Defaussez une carte");
        // Définit la taille de la fenêtre en pixels
        window.setSize(600, 200);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        
        mainPanel = new JPanel(new BorderLayout());
        
        if (!carte.isEmpty()) {
            if(carte.get(0) instanceof CarteRouge)
            {
                window.setTitle("Defaussez une carte");

                vuDefausse((ArrayList<CarteRouge>) carte);
            }
            else/* if (carte.get(0) instanceof CarteInondation)*/
            {
                window.setTitle("CarteInondation");

                vuInondation((ArrayList<CarteInondation>) carte);
            }
        }
        window.add(mainPanel);
        window.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent arg0) {}

            @Override
            public void windowClosing(WindowEvent arg0) {
                notifierObservateur(new Message(TypeEnumMessage.UNLOCK_PLATEAU));
            }

            @Override
            public void windowClosed(WindowEvent arg0) {}

            @Override
            public void windowIconified(WindowEvent arg0) {}

            @Override
            public void windowDeiconified(WindowEvent arg0) {}

            @Override
            public void windowActivated(WindowEvent arg0) {}

            @Override
            public void windowDeactivated(WindowEvent arg0) {}
        });
    }
    
    private void vuDefausse(ArrayList<CarteRouge> carteJoueur){
        
        carteDuJoueur = carteJoueur;
        
        //carteDuJoueur.add(new CarteAction("lul", TypeEnumCarteAction.HELICOPTERE));
        
        //JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel grilleCarte = new JPanel(new GridLayout(1, carteDuJoueur.size()));
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
                        carteSelectionne.add(carteDuJoueur.get(i).getDescription());
                    }
                }
                /**for(int i = 0; i < carteSelectionne.size(); i++)
                {
                    System.out.println(carteSelectionne.get(i));
                }**/
                
                notifierObservateur(new Message(TypeEnumMessage.DEFAUSSE_CARTE, carteSelectionne));
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
        
        mainPanel.add(grilleCarte, BorderLayout.CENTER);
        mainPanel.add(terminer, BorderLayout.SOUTH);
        window.add(mainPanel);
    }
    
    private void vuInondation(ArrayList<CarteInondation> carteInonder){
        
        carteInondation = carteInonder;
        
        //JPanel mainPanel = new JPanel();
        JPanel grilleCarte = new JPanel(new GridLayout(1, carteInondation.size()));
        grilleCarte.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
        
        for(int i = 0; i < carteInondation.size(); i++)
        {
            JPanel pn = new JPanel(new BorderLayout());
            JLabel lb = new JLabel(carteInondation.get(i).getNom());
            
            pn.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
            pn.add(lb);
            grilleCarte.add(pn);
        }
        
        JButton ok = new JButton("ok");
        
        ok.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                window.setVisible(false);
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
        
        mainPanel.add(grilleCarte, BorderLayout.CENTER);
        mainPanel.add(ok, BorderLayout.SOUTH);
        this.window.add(mainPanel);
    }

    public void afficher(){
        window.setVisible(true);
    }
    
    public void addObservateur(Observateur o){
        this.o = o;
    }
    
    public void notifierObservateur(Message m){
            if (o != null) {
                o.traiterMessage(m);
        }
    }
    
}
