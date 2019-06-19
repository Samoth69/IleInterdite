/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enumerations;

/**
 *
 * @author paul m
 */
public enum TypeEnumCarteAction {

    HELICOPTERE("Hélicoptère"),
    SAC_DE_SABLE("Sac de sable"),
    NA("NA");

    private String nom = "";

    TypeEnumCarteAction(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom;
    }
}
