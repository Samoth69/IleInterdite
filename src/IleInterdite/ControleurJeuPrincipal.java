/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import IHM.Joueurs;
import IHM.Menu;
import IHM.Plateau;
import Personnages.*;
import java.util.ArrayList;

/**
 *
 * @author Toto
 */
public class ControleurJeuPrincipal implements Observateur{

    /**
     * @param args the command line arguments
     */
    private Joueurs joueurs = new Joueurs();
    private Menu menu = new Menu();
    private Plateau plateau;
    private ControleurJeuSecondaire cj;
    
    public static void main(String[] args) {
        new ControleurJeuPrincipal();
    }
    
    private ControleurJeuPrincipal() {
        menu.addObservateur(this);
        joueurs.addObservateur(this);
        
        menu.afficher();
    }

    @Override
    public void traiterMessage(Message m) {
        switch (m.getMessageType2()) {
            case MENU_JOUEURS:
                joueurs.afficher();
                break;
            case MENU_PRINCIPAL:
                menu.afficher();
                break;
            case MENU_JOUER:
                ArrayList<String> infos = m.getAdditionnal();
                ArrayList<Personnage> perso = new ArrayList<>();
                int counter = 2;
                int joueurCompter = 0;

                while (joueurCompter < Integer.valueOf(infos.get(1))) {
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
                
                cj = new ControleurJeuSecondaire(perso, Integer.parseInt(infos.get(0)));
                plateau = new Plateau(perso, cj);
                cj.addObservateur(plateau);
                
                for (Personnage p : perso) {
                    p.setGrille(cj.getIle());
                }
                
                plateau.afficher();
                //System.out.println("coucou");
        }
    }
}
