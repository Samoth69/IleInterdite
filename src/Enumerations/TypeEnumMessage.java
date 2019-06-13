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
public enum TypeEnumMessage {
    NA,             // = non applicable
    ACTION,         //action à éffectuer par la vue du plateau (sera préciser par TypeEnumAction)
    CARTE,          // ?
    JOUEUR_SUIVANT,  //indique à l'IHM que l'on change de joueur
    DEFAUSSE_CARTE,  //message pour defausser des cartes
    CHANGEMENT_NIVEAU_EAU, //niveau d'eau qui change
    PIOCHE_CARTE_INONDATION, //permet d'appeler la vudefausse afin d'afficher les cartes qui ont été pioché
    UNLOCK_PLATEAU //envoyer par VuDefausse. indique au plateau qu'il peut set Enabled sur true pour pouvoir continuer à jouer
}
