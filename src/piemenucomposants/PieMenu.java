/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piemenucomposants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JLayeredPane;

/**
 * Pie Menu : possibilité d'ajouter des items.
 *
 * @author cannacan
 */
public class PieMenu extends JLayeredPane {

    //Propriété du nombre de quartier du Pie Menu.
    private static final String PROPERTY_NB_QUATIER = "nombrequartier";

    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private ArrayList<ItemPieMenu> items;

    //Coordonnées du Pie Menu
    private int xDep;
    private int yDep;

    //Dimension du Pie Menu
    private int widthPie;
    private int heigthPie;

    /**
     * Constructeur sans paramètres.
     */
    public PieMenu() {
        super();
        items = new ArrayList<ItemPieMenu>();
        xDep = 100;
        yDep = 100;
        widthPie = 150;
        heigthPie = 150;
        this.setBackground(Color.red);

        this.privateaddPropertyChangeListener(PROPERTY_NB_QUATIER, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                // List<ItemPieMenu> items = (List) evt.getNewValue();
                int angle = 360 / items.size();
                //Pour palier au probleme du nombre d'items impairs
                if (360 % items.size() != 0) {
                    angle++;
                }
                int i = 0;
                for (ItemPieMenu it : items) {
                    it.setHeigthPie(heigthPie);
                    it.setWidthPie(widthPie);
                    it.setAngle(angle);
                    it.setNum_quartier(i);
                    addItemInComponent(it);
                    i++;
                }
                repaint();
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                setVisible(false);
            }
        });
    }

    /**
     * Ajouter l'item dans le panel du piemenu.
     *
     * @param item quartier à ajouter.
     */
    private void addItemInComponent(ItemPieMenu item) {
        this.add(item, item.getNum_quartier());
    }

    /**
     * Getters et Setters.
     */
    public List getItems() {
        return items;
    }

    public void setItems(ArrayList items) {
        List<ItemPieMenu> oldItems = this.items;
        this.items = items;
        support.firePropertyChange(PROPERTY_NB_QUATIER, oldItems, this.items);
    }

    public void addItem(ItemPieMenu i) {
        items.add(i);
        //invalidate();
        support.firePropertyChange(PROPERTY_NB_QUATIER, null, this.items);
    }

    @Override
    public int getWidth() {
        return this.widthPie;
    }

    @Override
    public int getHeight() {
        return this.heigthPie;
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

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(widthPie, heigthPie);
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        for (ItemPieMenu item : items) {
            item.setBounds(0, 0, width, height);
        }
    }

    public void privateaddPropertyChangeListener(String propertyname, PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void privateremovePropertyChangeListener(String propertyname, PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public boolean Contains(int x, int y) {
        double a = getWidth() / 2;
        double b = getHeight() / 2;
        double X = x - a;
        double Y = y - a;
        return ((X * X) / (a * a) + (Y * Y) / (b * b) <= 1);
    }

}
