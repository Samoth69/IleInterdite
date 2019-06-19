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

    NA, // = non applicable
    JOUEUR_SUIVANT, //indique à l'IHM que l'on change de joueur
    DEFAUSSE_CARTE, //message pour defausser des cartes
    CHANGEMENT_NIVEAU_EAU, //niveau d'eau qui change
    PIOCHE_CARTE_INONDATION, //permet d'appeler la vudefausse afin d'afficher les cartes qui ont été pioché
    UNLOCK_PLATEAU, //envoyer par VuDefausse. indique au plateau qu'il peut set Enabled sur true pour pouvoir continuer à jouer
    HISTORIQUE, //pour ajouté un message à l'historique de la partie (si l'IHM implémente ceci)
    NOUVEAU_TOUR, //indique à l'IHM que un nouveau tour de jeu démare
    UPDATE_GUI, //indique à l'interface de mettre à jour son affichage
    FIN_PARTIE, //indique une fin de partie
    PARTIE_GAGNE, //indique que les joueurs ont gagnés
    RM_TRESOR   //Supprime un tresor d'une case
}
