/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;
import Personnages.Personnage;
import java.util.ArrayList;
/**
 *
 * @author violentt
 */
public class Tuille {
    
    private int X;
    private int Y;
    private Inondation EtatInondation;
    private ArrayList<Personnage> persoSurLaTuille = new ArrayList<Personnage>();
    
    Tuille(int X, int Y){
        
    }
    
    public int getX(){
        return X;
    }
    
    public void setX(int x){
        if(x < 0)
        {
            x = 0;
        }
    }
    
    public int getY(){
        return Y;
    }
    
    public Inondation getInondation(){
        return EtatInondation;
    }
    
    public void augmenterInondation(){
        
    }
    
    public void reduireInondation(){
        
    }
    
    private void ileInondee(){      //Demander a thomas pour plus de details
        
    }
    
    public Grille getGrille(){          //A completer
        return null;
    }
    
    public void addJoueur(Personnage perso){
        
    }
    
    public void removeJoueur(Personnage perso){
        
    }
    
    public ArrayList<Personnage> getPersonnages(){
        return persoSurLaTuille;
    }
}
