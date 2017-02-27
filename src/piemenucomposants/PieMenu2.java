/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piemenucomposants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;

/**
 *
 * @author bouzekel
 */
public class PieMenu2 extends JComponent {

    private List<ItemPieMenu> listeItem = new ArrayList<ItemPieMenu>();
    private final static int MIN_ITEM = 1;
    private final static int MAX_ITEM = 8;
    private final static int START_ANGLE = 0;
    private final static int END_ANGLE = 360;

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
//        g2.setColor(Color.red);
//        g2.fillRect(0, 0, getWidth(), getHeight());

        if (listeItem.size() > 0) {
            int angle = END_ANGLE / listeItem.size();
            for (int cpt = 0; cpt < listeItem.size(); cpt++) {
                ItemPieMenu item = listeItem.get(cpt);
                //System.out.println(cpt + " " + item.getText() + " " + getX() + " " + getY() + " " + (START_ANGLE + cpt * angle) + " " + angle);
                
                
                // Tracer les arcs de cercle 
                g2.setColor(item.getBackground());
                g2.fillArc(getX(), getY(), getWidth(), getWidth(), START_ANGLE + cpt * angle, angle);

                // Dessiner Labels
                g2.setColor(item.getForeground());
                 
                
                //g2.rotate(Math.toRadians(angle));
                int rayon = getWidth() / 2;
                int x = (int)Math.abs(rayon+(Math.cos( Math.toRadians( ((cpt) * angle + angle/2)))*rayon/2));
                int y = (int)Math.abs(rayon-(Math.sin( Math.toRadians( ((cpt) * angle + angle/2)))*rayon/2));
               
                
                System.out.println("angle : " + Math.toRadians( (cpt+1) * angle)+" / "+((cpt+1) * angle )+ " " +x + " " + y);
                
                g2.drawString(item.getText(), (int) x, (int) y);
                
               
            }
        }
    }

    /*
    g2.setColor(Color.GRAY);
        g2.drawString("Suivant", xDep + 20, yDep + 50);
        g2.drawString("Précedent", xDep + 5 + withPie / 2, yDep + 50);
        g2.drawString("Supprimer", xDep + 15, yDep + 40 + withPie / 2);
        g2.drawString("Modifier", xDep + 10 + withPie / 2, yDep + 40 + withPie / 2);
     */
    public ItemPieMenu addItem(ItemPieMenu item) {
        if (listeItem.size() <= MAX_ITEM) {
            listeItem.add(item);
            return item;
        } else {
            return null;
        }
    }

    public ItemPieMenu remove(ItemPieMenu item) {
        if (listeItem.size() > MIN_ITEM) {
            listeItem.remove(item);
            return item;
        } else {
            return null;
        }
    }

}
