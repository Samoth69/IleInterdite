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
public class Tuile {
    
    private int X;
    private int Y;
    private TypeEnum EtatInondation = TypeEnum.SEC;
    private ArrayList<Personnage> persoSurLaTuile = new ArrayList<Personnage>();
    
    Tuile(int X, int Y){
        setX(X);
        setY(Y);
    }
    
    public int getX(){
        return X;
    }
    
    public void setX(int x){
        if(x < 0)
        {
            this.X = 0;
        }
        else
        {
            this.X = x;
        }
    }
    
    public int getY(){
        return Y;
    }
    
    public void setY(int y){
        if(y < 0)
        {
            this.Y = 0;
        }
        else
        {
            this.Y = y;
        }
    }
    
    public TypeEnum getInondation(){        // Renvoie etat de la tuile (SEC, MOPUILLE ou INONDE)
        return EtatInondation;
    }
    
    public void augmenterInondation(){  // Augmente etat de l'inondation de la tuile en fonction de son etat actuel
        switch(EtatInondation)                                      
        {
            
            //Si SEC etat devient MOUILLE
            case SEC:
                EtatInondation = TypeEnum.MOUILLE;
            break;
            
            //Si MOUILLE etat devient INONDE
            case MOUILLE:
                EtatInondation = TypeEnum.INONDE;
            break;
            
            //Si INONDE affiche message pour dire que l'etat de la tuile ne peut etre changé
            case INONDE:
                System.out.println("Niveau d'eau déjà au max!");
            break;
            
            //Si aucun des cas ne correspondent affiche warning (modifiable à votre guise)
            default:
                System.out.println("WARNING : tuile non inondable!");
            break;
        }
    }
    
    public void reduireInondation(){    // Reduit etat de l'inondation de la tuile en fonction de son etat actuel
        switch(EtatInondation)
        {
            
            //Si SEC affiche message pour dire que l'etat de la tuile ne peut etre changé
            case SEC:
                System.out.println("Tuile déjà séche");
            break;
            
            //Si MOUILLE etat devient SEC
            case MOUILLE:
                EtatInondation = TypeEnum.SEC;
            break;
            
            //Si INONDE affiche message pour dire que l'etat de la tuile ne peut etre changé
            case INONDE:
                System.out.println("Tuile inondée, impossible de la sécher");
            break;
            
            //Si aucun des cas ne correspondent affiche warning (modifiable à votre guise)
            default:
                System.out.println("WARNING : tuile non séchable!");
            break;
        }
    }
    
    private void ileInondee(){      //Demander a thomas pour plus de details
        
    }
    
    public void addJoueur(Personnage perso){
        persoSurLaTuile.add(perso);
    }
    
    public void removeJoueur(Personnage perso){
        for(int i = 0; i < persoSurLaTuile.size(); i++)
        {
            if(persoSurLaTuile.get(i).getNom() == perso.getNom())
            {
                persoSurLaTuile.remove(i);
            }
        }
    }
    
    public ArrayList<Personnage> getPersonnages(){
        return persoSurLaTuile;
    }
}
