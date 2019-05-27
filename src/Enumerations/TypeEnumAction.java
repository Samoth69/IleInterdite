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
public enum TypeEnumAction {
    NA,                             //= non applicable
    DEPLACER_JOUEUR,                //demande de déplacement de joueur
    ASSECHER,                       //demande pour assécher une ile
    DONNER_CARTE,                   //demande pour donner une carte à un autre joueur
    GAGNER_TRESOR,                  //demande pour récupérer un trésor SI on est sur la bonne case
    PIOCHER_DEUX_CARTE_TRESOR,      //demande pour piocher les deux carte au trésor après les actions du joueur
    PIOCHER_CARTE_MONTEE_DES_EAUX   //demande pour piocher la (les) carte(s) montée des eaux en fonction du niveau actuel
}
