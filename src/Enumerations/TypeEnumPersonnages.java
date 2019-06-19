/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enumerations;

/**
 *
 * @author violentt
 */
//nom des différents role de l'ile interdite
public enum TypeEnumPersonnages {

    EXPLORATEUR("Explorateur"),
    INGENIEUR("Ingénieur"),
    MESSAGER("Messager"),
    NAVIGATEUR("Navigateur"),
    PILOTE("Pilote"),
    PLONGEUR("Plongeur");

    private String nom = "";

    TypeEnumPersonnages(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom;
    }
}
