/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;
import Enumerations.TypeEnumInondation;
import Personnages.Personnage;
import java.util.ArrayList;
import Cartes.CarteInondation;
import Enumerations.TypeEnumCouleurPion;
import Enumerations.TypeEnumTresors;
/**
 *
 * @author violentt
 */
public class Tuile extends CarteInondation {
    
    private int X;
    private int Y;
    private TypeEnumInondation EtatInondation = TypeEnumInondation.SEC;
    private TypeEnumTresors tresor;
    private ArrayList<Personnage> persoSurLaTuile = new ArrayList<Personnage>();
    
    public Tuile(int X, int Y, String nom){
        super(nom);
        setX(X);
        setY(Y);
    }
    
    public Tuile(int X, int Y, String nom, TypeEnumCouleurPion couleurPion){
        super(nom, couleurPion);
        setX(X);
        setY(Y);
    }
    
    public Tuile(int X, int Y, String nom, TypeEnumTresors tresor){
        super(nom, tresor);
        setX(X);
        setY(Y);
       
    }
    
    public Tuile(int X, int Y, String nom, TypeEnumTresors tresor, String image){
        super(nom, tresor, image);
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
    
    public TypeEnumInondation getInondation(){        // Renvoie etat de la tuile (SEC, MOUILLE ou INONDE)
        return EtatInondation;
    }
    
    public void augmenterInondation(){  // Augmente etat de l'inondation de la tuile en fonction de son etat actuel
        switch(EtatInondation)                                      
        {
            
            //Si SEC etat devient MOUILLE
            case SEC:
                EtatInondation = TypeEnumInondation.MOUILLE;
            break;
            
            //Si MOUILLE etat devient INONDE
            case MOUILLE:
                EtatInondation = TypeEnumInondation.INONDE;
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
                EtatInondation = TypeEnumInondation.SEC;
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
    
    public String getNom() {
        return super.getNom();
    }
}
