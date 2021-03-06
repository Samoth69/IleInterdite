/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import Cartes.CarteAction;
import Cartes.CarteInondation;
import Cartes.CarteMonteeDesEaux;
import Cartes.CarteRouge;
import Cartes.CarteTresor;
import Enumerations.TypeEnumCarteAction;
import Enumerations.TypeEnumCouleurPion;
import Enumerations.TypeEnumPersonnages;
import Enumerations.TypeEnumTresors;
import IHM.Joueurs;
import IHM.Menu;
import IHM.Plateau;
import Personnages.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Toto
 */
public class ControleurJeuPrincipal implements Observateur {

    /**
     * @param args the command line arguments
     */
    //ATTRIBUTS
    private Joueurs joueurs = new Joueurs(); //joueurs
    private Menu menu = new Menu();   //menu
    private Plateau plateau;  //plateau
    private ControleurJeuSecondaire cj;  //controleursecondaire

    public static void main(String[] args) {
        new ControleurJeuPrincipal();
    }

    //CONSTRUCTEUR
    private ControleurJeuPrincipal() {
        menu.addObservateur(this);
        joueurs.addObservateur(this);

        menu.afficher();
    }

    public void afficherMenu(boolean b) {
        menu.setVisible(b);
    }

    //traite le message
    @Override
    public void traiterMessage(Message m) {
        switch (m.getMessageType2()) {
            case MENU_JOUEURS:     // si le message est MENU_JOUEURS => affiche le fenetre joueurs
                joueurs.afficher();
                break;
            case MENU_PRINCIPAL:   //si le message est MENU_PRINCIAPL => affiche le menu
                menu.afficher();
                break;
            case MENU_JOUER:                // si les message est MENU_JOUER
                ArrayList<String> infos = m.getAdditionnal();
                ArrayList<Personnage> perso = new ArrayList<>();

                switch (infos.get(1)) {
                    case "0": //mode normal
                        int counter = 3;
                        int joueurCompter = 0;
                        int nombreJoueurRandom = 0;
                        while (joueurCompter < Integer.valueOf(infos.get(2))) {
                            switch (infos.get(counter + 1)) {
                                case "Explorateur":
                                    perso.add(new Explorateur(infos.get(counter), null));
                                    break;
                                case "Ingénieur":
                                    perso.add(new Ingenieur(infos.get(counter), null));
                                    break;
                                case "Messager":
                                    perso.add(new Messager(infos.get(counter), null));
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
                                    //ArrayList<Personnage> p = new ArrayList<>();
                                    Personnage person = null;
                                    //ArrayList<Integer> PersonnageDejaUtilise = new ArrayList<>();
                                    int rnd = 0;

                                    do {
                                        rnd = ThreadLocalRandom.current().nextInt(1, 6 + 1);
                                        switch (rnd) {
                                            case 1:
                                                person = (new Explorateur(infos.get(counter), null));
                                                break;
                                            case 2:
                                                person = (new Ingenieur(infos.get(counter), null));
                                                break;
                                            case 3:
                                                person = (new Messager(infos.get(counter), null));
                                                break;
                                            case 4:
                                                person = (new Navigateur(infos.get(counter), null));
                                                break;
                                            case 5:
                                                person = (new Pilote(infos.get(counter), null));
                                                break;
                                            case 6:
                                                person = (new Plongeur(infos.get(counter), null));
                                                break;

                                        }
                                    } while (rnd == 0 || person == null || search(perso, person.getType()));
                                    perso.add(person);
                                    break;
                                default:
                                    break;
                            }
                            joueurCompter++;
                            counter += 2;
                        }
                        cj = new ControleurJeuSecondaire(perso, Integer.parseInt(infos.get(0)), false, null, null);
                        plateau = new Plateau(perso, cj);
                        cj.addObservateur(plateau);
                        for (Personnage p : perso) {
                            p.setGrille(cj.getIle());
                        }
                        plateau.afficher();
                        break;
                    case "1": //scénario 1
                        perso.add(new Explorateur("Explo", null));
                        perso.add(new Ingenieur("Inge", null));
                        perso.add(new Pilote("Pilote", null));
                        perso.add(new Plongeur("Plongeur", null));

                        cj = new ControleurJeuSecondaire(perso, 4, true, getCarteRouge1(), getCarteInond1());
                        cj.getIle().setStartup(true);
                        plateau = new Plateau(perso, cj);
                        cj.addObservateur(plateau);
                        for (Personnage p : perso) {
                            p.setGrille(cj.getIle());
                        }

                        cj.augementerInondation("la caverne des ombres");
                        cj.augementerInondation("la caverne du brasier");
                        cj.augementerInondation("le jardin des hurlements");
                        cj.augementerInondation("le jardin des murmures");
                        cj.augementerInondation("le palais des marees");
                        cj.augementerInondation("le palais de corail");
                        cj.augementerInondation("le temple du soleil");
                        cj.augementerInondation("le temple de la lune");
                        cj.recupererTresor(cj.searchTuile("le jardin des murmures"), true);
                        cj.recupererTresor(cj.searchTuile("le palais des marees"), true);
                        cj.recupererTresor(cj.searchTuile("le temple du soleil"), true);
                        cj.deplacerJoueur(perso.get(0), cj.searchTuile("la caverne des ombres")); //explorateur
                        cj.deplacerJoueur(perso.get(1), cj.searchTuile("heliport")); //ingénieur
                        cj.deplacerJoueur(perso.get(2), cj.searchTuile("la tour du guet")); //pilote
                        cj.deplacerJoueur(perso.get(3), cj.searchTuile("le rocher fantome")); //plongeur
                        cj.getIle().setStartup(false);
                        plateau.updatePion();
                        plateau.afficher();
                        break;
                    case "2": //scénario 2
                        perso.add(new Ingenieur("Inge", null));
                        perso.add(new Pilote("Pilote", null));

                        cj = new ControleurJeuSecondaire(perso, 2, true, getCarteRouge1(), getCarteInond2());
                        cj.getIle().setStartup(true);
                        plateau = new Plateau(perso, cj);
                        cj.addObservateur(plateau);
                        for (Personnage p : perso) {
                            p.setGrille(cj.getIle());
                        }

                        cj.augementerInondation("la caverne des ombres");
                        cj.augementerInondation("la caverne du brasier");
                        //cj.augementerInondation("le jardin des hurlements");
                        cj.augementerInondation("le jardin des murmures");
                        //cj.augementerInondation("le palais des marees");
                        cj.augementerInondation("le palais de corail");
                        cj.augementerInondation("le temple du soleil");
                        cj.augementerInondation("le temple de la lune");
                        cj.deplacerJoueur(perso.get(0), cj.searchTuile("heliport"));
                        cj.deplacerJoueur(perso.get(1), cj.searchTuile("les dunes de l'illusion"));
                        cj.getIle().setStartup(false);
                        plateau.updatePion();
                        plateau.afficher();

                        break;
                    case "3": //scénario 3
                        perso.add(new Ingenieur("Inge", null));
                        perso.add(new Pilote("Pilote", null));

                        cj = new ControleurJeuSecondaire(perso, 2, true, getCarteRouge1(), getCarteInond3());
                        cj.getIle().setStartup(true);
                        plateau = new Plateau(perso, cj);
                        cj.addObservateur(plateau);
                        for (Personnage p : perso) {
                            p.setGrille(cj.getIle());
                        }

                        cj.augementerInondation("heliport");
                        cj.deplacerJoueur(perso.get(0), cj.searchTuile("la caverne des ombres"));
                        cj.deplacerJoueur(perso.get(1), cj.searchTuile("les dunes de l'illusion"));
                        cj.getIle().setStartup(false);
                        plateau.updatePion();
                        plateau.afficher();

                        break;
                    case "4": //scénario 4
                        perso.add(new Ingenieur("Inge", null));
                        perso.add(new Pilote("Pilote", null));

                        cj = new ControleurJeuSecondaire(perso, 2, true, getCarteRouge1(), getCarteInond4());
                        cj.getIle().setStartup(true);
                        plateau = new Plateau(perso, cj);
                        cj.addObservateur(plateau);
                        for (Personnage p : perso) {
                            p.setGrille(cj.getIle());
                        }

                        //cj.augementerInondation("heliport");
                        /*cj.augementerInondation("le temple du soleil");
                        cj.augementerInondation("le palais des marees");
                        cj.augementerInondation("le temple du soleil");*/
                        cj.augementerInondation("Le Jardin des Murmures");
                        cj.augementerInondation("le palais des marees");
                        cj.augementerInondation("le temple du soleil");
                        cj.augementerInondation("observatoire");
                        cj.augementerInondation("la caverne du brasier");
                        cj.augementerInondation("le marais brumeux");
                        cj.deplacerJoueur(perso.get(0), cj.searchTuile("Le marais brumeux"));
                        cj.deplacerJoueur(perso.get(1), cj.searchTuile("les dunes de l'illusion"));
                        cj.getIle().setStartup(false);
                        plateau.updatePion();
                        plateau.afficher();

                        break;
                    case "5": //scénario 5
                        perso.add(new Ingenieur("Inge", null));
                        perso.add(new Pilote("Pilote", null));

                        cj = new ControleurJeuSecondaire(perso, 2, true, getCarteRouge2(), getCarteInond4());
                        cj.getIle().setStartup(true);
                        plateau = new Plateau(perso, cj);
                        cj.addObservateur(plateau);
                        for (Personnage p : perso) {
                            p.setGrille(cj.getIle());
                        }

                        cj.augmenterNiveauEau();
                        cj.augmenterNiveauEau();
                        cj.augmenterNiveauEau();
                        cj.augmenterNiveauEau();
                        cj.augmenterNiveauEau();
                        cj.augmenterNiveauEau();

                        cj.deplacerJoueur(perso.get(0), cj.searchTuile("heliport"));
                        cj.deplacerJoueur(perso.get(1), cj.searchTuile("les dunes de l'illusion"));
                        cj.getIle().setStartup(false);
                        plateau.updatePion();
                        plateau.afficher();

                        break;
                    default:
                        break;
                }

        }
    }

    private ArrayList<CarteInondation> getCarteInond1() {
        ArrayList<CarteInondation> out = new ArrayList<>();

        final String cheminCarte = System.getProperty("user.dir") + "/src/RessourcesCarteInondations/";
        final String cheminTuile = System.getProperty("user.dir") + "/src/RessourcesTuiles/";

        out.add(new CarteInondation("Le Pont des Abimes", cheminCarte + "LePontDesAbimes.png", cheminTuile + "LePontDesAbimes.png"));
        out.add(new CarteInondation("La Porte de Bronze", TypeEnumCouleurPion.ROUGE, cheminCarte + "LaPorteDeBronze.png", cheminTuile + "LaPorteDeBronze.png"));
        out.add(new CarteInondation("La Caverne des Ombres", TypeEnumTresors.FEU, cheminCarte + "LaCaverneDesOmbres.png", cheminTuile + "LaCaverneDesOmbres.png"));
        out.add(new CarteInondation("La Porte de Fer", TypeEnumCouleurPion.VIOLET, cheminCarte + "LaPorteDeFer.png", cheminTuile + "LaPorteDeFer.png"));
        out.add(new CarteInondation("La Porte d'Or", TypeEnumCouleurPion.JAUNE, cheminCarte + "LaPorteDOr.png", cheminTuile + "LaPortedOr.png"));
        out.add(new CarteInondation("Les Falaises de l’Oubli", cheminCarte + "LesFalaisesDeLOubli.png", cheminTuile + "LesFalaisesDeLOubli.png"));
        out.add(new CarteInondation("Le Palais de Corail", TypeEnumTresors.TROPHEE, cheminCarte + "LePalaisDeCorail.png", cheminTuile + "LePalaisDeCorail.png"));
        out.add(new CarteInondation("La Porte d'Argent", TypeEnumCouleurPion.ORANGE, cheminCarte + "LaPortedArgent.png", cheminTuile + "LaPortedArgent.png"));
        out.add(new CarteInondation("Les Dunes de l'Illusion", cheminCarte + "LesDunesDeLIllusion.png", cheminTuile + "lesDunesDeLIllusion.png"));
        out.add(new CarteInondation("Heliport", TypeEnumCouleurPion.BLEU, cheminCarte + "Heliport.png", cheminTuile + "Heliport.png"));
        out.add(new CarteInondation("La Porte de Cuivre", TypeEnumCouleurPion.VERT, cheminCarte + "LaPorteDeCuivre.png", cheminTuile + "LaPorteDeCuivre.png"));
        out.add(new CarteInondation("Le Jardin des Hurlements", TypeEnumTresors.LION, cheminCarte + "LeJardinDesHurlements.png", cheminTuile + "LeJardinDesHurlements.png"));
        out.add(new CarteInondation("La Foret Pourpre", cheminCarte + "LaForetPoupre.png", cheminTuile + "LaForetPoupre.png"));
        out.add(new CarteInondation("Le Lagon Perdu", cheminCarte + "LeLagonPerdu.png", cheminTuile + "LeLagonPerdu.png"));
        out.add(new CarteInondation("Le Marais Brumeux", cheminCarte + "LeMaraisBrumeux.png", cheminTuile + "LeMaraisBrumeux.png"));
        out.add(new CarteInondation("Observatoire", cheminCarte + "Observatoire.png", cheminTuile + "Observatoire.png"));
        out.add(new CarteInondation("Le Rocher Fantome", cheminCarte + "LeRocherFantome.png", cheminTuile + "LeRocherFantome.png"));
        out.add(new CarteInondation("La Caverne du Brasier", TypeEnumTresors.FEU, cheminCarte + "CaverneDuBrasier.png", cheminTuile + "LaCaverneDuBrasier.png"));
        out.add(new CarteInondation("Le Temple du Soleil", TypeEnumTresors.LUNE, cheminCarte + "LeTempteDuSoleil.png", cheminTuile + "LeTempleDuSoleil.png"));
        out.add(new CarteInondation("Le Temple de La Lune", TypeEnumTresors.LUNE, cheminCarte + "LeTempleDeLaLune.png", cheminTuile + "LeTempleDeLaLune.png"));
        out.add(new CarteInondation("Le Palais des Marees", TypeEnumTresors.TROPHEE, cheminCarte + "LePalaisDesMarees.png", cheminTuile + "LePalaisDesMarees.png"));
        out.add(new CarteInondation("Le Val du Crepuscule", cheminCarte + "LeValDuCrepuscule.png", cheminTuile + "LeValDuCrepuscule.png"));
        out.add(new CarteInondation("La Tour du Guet", cheminCarte + "LaTourDeGuet.png", cheminTuile + "LaTourDuGuet.png"));
        out.add(new CarteInondation("Le Jardin des Murmures", TypeEnumTresors.LION, cheminCarte + "LeJardinDesMurmures.png", cheminTuile + "LeJardinDesMurmures.png"));

        return out;
    }

    private ArrayList<CarteRouge> getCarteRouge1() {
        ArrayList<CarteRouge> out = new ArrayList<>();

        final String chemin = System.getProperty("user.dir") + "/src/RessourcesCarteTresor/";

        out.add(new CarteMonteeDesEaux("CarteMonteeDesEaux1", chemin + "MonteeDesEaux.png"));
        out.add(new CarteMonteeDesEaux("CarteMonteeDesEaux2", chemin + "MonteeDesEaux.png"));
        out.add(new CarteMonteeDesEaux("CarteMonteeDesEaux3", chemin + "MonteeDesEaux.png"));
        out.add(new CarteAction("Helicoptere", TypeEnumCarteAction.HELICOPTERE, chemin + "Helicoptere.png"));
        out.add(new CarteTresor("Trophee", TypeEnumTresors.TROPHEE, chemin + "Calice.png"));
        out.add(new CarteAction("Helicoptere", TypeEnumCarteAction.HELICOPTERE, chemin + "Helicoptere.png"));
        out.add(new CarteAction("sac", TypeEnumCarteAction.SAC_DE_SABLE, chemin + "SacsDeSable.png"));
        out.add(new CarteAction("sac", TypeEnumCarteAction.SAC_DE_SABLE, chemin + "SacsDeSable.png"));
        out.add(new CarteTresor("Lion", TypeEnumTresors.LION, chemin + "Zephyr.png"));
        out.add(new CarteTresor("Lion", TypeEnumTresors.LION, chemin + "Zephyr.png"));
        out.add(new CarteTresor("Lion", TypeEnumTresors.LION, chemin + "Zephyr.png"));
        out.add(new CarteTresor("Lion", TypeEnumTresors.LION, chemin + "Zephyr.png"));
        out.add(new CarteTresor("Lion", TypeEnumTresors.LION, chemin + "Zephyr.png"));
        out.add(new CarteTresor("Lune", TypeEnumTresors.LUNE, chemin + "Pierre.png"));
        out.add(new CarteTresor("Lune", TypeEnumTresors.LUNE, chemin + "Pierre.png"));
        out.add(new CarteTresor("Lune", TypeEnumTresors.LUNE, chemin + "Pierre.png"));
        out.add(new CarteTresor("Lune", TypeEnumTresors.LUNE, chemin + "Pierre.png"));
        out.add(new CarteTresor("Lune", TypeEnumTresors.LUNE, chemin + "Pierre.png"));
        out.add(new CarteTresor("Feu", TypeEnumTresors.FEU, chemin + "Cristal.png"));
        out.add(new CarteTresor("Feu", TypeEnumTresors.FEU, chemin + "Cristal.png"));
        out.add(new CarteTresor("Feu", TypeEnumTresors.FEU, chemin + "Cristal.png"));
        out.add(new CarteTresor("Trophee", TypeEnumTresors.TROPHEE, chemin + "Calice.png"));
        out.add(new CarteTresor("Trophee", TypeEnumTresors.TROPHEE, chemin + "Calice.png"));
        out.add(new CarteTresor("Feu", TypeEnumTresors.FEU, chemin + "Cristal.png"));
        out.add(new CarteTresor("Trophee", TypeEnumTresors.TROPHEE, chemin + "Calice.png"));
        out.add(new CarteAction("Helicoptere", TypeEnumCarteAction.HELICOPTERE, chemin + "Helicoptere.png"));
        out.add(new CarteTresor("Trophee", TypeEnumTresors.TROPHEE, chemin + "Calice.png"));
        out.add(new CarteTresor("Feu", TypeEnumTresors.FEU, chemin + "Cristal.png"));

        return out;
    }

    private ArrayList<CarteInondation> getCarteInond2() {
        ArrayList<CarteInondation> out = new ArrayList<>();

        final String cheminCarte = System.getProperty("user.dir") + "/src/RessourcesCarteInondations/";
        final String cheminTuile = System.getProperty("user.dir") + "/src/RessourcesTuiles/";

        out.add(new CarteInondation("Le Pont des Abimes", cheminCarte + "LePontDesAbimes.png", cheminTuile + "LePontDesAbimes.png"));
        out.add(new CarteInondation("La Porte de Bronze", TypeEnumCouleurPion.ROUGE, cheminCarte + "LaPorteDeBronze.png", cheminTuile + "LaPorteDeBronze.png"));
        out.add(new CarteInondation("La Porte de Fer", TypeEnumCouleurPion.VIOLET, cheminCarte + "LaPorteDeFer.png", cheminTuile + "LaPorteDeFer.png"));
        out.add(new CarteInondation("La Porte d'Or", TypeEnumCouleurPion.JAUNE, cheminCarte + "LaPorteDOr.png", cheminTuile + "LaPortedOr.png"));
        out.add(new CarteInondation("Les Falaises de l’Oubli", cheminCarte + "LesFalaisesDeLOubli.png", cheminTuile + "LesFalaisesDeLOubli.png"));
        out.add(new CarteInondation("La Porte d'Argent", TypeEnumCouleurPion.ORANGE, cheminCarte + "LaPortedArgent.png", cheminTuile + "LaPortedArgent.png"));
        out.add(new CarteInondation("Les Dunes de l'Illusion", cheminCarte + "LesDunesDeLIllusion.png", cheminTuile + "lesDunesDeLIllusion.png"));
        out.add(new CarteInondation("Heliport", TypeEnumCouleurPion.BLEU, cheminCarte + "Heliport.png", cheminTuile + "Heliport.png"));
        out.add(new CarteInondation("La Porte de Cuivre", TypeEnumCouleurPion.VERT, cheminCarte + "LaPorteDeCuivre.png", cheminTuile + "LaPorteDeCuivre.png"));
        out.add(new CarteInondation("Le Val du Crepuscule", cheminCarte + "LeValDuCrepuscule.png", cheminTuile + "LeValDuCrepuscule.png"));
        out.add(new CarteInondation("La Tour du Guet", cheminCarte + "LaTourDeGuet.png", cheminTuile + "LaTourDuGuet.png"));
        out.add(new CarteInondation("La Foret Pourpre", cheminCarte + "LaForetPoupre.png", cheminTuile + "LaForetPoupre.png"));
        out.add(new CarteInondation("Le Lagon Perdu", cheminCarte + "LeLagonPerdu.png", cheminTuile + "LeLagonPerdu.png"));
        out.add(new CarteInondation("Le Marais Brumeux", cheminCarte + "LeMaraisBrumeux.png", cheminTuile + "LeMaraisBrumeux.png"));
        out.add(new CarteInondation("Observatoire", cheminCarte + "Observatoire.png", cheminTuile + "Observatoire.png"));
        out.add(new CarteInondation("Le Rocher Fantome", cheminCarte + "LeRocherFantome.png", cheminTuile + "LeRocherFantome.png"));
        out.add(new CarteInondation("La Caverne du Brasier", TypeEnumTresors.FEU, cheminCarte + "CaverneDuBrasier.png", cheminTuile + "LaCaverneDuBrasier.png"));
        out.add(new CarteInondation("Le Temple du Soleil", TypeEnumTresors.LUNE, cheminCarte + "LeTempteDuSoleil.png", cheminTuile + "LeTempleDuSoleil.png"));
        out.add(new CarteInondation("Le Temple de La Lune", TypeEnumTresors.LUNE, cheminCarte + "LeTempleDeLaLune.png", cheminTuile + "LeTempleDeLaLune.png"));
        out.add(new CarteInondation("Le Palais des Marees", TypeEnumTresors.TROPHEE, cheminCarte + "LePalaisDesMarees.png", cheminTuile + "LePalaisDesMarees.png"));
        out.add(new CarteInondation("Le Jardin des Murmures", TypeEnumTresors.LION, cheminCarte + "LeJardinDesMurmures.png", cheminTuile + "LeJardinDesMurmures.png"));
        out.add(new CarteInondation("La Caverne des Ombres", TypeEnumTresors.FEU, cheminCarte + "LaCaverneDesOmbres.png", cheminTuile + "LaCaverneDesOmbres.png"));
        out.add(new CarteInondation("Le Palais de Corail", TypeEnumTresors.TROPHEE, cheminCarte + "LePalaisDeCorail.png", cheminTuile + "LePalaisDeCorail.png"));
        out.add(new CarteInondation("Le Jardin des Hurlements", TypeEnumTresors.LION, cheminCarte + "LeJardinDesHurlements.png", cheminTuile + "LeJardinDesHurlements.png"));

        return out;
    }

    private ArrayList<CarteInondation> getCarteInond3() {
        ArrayList<CarteInondation> out = new ArrayList<>();

        final String cheminCarte = System.getProperty("user.dir") + "/src/RessourcesCarteInondations/";
        final String cheminTuile = System.getProperty("user.dir") + "/src/RessourcesTuiles/";

        out.add(new CarteInondation("Le Pont des Abimes", cheminCarte + "LePontDesAbimes.png", cheminTuile + "LePontDesAbimes.png"));
        out.add(new CarteInondation("La Porte de Bronze", TypeEnumCouleurPion.ROUGE, cheminCarte + "LaPorteDeBronze.png", cheminTuile + "LaPorteDeBronze.png"));
        out.add(new CarteInondation("La Porte de Fer", TypeEnumCouleurPion.VIOLET, cheminCarte + "LaPorteDeFer.png", cheminTuile + "LaPorteDeFer.png"));
        out.add(new CarteInondation("La Porte d'Or", TypeEnumCouleurPion.JAUNE, cheminCarte + "LaPorteDOr.png", cheminTuile + "LaPortedOr.png"));
        out.add(new CarteInondation("Les Falaises de l’Oubli", cheminCarte + "LesFalaisesDeLOubli.png", cheminTuile + "LesFalaisesDeLOubli.png"));
        out.add(new CarteInondation("La Porte d'Argent", TypeEnumCouleurPion.ORANGE, cheminCarte + "LaPortedArgent.png", cheminTuile + "LaPortedArgent.png"));
        out.add(new CarteInondation("Les Dunes de l'Illusion", cheminCarte + "LesDunesDeLIllusion.png", cheminTuile + "lesDunesDeLIllusion.png"));
        out.add(new CarteInondation("La Porte de Cuivre", TypeEnumCouleurPion.VERT, cheminCarte + "LaPorteDeCuivre.png", cheminTuile + "LaPorteDeCuivre.png"));
        out.add(new CarteInondation("Le Val du Crepuscule", cheminCarte + "LeValDuCrepuscule.png", cheminTuile + "LeValDuCrepuscule.png"));
        out.add(new CarteInondation("La Tour du Guet", cheminCarte + "LaTourDeGuet.png", cheminTuile + "LaTourDuGuet.png"));
        out.add(new CarteInondation("La Foret Pourpre", cheminCarte + "LaForetPoupre.png", cheminTuile + "LaForetPoupre.png"));
        out.add(new CarteInondation("Le Lagon Perdu", cheminCarte + "LeLagonPerdu.png", cheminTuile + "LeLagonPerdu.png"));
        out.add(new CarteInondation("Le Marais Brumeux", cheminCarte + "LeMaraisBrumeux.png", cheminTuile + "LeMaraisBrumeux.png"));
        out.add(new CarteInondation("Observatoire", cheminCarte + "Observatoire.png", cheminTuile + "Observatoire.png"));
        out.add(new CarteInondation("Le Rocher Fantome", cheminCarte + "LeRocherFantome.png", cheminTuile + "LeRocherFantome.png"));
        out.add(new CarteInondation("La Caverne du Brasier", TypeEnumTresors.FEU, cheminCarte + "CaverneDuBrasier.png", cheminTuile + "LaCaverneDuBrasier.png"));
        out.add(new CarteInondation("Le Temple du Soleil", TypeEnumTresors.LUNE, cheminCarte + "LeTempteDuSoleil.png", cheminTuile + "LeTempleDuSoleil.png"));
        out.add(new CarteInondation("Heliport", TypeEnumCouleurPion.BLEU, cheminCarte + "Heliport.png", cheminTuile + "Heliport.png"));
        out.add(new CarteInondation("Le Temple de La Lune", TypeEnumTresors.LUNE, cheminCarte + "LeTempleDeLaLune.png", cheminTuile + "LeTempleDeLaLune.png"));
        out.add(new CarteInondation("Le Palais des Marees", TypeEnumTresors.TROPHEE, cheminCarte + "LePalaisDesMarees.png", cheminTuile + "LePalaisDesMarees.png"));
        out.add(new CarteInondation("Le Jardin des Murmures", TypeEnumTresors.LION, cheminCarte + "LeJardinDesMurmures.png", cheminTuile + "LeJardinDesMurmures.png"));
        out.add(new CarteInondation("La Caverne des Ombres", TypeEnumTresors.FEU, cheminCarte + "LaCaverneDesOmbres.png", cheminTuile + "LaCaverneDesOmbres.png"));
        out.add(new CarteInondation("Le Palais de Corail", TypeEnumTresors.TROPHEE, cheminCarte + "LePalaisDeCorail.png", cheminTuile + "LePalaisDeCorail.png"));
        out.add(new CarteInondation("Le Jardin des Hurlements", TypeEnumTresors.LION, cheminCarte + "LeJardinDesHurlements.png", cheminTuile + "LeJardinDesHurlements.png"));

        return out;
    }

    private ArrayList<CarteInondation> getCarteInond4() {
        ArrayList<CarteInondation> out = new ArrayList<>();

        final String cheminCarte = System.getProperty("user.dir") + "/src/RessourcesCarteInondations/";
        final String cheminTuile = System.getProperty("user.dir") + "/src/RessourcesTuiles/";

        out.add(new CarteInondation("Le Pont des Abimes", cheminCarte + "LePontDesAbimes.png", cheminTuile + "LePontDesAbimes.png"));
        out.add(new CarteInondation("La Porte de Bronze", TypeEnumCouleurPion.ROUGE, cheminCarte + "LaPorteDeBronze.png", cheminTuile + "LaPorteDeBronze.png"));
        out.add(new CarteInondation("La Porte de Fer", TypeEnumCouleurPion.VIOLET, cheminCarte + "LaPorteDeFer.png", cheminTuile + "LaPorteDeFer.png"));
        out.add(new CarteInondation("La Porte d'Or", TypeEnumCouleurPion.JAUNE, cheminCarte + "LaPorteDOr.png", cheminTuile + "LaPortedOr.png"));
        out.add(new CarteInondation("Les Falaises de l’Oubli", cheminCarte + "LesFalaisesDeLOubli.png", cheminTuile + "LesFalaisesDeLOubli.png"));
        out.add(new CarteInondation("La Porte d'Argent", TypeEnumCouleurPion.ORANGE, cheminCarte + "LaPortedArgent.png", cheminTuile + "LaPortedArgent.png"));
        out.add(new CarteInondation("Les Dunes de l'Illusion", cheminCarte + "LesDunesDeLIllusion.png", cheminTuile + "lesDunesDeLIllusion.png"));
        out.add(new CarteInondation("Heliport", TypeEnumCouleurPion.BLEU, cheminCarte + "Heliport.png", cheminTuile + "Heliport.png"));
        out.add(new CarteInondation("La Porte de Cuivre", TypeEnumCouleurPion.VERT, cheminCarte + "LaPorteDeCuivre.png", cheminTuile + "LaPorteDeCuivre.png"));
        out.add(new CarteInondation("La Caverne des Ombres", TypeEnumTresors.FEU, cheminCarte + "LaCaverneDesOmbres.png", cheminTuile + "LaCaverneDesOmbres.png"));
        out.add(new CarteInondation("Le Palais de Corail", TypeEnumTresors.TROPHEE, cheminCarte + "LePalaisDeCorail.png", cheminTuile + "LePalaisDeCorail.png"));
        out.add(new CarteInondation("Le Jardin des Hurlements", TypeEnumTresors.LION, cheminCarte + "LeJardinDesHurlements.png", cheminTuile + "LeJardinDesHurlements.png"));
        out.add(new CarteInondation("Le Val du Crepuscule", cheminCarte + "LeValDuCrepuscule.png", cheminTuile + "LeValDuCrepuscule.png"));
        out.add(new CarteInondation("La Tour du Guet", cheminCarte + "LaTourDeGuet.png", cheminTuile + "LaTourDuGuet.png"));
        out.add(new CarteInondation("Le Temple de La Lune", TypeEnumTresors.LUNE, cheminCarte + "LeTempleDeLaLune.png", cheminTuile + "LeTempleDeLaLune.png"));
        out.add(new CarteInondation("La Foret Pourpre", cheminCarte + "LaForetPoupre.png", cheminTuile + "LaForetPoupre.png"));
        out.add(new CarteInondation("Le Lagon Perdu", cheminCarte + "LeLagonPerdu.png", cheminTuile + "LeLagonPerdu.png"));
        out.add(new CarteInondation("Le Jardin des Murmures", TypeEnumTresors.LION, cheminCarte + "LeJardinDesMurmures.png", cheminTuile + "LeJardinDesMurmures.png"));
        out.add(new CarteInondation("La Caverne du Brasier", TypeEnumTresors.FEU, cheminCarte + "CaverneDuBrasier.png", cheminTuile + "LaCaverneDuBrasier.png"));
        out.add(new CarteInondation("Le Palais des Marees", TypeEnumTresors.TROPHEE, cheminCarte + "LePalaisDesMarees.png", cheminTuile + "LePalaisDesMarees.png"));
        out.add(new CarteInondation("Le Rocher Fantome", cheminCarte + "LeRocherFantome.png", cheminTuile + "LeRocherFantome.png"));
        out.add(new CarteInondation("Observatoire", cheminCarte + "Observatoire.png", cheminTuile + "Observatoire.png"));
        out.add(new CarteInondation("Le Marais Brumeux", cheminCarte + "LeMaraisBrumeux.png", cheminTuile + "LeMaraisBrumeux.png"));
        //out.add(new CarteInondation("La Caverne du Brasier", TypeEnumTresors.FEU, cheminCarte + "CaverneDuBrasier.png", cheminTuile + "LaCaverneDuBrasier.png"));
        out.add(new CarteInondation("Le Temple du Soleil", TypeEnumTresors.LUNE, cheminCarte + "LeTempteDuSoleil.png", cheminTuile + "LeTempleDuSoleil.png"));

        return out;
    }

    private ArrayList<CarteRouge> getCarteRouge2() {
        ArrayList<CarteRouge> out = new ArrayList<>();

        final String chemin = System.getProperty("user.dir") + "/src/RessourcesCarteTresor/";

        out.add(new CarteAction("Helicoptere", TypeEnumCarteAction.HELICOPTERE, chemin + "Helicoptere.png"));
        out.add(new CarteTresor("Trophee", TypeEnumTresors.TROPHEE, chemin + "Calice.png"));
        out.add(new CarteAction("Helicoptere", TypeEnumCarteAction.HELICOPTERE, chemin + "Helicoptere.png"));
        out.add(new CarteAction("sac", TypeEnumCarteAction.SAC_DE_SABLE, chemin + "SacsDeSable.png"));
        out.add(new CarteAction("sac", TypeEnumCarteAction.SAC_DE_SABLE, chemin + "SacsDeSable.png"));
        out.add(new CarteTresor("Lion", TypeEnumTresors.LION, chemin + "Zephyr.png"));
        out.add(new CarteTresor("Lion", TypeEnumTresors.LION, chemin + "Zephyr.png"));
        out.add(new CarteTresor("Lion", TypeEnumTresors.LION, chemin + "Zephyr.png"));
        out.add(new CarteTresor("Lion", TypeEnumTresors.LION, chemin + "Zephyr.png"));
        out.add(new CarteTresor("Lion", TypeEnumTresors.LION, chemin + "Zephyr.png"));
        out.add(new CarteTresor("Lune", TypeEnumTresors.LUNE, chemin + "Pierre.png"));
        out.add(new CarteTresor("Lune", TypeEnumTresors.LUNE, chemin + "Pierre.png"));
        out.add(new CarteTresor("Lune", TypeEnumTresors.LUNE, chemin + "Pierre.png"));
        out.add(new CarteTresor("Lune", TypeEnumTresors.LUNE, chemin + "Pierre.png"));
        out.add(new CarteTresor("Lune", TypeEnumTresors.LUNE, chemin + "Pierre.png"));
        out.add(new CarteTresor("Feu", TypeEnumTresors.FEU, chemin + "Cristal.png"));
        out.add(new CarteTresor("Feu", TypeEnumTresors.FEU, chemin + "Cristal.png"));
        out.add(new CarteTresor("Feu", TypeEnumTresors.FEU, chemin + "Cristal.png"));
        out.add(new CarteTresor("Trophee", TypeEnumTresors.TROPHEE, chemin + "Calice.png"));
        out.add(new CarteTresor("Trophee", TypeEnumTresors.TROPHEE, chemin + "Calice.png"));
        out.add(new CarteMonteeDesEaux("CarteMonteeDesEaux1", chemin + "MonteeDesEaux.png"));
        out.add(new CarteMonteeDesEaux("CarteMonteeDesEaux2", chemin + "MonteeDesEaux.png"));
        out.add(new CarteMonteeDesEaux("CarteMonteeDesEaux3", chemin + "MonteeDesEaux.png"));
        out.add(new CarteTresor("Feu", TypeEnumTresors.FEU, chemin + "Cristal.png"));
        out.add(new CarteTresor("Trophee", TypeEnumTresors.TROPHEE, chemin + "Calice.png"));
        out.add(new CarteAction("Helicoptere", TypeEnumCarteAction.HELICOPTERE, chemin + "Helicoptere.png"));
        out.add(new CarteTresor("Trophee", TypeEnumTresors.TROPHEE, chemin + "Calice.png"));
        out.add(new CarteTresor("Feu", TypeEnumTresors.FEU, chemin + "Cristal.png"));

        return out;
    }

    //cherche dans un arraylist si num est trouvé, renvoie true. sinon renvoie faux.
    private boolean search(ArrayList<Personnage> p, TypeEnumPersonnages tep) {
        for (Personnage pe : p) {
            if (pe.getType() == tep) {
                return true;
            }
        }
        return false;
    }
}
