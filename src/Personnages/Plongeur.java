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
public class Plongeur extends Personnage{
    
    public Plongeur(String nom, Grille ile) {
        super(nom, ile, TypeEnumCouleurPion.VIOLET);
    }

    private ArrayList<Tuile> parcour = new ArrayList<>();

    @Override
    public ArrayList<Tuile> getDeplacements() {
        Tuile tabTuile[][] = ile.getTuiles();
        ArrayList<Tuile> tuilesAutourPraticable = new ArrayList<>();
        Tuile tuileActuel = getEmplacement();
        ArrayList<Tuile> tuileATraiter = new ArrayList<>();
        //verif diagonale haut gauche
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 || j != 0) {
                    //System.out.println("i=" + i + "\tj=" + j);
                    if(((tuileActuel.getX()+i) >= 0 && (tuileActuel.getX() + i) <= 5) && ((tuileActuel.getY() + j) >= 0 && (tuileActuel.getY() + j) <= 5) && tabTuile[tuileActuel.getX()+i][tuileActuel.getY()+j] != null)
                    {
                        //System.out.println(tuile.getX() + "\t" + i + "\t" + tuile.getY() + "\t" + j);
                       // if((tabTuile[this.getEmplacement().getX()+i][this.getEmplacement().getY()+j].getInondation() == TypeEnumInondation.SEC) || (tabTuile[this.getEmplacement().getX()+i][this.getEmplacement().getY()+j].getInondation() == TypeEnumInondation.MOUILLE)) {
                            tuilesAutourPraticable.add(tabTuile[tuileActuel.getX()+i][tuileActuel.getY()+j]);
                            
                            if(tabTuile[tuileActuel.getX()+i][tuileActuel.getY()+j].getInondation() == tuileActuel.getInondation() && tuileActuel.getInondation() != TypeEnumInondation.SEC)
                            {
                                tuileATraiter.add(tabTuile[tuileActuel.getX()+i][tuileActuel.getY()+j]);
                            }

                    }
                }
            } 
        }
        
        while(!tuileATraiter.isEmpty()) //is not empty
        {
            int k = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (i != 0 || j != 0) {
                            //System.out.println("i=" + i + "\tj=" + j);
                            //if(((tuileATraiter.get(k).getX()+i) >= 0 && (tuileATraiter.get(k).getX() + i) <= 5) && ((tuileATraiter.get(k).getY() + j) >= 0 && (tuileATraiter.get(k).getY() + j) <= 5) && tabTuile[tuileATraiter.get(k).getX()+i][tuileATraiter.get(k).getY()+j] != null)
                            //{
                                //System.out.println(tuile.getX() + "\t" + i + "\t" + tuile.getY() + "\t" + j);
                                if(tabTuile[tuileATraiter.get(k).getX()+i][tuileATraiter.get(k).getY()+j].getInondation() == tuileATraiter.get(k).getInondation()) {
                                    tuilesAutourPraticable.add(tabTuile[tuileATraiter.get(k).getX()+i][tuileATraiter.get(k).getY()+j]);
                                }
                                if(tabTuile[tuileATraiter.get(k).getX()+i][tuileATraiter.get(k).getY()+j].getInondation() == tuileATraiter.get(k).getInondation() && tuileATraiter.get(k).getInondation() != TypeEnumInondation.SEC)
                                {
                                    tuileATraiter.remove(tuileATraiter.size()-k);
                                    tuileATraiter.add(tabTuile[tuileATraiter.get(k).getX()+i][tuileATraiter.get(k).getY()+j]);
                                }

                            //}
                        }
                        k++;
                    }
                }
            k = 0;
        }    
        
        return tuilesAutourPraticable;
    }

    private void drawChemin(Tuile pos) {
        for (Tuile t : getGrille().getTuilesAutours(pos)) {
            System.out.println("for: " + t.getNom());
            if (t.getX() == pos.getX() || t.getY() == pos.getY()) {
                if (!parcour.contains(pos)) {
                    if (t.getInondation() == TypeEnumInondation.SEC) {
                        parcour.add(pos);
                    } else {
                        parcour.add(pos);
                        drawChemin(t);
                    }
                }
            }
        }
    }
    
    @Override
    public TypeEnumPersonnages getType() {
        return TypeEnumPersonnages.PLONGEUR;
    }
}