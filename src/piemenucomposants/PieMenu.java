/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piemenucomposants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.Popup;
import javax.swing.PopupFactory;

/**
 *
 * @author cannacan
 */
public class PieMenu extends JComponent {

    private ArrayList<ItemPieMenu> items;
    private int xDep;
    private int yDep;

    private int widthPie;
    private int heigthPie;

    public PieMenu() {
        items = new ArrayList<ItemPieMenu>();
        xDep = 100;
        yDep = 100;
        widthPie = 150;
        heigthPie = 150;
    }

    public List getItems() {
        return items;
    }

    public void setItems(ArrayList items) {
        this.items = items;
    }

    public void addItem(ItemPieMenu i) {
        items.add(i);
        invalidate();
    }

    public int getxDep() {
        return xDep;
    }

    public void setxDep(int xDep) {
        this.xDep = xDep;
    }

    public int getyDep() {
        return yDep;
    }

    public void setyDep(int yDep) {
        this.yDep = yDep;
    }

    public int getWidthPie() {
        return widthPie;
    }

    public void setWidthPie(int widthPie) {
        this.widthPie = widthPie;
    }

    public int getHeigthPie() {
        return heigthPie;
    }

    public void setHeigthPie(int heigthPie) {
        this.heigthPie = heigthPie;
    }

    public void highlightQuartier(int x, int y) {
        System.out.println("x : " + xDep + "  y : " + yDep);
        Graphics2D g2 = (Graphics2D) getGraphics();
        int r = widthPie/2;
        double n = Math.sqrt((double)((x - r) * (x - r) + (y - r) * (y - r)));
        double sinTheta = (y - r)/n;
        double cosTheta = (x - r)/n;
        double arcSin = Math.asin(sinTheta);
        double arcCos = Math.acos(cosTheta);
        System.out.println("arcsin : " + arcSin);
        System.out.println("arccos : " + arcCos);
        /*
        if (g2 != null) {
            System.out.println("hilight");
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setPaint(new GradientPaint(5, 7, Color.WHITE, 400, 7, new Color(187, 210, 247)));
            g2.fillArc(0, 0, widthPie, heigthPie, 0, 90);
        }*/
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        int angle = 360 / items.size();

        //Pour palier au probleme du nombre d'items impairs
        if (360 % items.size() != 0) {
            angle++;
        }

        System.out.println("angle : " + angle);
        for (int i = 0; i < items.size(); i++) {
            ItemPieMenu item = items.get(i);

            // Tracer les arcs de cercle 
            g2.setColor(item.getBackground());
            g2.fillArc(0, 0, widthPie, heigthPie, angle * i, angle);

            //Tracé du cercle
            int d = widthPie / 2;
            int x = (int) ((widthPie * 1 / 3) * Math.cos(Math.toRadians((-1) * (angle * (2 * i + 1)) / 2)));
            int y = (int) ((d * 2 / 3) * Math.sin(Math.toRadians((-1) * (angle * (2 * i + 1)) / 2)));
            g2.setColor(item.getForeground());
            g2.drawString(item.getText(), x + d, y + d);
        }

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(widthPie, heigthPie);
    }
}
