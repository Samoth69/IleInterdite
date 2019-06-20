/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Enumerations.TypeEnumCouleurPion;
import Personnages.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author mariottp
 */
public class Pion extends JComponent{

    //ATTRIBUTS
    private Personnage perso;
    private TypeEnumCouleurPion couleur;
    private int width;
    private int height;
    
    //CONSTRUCTEUR1
    public Pion(Personnage perso){
        this.couleur = perso.getCouleurPion();
        width = 30;
        height = 30;
        this.perso = perso;
    }
    
    //CONSTRUCTEURS2
    public Pion(TypeEnumCouleurPion couleur){
        this.couleur = couleur;
        width = 30;
        height = 30;
    }
    //CONSTRUCTEURS3
    public Pion(TypeEnumCouleurPion couleur, int width, int height) {
        this.couleur = couleur;
        this.width = width;
        this.height = height;
    }
    
    //Fonction appelé par defaut pour dessiner le pion /mettre la couleur
    @Override
    public void paint(Graphics g){
        Dimension dim = getSize();
        
        g.setColor(Color.white);
        g.fillOval(0, 0, width, height);
        
        switch(couleur)
        {
            case BLEU:
                g.setColor(Color.BLUE);
            break;
            case JAUNE:
                g.setColor(Color.YELLOW);
            break;
            case ORANGE:
                g.setColor(Color.ORANGE);
            break;
            case ROUGE:
                g.setColor(Color.RED);
            break;
            case VERT:
                g.setColor(Color.GREEN);
            break;
            case VIOLET:
                g.setColor(Color.MAGENTA);
            break;
            default:
                g.setColor(Color.WHITE);
            break;
        }
        g.fillOval(3, 3, width - 6, height - 6);
        
        
    }
    
    //retourne une dimension predefinie
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }   
    
    //getteur pour la couleur du pion en fonction du personnage
    public TypeEnumCouleurPion getCouleurPion(){
        return couleur;
    }

    //assigne la couleur au pion
    public void setCouleur(TypeEnumCouleurPion couleur) {
        this.couleur = couleur;
    }
    
    public Personnage getPerso() {
        return perso;
    }
}
