/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import Enumerations.TypeEnumMessage;
import IHM.Joueurs;
import IHM.Menu;
import IHM.Plateau;
import Personnages.*;
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
    private Plateau plateau;
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
                ArrayList<Personnage> perso = new ArrayList<>();
                int counter = 1;
                int joueurCompter = 0;
                
                while (joueurCompter < Integer.valueOf(infos.get(0))) {
                    switch(infos.get(counter + 1)) {
                        case "Explorateur":
                            perso.add(new Explorateur(infos.get(counter), null));
                            break;
                        case "Ingénieur":
                            perso.add(new Ingenieur(infos.get(counter), null));
                            break;
                        case "Navigateur":
                            perso.add(new Navigateur(infos.get(counter), null));
                            break;
                        case "Pilote":
                            perso.add(new Pilote(infos.get(counter), null));
                            break;
                        case "Plongeur":
                            perso.add(new Plongeur(infos.get(counter), null));
                            break;
                    }
                    joueurCompter++;
                    counter += 2;
                }
                
                cj = new ControlleurJeu(perso);
                plateau = new Plateau(perso, cj);
                plateau.addObservateur(this);
                
                for (Personnage p : perso) {
                    p.setGrille(cj.getIle());
                }
                
                plateau.afficher();
                //System.out.println("coucou");
        }
    }
}
