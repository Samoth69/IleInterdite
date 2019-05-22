/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import Cartes.CarteInondation;
import Enumerations.TypeEnumCouleurPion;
import Personnages.Explorateur;
import Personnages.Personnage;
import java.util.ArrayList;

/**
 *
 * @author Thomas
 */
public class Tests {

    /**
     * @param args the command line arguments
     */
    
    //permet de tester les différentes fonctions du programme
    //A executer puis ce laissez guider
    ArrayList<Personnage> perso = new ArrayList<>();
    Grille grille;
    
    public Tests() {
        
    }
    
    public ArrayList<CarteInondation> getListTuiles() {
        ArrayList<CarteInondation> out = new ArrayList<>();
        
        out.add(new CarteInondation("Le Pont des Abimes"));
        out.add(new CarteInondation("La Porte de Bronze", TypeEnumCouleurPion.ROUGE));
        out.add(new CarteInondation("La Caverne des Ombres"));
        out.add(new CarteInondation("La Porte de Fer", TypeEnumCouleurPion.VIOLET));
        out.add(new CarteInondation("La Porte d'Or", TypeEnumCouleurPion.JAUNE));
        out.add(new CarteInondation("Les Falaises de l’Oubli"));
        out.add(new CarteInondation("Le Palais de Corail"));
        out.add(new CarteInondation("La Porte d'Argent", TypeEnumCouleurPion.ORANGE));
        out.add(new CarteInondation("Les Dunes de l'Illusion"));
        out.add(new CarteInondation("Heliport", TypeEnumCouleurPion.BLEU));
        out.add(new CarteInondation("La Porte de Cuivre", TypeEnumCouleurPion.VERT));
        out.add(new CarteInondation("Le Jardin des Hurlements"));
        out.add(new CarteInondation("La Foret Pourpre"));
        out.add(new CarteInondation("Le Lagon Perdu"));
        out.add(new CarteInondation("Le Marais Brumeux"));
        out.add(new CarteInondation("Observatoire"));
        out.add(new CarteInondation("Le Rocher Fantome"));
        out.add(new CarteInondation("La Caverne du Brasier"));
        out.add(new CarteInondation("Le Temple du Soleil"));
        out.add(new CarteInondation("Le Temple de La Lune"));
        out.add(new CarteInondation("Le Palais des Marees"));
        out.add(new CarteInondation("Le Val du Crepuscule"));
        out.add(new CarteInondation("La Tour du Guet"));
        out.add(new CarteInondation("Le Jardin des Murmures"));
        
        return out;
    }
    
    public static void main(String[] args) {new Tests();}
}
