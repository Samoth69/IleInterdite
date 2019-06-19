/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnages;

import Cartes.CarteRouge;
import Cartes.CarteAction;
import Cartes.CarteTresor;
import Cartes.CarteInondation;
import Cartes.CarteMonteeDesEaux;
import Enumerations.TypeEnumCouleurPion;
import Enumerations.TypeEnumInondation;
import Enumerations.TypeEnumPersonnages;
import IleInterdite.Grille;
import IleInterdite.Tuile;
import java.util.ArrayList;

/**
 *
 * @author violentt
 */
public abstract class Personnage {

    //ATTRIBUTS
    //nom du joueur
    private String nom;
    //emplacement du Joueur (liée à la tuille sur laquel le personnage est)
    private Tuile emplacementJoueur;
    //grille du jeu
    public Grille ile;
    //TOUTES cartes que le joueur possède
    private ArrayList<CarteRouge> cartes = new ArrayList<>();

    /*
     // cartes trésors que le joueur possede
     private ArrayList<CarteTresor> cartesTresors = new ArrayList<>();
     // cartes action spéciales que le joueur possede
     private ArrayList<CarteAction> cartesAction = new ArrayList<>();    
     // cartes inondation que le joueur possede
     private ArrayList<CarteInondation> cartesinnondations = new ArrayList<>();    
     // carte montée des eaux que le joeur peut avoir lors du pioche
     private CarteMonteeDesEaux carteMonteeEau;
     */
    //couleur pion associé au personnage
    private TypeEnumCouleurPion pion = TypeEnumCouleurPion.AUCUN;
    private boolean pouvoirDeplacementUtilise = false; //devient vrai quand l'utilisateur à utilisé sont pouvoir de déplacement (déplacement diagonal pour explorateur, déplacement sur la carte pour le pilote) redevient faux quand le joueur en cour change

    //CONSTRUCTEUR
    Personnage(String nom, Grille ile, TypeEnumCouleurPion pion) {
        this.nom = nom;
        this.ile = ile;
        this.pion = pion;
        //System.out.println("init perso");
    }

    //METHODES
    //utiliser uniquement pour définir l'emplacement de départ du joueur.
    //utiliser la fonction deplacement pour déplacer le personnage sur la grille
    public void setEmplacementJoueur(Tuile emplacementJoueur) {
        if (this.emplacementJoueur == null) {
            this.emplacementJoueur = emplacementJoueur;
        } else {
            new Error("La fonction setEmplacementJoueur ne doit être utilisée que à l'initialisation du personnage ! \nPas pour déplacer le joueur sur la map");
        }
    }

    //récupère la couleur du pion associé à ce personnage
    public TypeEnumCouleurPion getCouleurPion() {
        return pion;
    }

    //renvoie les déplacement possible autour de la position actuel du joueur
    //peut être renvoyer une arraylist vide
    public ArrayList<Tuile> getDeplacements() {
        ArrayList<Tuile> tmp = ile.getTuilesAutoursPraticable(this); //récupère la position actuel du joueur
        ArrayList<Tuile> out = new ArrayList<>(); //liste que l'on revoie à la fin (même si elle est vide)

        int currentX = this.emplacementJoueur.getX();
        int currentY = this.emplacementJoueur.getY();

        if (tmp != null) {
            if (!tmp.isEmpty()) {
                for (Tuile t : tmp) {
                    if (t.getX() == currentX || t.getY() == currentY) { //si X ou Y dans la liste est égal à la position actuel du personnage
                        out.add(t);
                    }
                }
            }
        }
        return out;
    }

    //change l'emplacement du joueur
    public void deplacement(Tuile nouvelleTuille) {
        emplacementJoueur.removeJoueur(this);
        emplacementJoueur = nouvelleTuille;
        emplacementJoueur.addJoueur(this);
    }

    //Renvoie la tuile sur laquelle est le joueur
    public Tuile getEmplacement() {
        return emplacementJoueur;
    }

    //Renvoie la grille
    protected Grille getGrille() {
        return ile;
    }

    //setter grille
    public void setGrille(Grille g) {
        ile = g;
    }

    //renvoie les iles qui peuvent être sécher autour du joueur
    //peut être renvoyer une arraylist vide
    public ArrayList<Tuile> getTuileQuiPeutSecher() {
        ArrayList<Tuile> tmp = ile.getTuilesAutoursMouille(this); //liste contenant les tuiles qui sont mouillées autour du joueur
        ArrayList<Tuile> out = new ArrayList<>();

        int currentX = this.emplacementJoueur.getX();     //X et Y prennent la position actuel du joueur
        int currentY = this.emplacementJoueur.getY();

        if (tmp != null) {
            if (tmp.size() != 0) {
                for (Tuile t : tmp) {
                    if (t.getX() == currentX || t.getY() == currentY) {
                        out.add(t);   // ajoute a la liste out, les tuiles qu'un personnage peut assecher
                    }
                }
            }
        }
        return out;
    }

    //assecher une tuile
    public void assecher(Tuile tuileAssechable) {
        tuileAssechable.reduireInondation();
    }

    /*
     //renvoie le nombre de case qui peuvent être sécher en une action
     public int getNbTuileSechable() {
     return 1;
     }
     */
    //donne une (ou plusieur) carte(s) à un autre personnage
    public void donnerCarteAJoueur(Personnage personnage, ArrayList<CarteRouge> cartes) {
        if (personnage.getEmplacement() == this.getEmplacement()) {
            personnage.addCartes(cartes);
            this.removeCartes(cartes);
        }
    }

    //donne une carte à un autre joueur
    public void donnerCarteAJoueur(Personnage personnage, CarteRouge carte) {
        if (personnage.getEmplacement() == this.getEmplacement()) {
            personnage.addCarte(carte);
            this.removeCarte(carte);
        }
    }

    //ajoute la/les cartes à la liste des cartes de ce joueur
    public void addCartes(ArrayList<CarteRouge> cartes) {
        this.cartes.addAll(cartes);
    }

    //ajoute la carte à la liste des cartes de ce joueur
    public void addCarte(CarteRouge carte) {
        this.cartes.add(carte);
    }

    //renvoie toute les cartes que possède ce joueur
    public ArrayList<CarteRouge> getCartes() {
        return cartes;
    }

    //enlève une/des cartes de ce joueur
    public void removeCartes(ArrayList<CarteRouge> cartes) {
        this.cartes.removeAll(cartes);
    }

    //enlève une carte de ce joueur
    public void removeCarte(CarteRouge carte) {
        this.cartes.remove(carte);
    }

    // retourne le nom du personnage
    public String getNom() {
        return nom;
    }

    //indique de quel type est la classe (explorateur, ingénieur....)
    public abstract TypeEnumPersonnages getType();

    public void passageJoueurSuivant() {
        pouvoirDeplacementUtilise = false;
    }

    //Renvoie true si le personnage peut se deplacer, sinon false
    protected boolean getPouvoirDeplacementUtilise() {
        return pouvoirDeplacementUtilise;
    }

    //Permet de donner le pouvoir de deplacement au personnage
    protected void setPouvoirDeplacementUtilise(boolean v) {
        pouvoirDeplacementUtilise = v;
    }
}
