/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

/**
 *
 * @author violentt
 */
public interface Observe {

    public void addObservateur(Observateur o);

    public void notifierObservateur(Message m);
}
