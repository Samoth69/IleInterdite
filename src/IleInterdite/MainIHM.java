/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import Enumerations.TypeEnumMessage;
import IHM.Joueurs;
import IHM.Menu;
import java.util.ArrayList;

/**
 *
 * @author Toto
 */
public class MainIHM implements Observateur{

    /**
     * @param args the command line arguments
     */
    private Joueurs joueurs = new Joueurs();
    private Menu menu = new Menu();
    private ControlleurJeu cj;
    
    
    public static void main(String[] args) {
        new MainIHM();
    }
    
    private MainIHM() {
        menu.addObservateur(this);
        joueurs.addObservateur(this);
        
        menu.afficher();
    }

    @Override
    public void traiterMessage(Message m) {
        switch (m.getMessageType()) {
            case MENU_JOUEURS:
                joueurs.afficher();
                break;
            case MENU_PRINCIPAL:
                menu.afficher();
                break;
            case MENU_JOUER:
                ArrayList<String> infos = m.getAdditionnal();
                for (int i = 1; i < Integer.parseInt(infos.get(0)); i++) {
                    //Personnage perso;
                  //TODO  
                }
                
                //cj = new ControlleurJeu();
                break;
            default:
                break;
        }
    }
    
}
