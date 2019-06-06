/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import Enumerations.TypeEnumTresors;
import Personnages.Personnage;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author violentt
 */
public class MainCLI implements Observateur{
    
    private ControlleurJeuSecondaire cj;
    
    MainCLI() {
        System.out.print("Entrez le nombre de joueurs(entre 2 et 4):");
        Scanner sc = new Scanner(System.in);
        int nombreJoueur = sc.nextInt();
        
        cj = new ControlleurJeuSecondaire(nombreJoueur);
        cj.addObservateur(this);
        
        ecrireCarte();
        
        //System.out.println(cj.getNomJoueur());
        
    }
    
    private void ecrireActionsPossible() {
        System.out.println("");
    }
    
    private void ecrireCarte() {
        ArrayList<Tuile> al = cj.getListeCarte();
        int maxCharCount = 0; 
        /*for (Tuile t : al) {
            if (t.getNom().length() > maxCharCount) {
                maxCharCount = t.getNom().length();
            }
        }*/
        maxCharCount+= 35;
        //System.out.println(maxCharCount);
        String[] text = new String[8];
        Tuile[][] t = cj.getGrille();
        for (int x = 0; x < 6; x++) {
            for (int i = 0; i < text.length; i++) {
                text[i] = "";
            }
            for (int y = 0; y < 6; y++) {
                
                text[0] += "+";
                
                for (int i = 0; i < maxCharCount; i++) {
                    text[0] += "-";
                }
                for (int i = 1; i < text.length; i++) {
                        text[i] += "|";
                }
                if (t[x][y] != null) {
                    text[1] += t[x][y].getNom() + "(" + t[x][y].getInondation() + ")";
                    if (t[x][y].getTresor() != TypeEnumTresors.AUCUN) {
                        text[2] += "Trésor: " + t[x][y].getTresor();
                    }
                    //text[3] += t[x][y].getCouleurPion();
                    if (!t[x][y].getPersonnages().isEmpty()) {
                        int counter = 4;
                        for (Personnage p: t[x][y].getPersonnages()) {
                            text[counter] += p.getNom();
                            counter++;
                        }
                    }
                }
                
                for (int j = 1; j < text.length; j++) {
                    int dif = text[0].length() - text[j].length();

                    for (int i = 0; i < dif; i++) {
                        text[j] += " ";
                    }
                }
            }
            text[0] += "+";
            for (int j = 1; j < text.length; j++) {
                text[j] += "|";
            }
            for (int i = 0; i < text.length; i++) {
                System.out.println(text[i]);
            }
        }
        for (int j = 0; j < 6; j++) {
            System.out.print("+");
            for (int i = 0; i < maxCharCount; i++) {
                System.out.print("-");
            }  
        }
        System.out.println("+");
    }
    
    public void traiterMessage(Message m) {
        System.out.println("msg:");
        System.out.println(m.getMessageType() + "\n" + m.getActionType() + "\n" + m.getAdditionnal());
    }
    
    public static void main(String[] args) {new MainCLI();}
}
