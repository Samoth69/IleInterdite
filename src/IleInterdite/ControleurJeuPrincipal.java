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
                        case "Aléatoire":
                            //  Variable aléatoire pour le switch
                            int valAle = 1 + (int)(Math.random() * ((5 - 1) + 1));
                            //  Variable qui indique si un role de joueur (ex : Explo) est disponible
                            boolean joueurOk = true;
                            
                            //  boucle do while
                            do
                            {
                                //  reset de la variable sur true
                                joueurOk = true;
                                
                                switch(valAle)
                                {
                                    case 1:
                                        //  Verifie que le role est bien dispo
                                        for(Personnage p : perso)
                                        {
                                            //  Si il y a deja un role Explo
                                            if(p instanceof Explorateur)
                                            {
                                                //  variable qui indique si un role est dispo est passé sur false
                                                joueurOk = false;
                                                //  si la variable aléatoire depasse les cas du switch
                                                if(valAle<=4)
                                                {
                                                    valAle++;
                                                }
                                                else
                                                {
                                                    valAle = 1;
                                                }
                                            }
                                        }
                                        //  si le role est dispo
                                        if(joueurOk)
                                        {
                                            //  Ajout du personnage avec son role
                                           perso.add(new Explorateur(infos.get(counter), null)); 
                                        }
                                        break;
                                    case 2: //  idem cas 1
                                        for(Personnage p : perso)
                                        {
                                            if(p instanceof Ingenieur)
                                            {
                                                joueurOk = false;
                                                if(valAle<=4)
                                                {
                                                    valAle++;
                                                }
                                                else
                                                {
                                                    valAle = 1;
                                                }
                                            }
                                        }
                                        if(joueurOk)
                                        {
                                           perso.add(new Ingenieur(infos.get(counter), null)); 
                                        }
                                        break;
                                    case 3: //  idem cas 1
                                        for(Personnage p : perso)
                                        {
                                            if(p instanceof Navigateur)
                                            {
                                                joueurOk = false;
                                                if(valAle<=4)
                                                {
                                                    valAle++;
                                                }
                                                else
                                                {
                                                    valAle = 1;
                                                }
                                            }
                                        }
                                        if(joueurOk)
                                        {
                                           perso.add(new Navigateur(infos.get(counter), null)); 
                                        }
                                        break;
                                    case 4: //  idem cas 1
                                        for(Personnage p : perso)
                                        {
                                            if(p instanceof Pilote)
                                            {
                                                joueurOk = false;
                                                if(valAle<=4)
                                                {
                                                    valAle++;
                                                }
                                                else
                                                {
                                                    valAle = 1;
                                                }
                                            }
                                        }
                                        if(joueurOk)
                                        {
                                           perso.add(new Pilote(infos.get(counter), null)); 
                                        }
                                        break;
                                    case 5: //  idem cas 1
                                        for(Personnage p : perso)
                                        {
                                            if(p instanceof Plongeur)
                                            {
                                                joueurOk = false;
                                                if(valAle<=4)
                                                {
                                                    valAle++;
                                                }
                                                else
                                                {
                                                    valAle = 1;
                                                }
                                            }
                                        }
                                        if(joueurOk)
                                        {
                                           perso.add(new Plongeur(infos.get(counter), null)); 
                                        }
                                        break;
                                }
                            }while(joueurOk != true);   //  Tan que l'on a pas trouvé de role dispo on cherche
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
        }
    }
}
