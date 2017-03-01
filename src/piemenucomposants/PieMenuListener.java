/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piemenucomposants;

/**
 *
 * @author bouzekel
 */
public interface PieMenuListener {

    void pieMenuClick(PieMenuEvent evt);

    void pieMenuEntered(PieMenuEvent evt);
    
    void pieMenuExited(PieMenuEvent evt);
    
}
