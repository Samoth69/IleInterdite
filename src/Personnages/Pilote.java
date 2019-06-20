/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnages;

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
public class Pilote extends Personnage {

    //CONSTRUCTEUR
    public Pilote(String nom, Grille ile) {
        super(nom, ile, TypeEnumCouleurPion.BLEU);
    }

    //METHODES
    @Override
    public ArrayList<Tuile> getDeplacements() {
        Tuile tb[][] = super.getGrille().getTuiles();
        ArrayList<Tuile> out = new ArrayList<>(); //ce qu on retourne
        if (getPouvoirDeplacementUtilise()) {   //Verifie si le pouvoir du pilote à été utilisé
            return super.getDeplacements();  // deplacement classique
        } else { //deplacement avec pouvoir
            for (int i = 0; i <= 5; i++) {
                for (int j = 0; j <= 5; j++) {
                    if ((i != super.getEmplacement().getX() || j != super.getEmplacement().getY()) && tb[i][j] != null && tb[i][j].getInondation() != TypeEnumInondation.INONDE) {
                        out.add(tb[i][j]);             //ajoute a la liste out, toute les tuiles pratiquable.
                    }
                }
            }
            return out;
        }
    }

    //Deplace le pilote
    @Override
    public void deplacement(Tuile nouvelleTuille) {
        boolean found = false;
        for (Tuile t : super.getGrille().getTuilesAutoursPraticable(nouvelleTuille)) { //pour tte les tuiles praticable
            if (t.getPersonnages().contains(this)) {
                found = true;           //deplacement classique utilisé, found true empeche l'autre type de deplacement
            }
        }
        if (!found) {   //si found false, deplacement avec pouvoir utilisé
            setPouvoirDeplacementUtilise(true);  //pouvoir true => il ne peut plus l utiliser ce tour ci
        }
        super.deplacement(nouvelleTuille); //deplace
    }

    //retourne la type
    @Override
    public TypeEnumPersonnages getType() {
        return TypeEnumPersonnages.PILOTE; //retourne type pilote
    }
}
