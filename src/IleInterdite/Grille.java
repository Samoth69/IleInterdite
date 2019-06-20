/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import Cartes.CarteInondation;
import Enumerations.TypeEnumInondation;
import Enumerations.TypeEnumCouleurPion;
import Enumerations.TypeEnumTresors;
import Personnages.Personnage;
import java.util.ArrayList;
import java.util.Collections;

import java.util.concurrent.ThreadLocalRandom; //pour générer nombre aléatoire

/**
 *
 * @author violentt
 */
public class Grille {

    private final Tuile tabTuile[][] = new Tuile[6][6]; // A potentiellement changer pour mettre des cases vides --- par quoi mdr ?
    private final ArrayList<Tuile> listTuile = new ArrayList<>();
    private ArrayList<Personnage> persos = new ArrayList<>();
    private int counter = 0;
    private boolean demoMode = false;
    private boolean startup = true;
    
    public Grille(ArrayList<Personnage> p, boolean demo) {
        persos.addAll(p);
        genererTableauTuiles(demo);
        demoMode = demo;
        assignerJoueursATuile(persos);
    }

    public Tuile[][] getTuiles() {
        return tabTuile;
    }

    private void genererTableauTuiles() {
        genererTableauTuiles(false);
    }
    
    public boolean isDemoMode() {
        return demoMode;
    }
    
    public boolean isStartup() {
        return startup;
    }

    public void setStartup(boolean startup) {
        this.startup = startup;
    }

    //génère le tableau des tuiles aléatoire
    //demo: désactive le mélange des cartes
    private void genererTableauTuiles(boolean demo) {
        ArrayList<CarteInondation> tuile = getListCarteInondation();
        if (!demo) {
            for (int i = 0; i < ThreadLocalRandom.current().nextInt(2, 5); i++) {
                Collections.shuffle(tuile); //mélange la liste des tuiles
            }
        }

        counter = 0;

        for (int x = 0; x <= 5; x++) {
            addTuile(x, 2, tuile);
            addTuile(x, 3, tuile);
            if (x >= 1 && x <= 4) {
                addTuile(x, 1, tuile);
                addTuile(x, 4, tuile);
            }
            if (x == 2 || x == 3) {
                addTuile(x, 0, tuile);
                addTuile(x, 5, tuile);
            }
        }
    }

    private void addTuile(int x, int y, ArrayList<CarteInondation> tuile) {
        Tuile t = new Tuile(x, y, tuile.get(this.counter));
        tabTuile[x][y] = t;
        listTuile.add(t);
        //System.out.println(x + "\t" + y + "\t" + this.counter + "\t" + tuile.get(this.counter).getNom());

        this.counter++;
    }

    private void assignerJoueursATuile(ArrayList<Personnage> perso) {
        for (Personnage p : perso) {
            int i = 0;
            //System.out.println(listTuile.get(i).getCouleurPion());
            //System.out.println(p.getCouleurPion());
            while (p.getCouleurPion() != listTuile.get(i).getCouleurPion() && i < listTuile.size()) {
                //System.out.println(i);
                i++;
            }
            if (i != listTuile.size()) {
                p.setEmplacementJoueur(listTuile.get(i));
                listTuile.get(i).addJoueur(p);
                //System.out.println("Association");
            }

        }
    }

    public ArrayList<Tuile> getListTuile() {
        return listTuile;
    }

    //renvoie les cartes du jeu
    public ArrayList<CarteInondation> getListCarteInondation() {
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

    //Prend une tuile en parametre. Augmente directement le niveau d'innondation d'une tuile
    //sans passer par le tableau de tuile
    public void AugmenterInnondation(Tuile tuile) {
        tuile.augmenterInondation();
    }

    //Prend un nom de carte en parametre. Augmente le niveau d'inondation d'une tuile en
    //fonction du nom d'une carte innondation
    public void AugmenterInnondation(String nom) {
        /*for(int i = 0; i < 5; i++)
         {
         for(int j = 0; j < 5; j++)
         {
         if(tabTuile[i][j] != null && tabTuile[i][j].getNom().equals(nom))
         {
         tabTuile[i][j].augmenterInondation();
         return;
         }
         }
         }*/

        for (Tuile t : listTuile) {
            if (t.getNom().equals(nom)) {
                t.augmenterInondation();
                break;
            }
        }
    }

    //Prend une tuile en parametre. Reduit directement le niveau d'innondation d'une tuile
    //sans passer par le tableau de tuile
    public void ReduireInondation(Tuile tuile) {
        tuile.reduireInondation();
    }

    //Prend une tuile en parametre. renvoie un arrylist de tuile.
    //Renvoie les tuile praticable(SEC ou MOUILLE) autour de la tuile en parametre
    public ArrayList<Tuile> getTuilesAutoursPraticable(Tuile tuile) {
        ArrayList<Tuile> tuilesAutourPraticable = new ArrayList<>();
        //verif diagonale haut gauche
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 || j != 0) {
                    //System.out.println("i=" + i + "\tj=" + j);
                    if (((tuile.getX() + i) >= 0 && (tuile.getX() + i) <= 5) && ((tuile.getY() + j) >= 0 && (tuile.getY() + j) <= 5) && tabTuile[tuile.getX() + i][tuile.getY() + j] != null) {
                        //System.out.println(tuile.getX() + "\t" + i + "\t" + tuile.getY() + "\t" + j);
                        if ((tabTuile[tuile.getX() + i][tuile.getY() + j].getInondation() == TypeEnumInondation.SEC) || (tabTuile[tuile.getX() + i][tuile.getY() + j].getInondation() == TypeEnumInondation.MOUILLE)) {
                            tuilesAutourPraticable.add(tabTuile[tuile.getX() + i][tuile.getY() + j]);
                        }

                    }
                }
            }
        }
        return tuilesAutourPraticable;
    }

    //Surcharge de la fonction precedente
    public ArrayList<Tuile> getTuilesAutoursPraticable(Personnage personnage) {
        return getTuilesAutoursPraticable(personnage.getEmplacement());
    }

    //Prend une tuile en parametre. renvoie un arrylist de tuile.
    //Renvoie les tuile MOUILLE autour de la tuile en parametre.
    public ArrayList<Tuile> getTuilesAutoursMouille(Tuile tuile) {
        ArrayList<Tuile> tuilesAutourMouille = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                //System.out.println("i=" + i + "\tj=" + j);
                if (((tuile.getX() + i) >= 0 && (tuile.getX() + i) <= 5) && ((tuile.getY() + j) >= 0 && (tuile.getY() + j) <= 5) && tabTuile[tuile.getX() + i][tuile.getY() + j] != null) {
                    //System.out.println(tuile.getX() + "\t" + i + "\t" + tuile.getY() + "\t" + j);
                    if (tabTuile[tuile.getX() + i][tuile.getY() + j].getInondation() == TypeEnumInondation.MOUILLE) {
                        tuilesAutourMouille.add(tabTuile[tuile.getX() + i][tuile.getY() + j]);
                    }

                }
            }
        }
        return tuilesAutourMouille;
    }

    //Surcharge de la fonction precedente
    public ArrayList<Tuile> getTuilesAutoursMouille(Personnage personnage) {
        return getTuilesAutoursMouille(personnage.getEmplacement());
    }

    public ArrayList<Tuile> getTuilesAutours(Tuile tuile) {
        ArrayList<Tuile> tuilesAutourMouille = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                //System.out.println("i=" + i + "\tj=" + j);
                if (((tuile.getX() + i) >= 0 && (tuile.getX() + i) <= 5) && ((tuile.getY() + j) >= 0 && (tuile.getY() + j) <= 5) && tabTuile[tuile.getX() + i][tuile.getY() + j] != null) {
                    tuilesAutourMouille.add(tabTuile[tuile.getX() + i][tuile.getY() + j]);
                }
            }
        }
        return tuilesAutourMouille;
    }
}
