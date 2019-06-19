/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enumerations;

import java.awt.Color;

/**
 *
 * @author Thomas
 */
public enum TypeEnumCouleurPion {

    AUCUN("AUCUN"),
    BLEU("Bleu"),
    JAUNE("Jaune"),
    ORANGE("Orange"),
    ROUGE("Rouge"),
    VIOLET("Violet"),
    VERT("Vert");

    private String nom = "";

    TypeEnumCouleurPion(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom;
    }

    public Color getColor() {
        switch (this) {
            case AUCUN:
                return Color.WHITE;
            case BLEU:
                return Color.BLUE;
            case JAUNE:
                return Color.YELLOW;
            case ORANGE:
                return Color.ORANGE;
            case ROUGE:
                return Color.RED;
            case VERT:
                return Color.GREEN;
            case VIOLET:
                return new Color(238, 130, 238);
        }
        return Color.WHITE; //suppresion de warning, normalement jamais atteind.
    }

}
