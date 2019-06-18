/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import IleInterdite.Message;
import IleInterdite.Observateur;
import javax.swing.JLayeredPane;

/**
 *
 * @author Eric Cette classe contient 3 images superposées : - une photo de
 * jardin en arrière-plan - une statue en plan intermédiaire - un dragon au
 * premier plan
 */
public class ReglesDuJeu extends JLayeredPane {

    public ReglesDuJeu() {
    }

    private Observateur observateur;

    public void addObservateur(Observateur o) {
        this.observateur = o;
    }

    public void notifierObservateur(Message m) {
        if (observateur != null) {
            observateur.traiterMessage(m);
        }
    }

    public void afficher() {
        Page1 page1 = new Page1();
        page1.afficher();
    }

}
