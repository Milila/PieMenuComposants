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

    void pieMenuMouseMove(PieMenuEvent evt);

    void pieMenuMouseDown(PieMenuEvent evt);
    
    void pieMenuMouseUp(PieMenuEvent evt);
    
    void pieMenuTick(PieMenuEvent evt);
}
