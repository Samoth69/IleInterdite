/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import Personnages.Explorateur;
import Personnages.Ingenieur;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author bekelen
 */
public class Test2 {

    /**
     * @param args the command line arguments
     */Grille grille;
    Explorateur perso1;
    Ingenieur perso2;
    ArrayList<Tuile> tuilesAssechables= new ArrayList<>();
    
    public Test2() {
        perso1 = new Explorateur("NomExplorateur1", grille);
        perso2 = new Ingenieur("NomIngenieur1", grille);
        grille = new Grille(perso1, perso2);
        
        Tuile[][] t = grille.getTuiles();
        
        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 5; j++) {
                if (t[i][j] != null) {
                    System.out.print(i + ":" + j + "\t" + t[i][j].getNom() + "\t");
                } else {
                    System.out.print(i + ":" + j + "\t NULL \t\t");
                }
                
            }
            System.out.println();
        }
        
        System.out.println("perso1:");
        Tuile tuile = perso1.getEmplacement();
        System.out.println(tuile.getNom() + "\t" + tuile.getX() + "\t" + tuile.getY());
        System.out.println("perso2:");
        tuile = perso2.getEmplacement();
        System.out.println(tuile.getNom() + "\t" + tuile.getX() + "\t" + tuile.getY());
        
        
        System.out.println("1er Tour");
        System.out.println("perso1 saisir nom tuile");
        Scanner nom= new Scanner(System.in);
        String nomTuile;
        Tuile nouvelleTuile=null;
        
        nomTuile=nom.nextLine();
        boolean TuileExists=false;
        
        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 5; j++) {
                if(t[i][j]!=null){
                if (t[i][j].getNom().contentEquals(nomTuile)) {
                 TuileExists=true;
                 nouvelleTuile=t[i][j];
            }
          }     
        }
    }      
    
        if(TuileExists==false){
            System.out.println("Nom tuile que vous avez entré n'existe pas");
        } else{
            perso1.deplacement(nouvelleTuile);
            System.out.println("Nouveau emplacement :" + perso1.getEmplacement().getNom()+" "+perso1.getEmplacement().getX()+" "+perso1.getEmplacement().getY() );
            /*System.out.print("Voulez-vous assécher une tuile ? (oui ou non)");
            String assecherChoix=nom.nextLine();
               if(assecherChoix.contentEquals("oui")){
                   System.out.println("Saisir nom tuile à assécher");
                   String assecherTuileNom=nom.nextLine(); TuileExists=false; Tuile assecherTuile=null; ;
                   
                   tuilesAssechables=perso1.getTuilleQuiPeutSecher();
                   for(int i=0; i<tuilesAssechables; i++){
                       if(tuilesAssechables[i])
                   }
                   
                   if(TuileExists==false){
                       System.out.println("Tuile asséchable saisie n'exite pas"); 
                   } else{
                       System.out.print(assecherTuile+" "+assecherTuile.getInondation());
                       perso1.assecher(assecherTuile);
                       System.out.println("Tuile assécher");
                   }
                   
               }*/
        }
    
    }
    
    public static void main(String[] args) {new Test2();}
    
}
