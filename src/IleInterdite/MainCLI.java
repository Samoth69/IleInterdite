/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import java.util.Scanner;

/**
 *
 * @author violentt
 */
public class MainCLI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.print("Entrez le nombre de joueurs(entre 2 et 4):");
        Scanner sc = new Scanner(System.in);
        int nombreJoueur = sc.nextInt();
        
        ControlleurJeu cj = new ControlleurJeu(nombreJoueur);
    }
    
}
