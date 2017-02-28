/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piemenucomposants;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import sun.security.krb5.internal.KDCOptions;

/**
 *
 * @author cannacan
 */
public class ItemPieMenu extends JComponent {

    //IL MANQUE LES ACTIONS ASSOCIEES A CHAQUE ITEM!!!
    private static final String PROPERTY_TEXT = "text";
    private static final String PROPERTY_BACKGROUND = "background";
    private static final String PROPERTY_FOREGROUND = "foreground";
    private static final String PROPERTY_WIDTH = "width";
    private static final String PROPERTY_HEIGTH = "heigth";
    private static final String PROPERTY_NUM_QUARTIER = "numquartier";
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private List<PieMenuListener> itemListeners;

    private boolean armed;
    private int widthPie = 150;
    private int heigthPie = 150;
    private int num_quartier = 0;

    private String text;
    private Color background;
    private Color foreground;
    private int angle;

    public ItemPieMenu() {
        this("", Color.BLACK, Color.BLUE);
    }

    public ItemPieMenu(String t, Color b, Color f) {
        this.text = t;
        this.background = b;
        this.foreground = f;
        this.itemListeners = new ArrayList();

        this.privateAddPropertyChange(PROPERTY_TEXT, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                repaint();
            }
        });
        this.privateAddPropertyChange(PROPERTY_BACKGROUND, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                repaint();
            }
        });
        this.privateAddPropertyChange(PROPERTY_FOREGROUND, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                repaint();
            }
        });
        this.privateAddPropertyChange(PROPERTY_HEIGTH, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                repaint();
            }
        });
        this.privateAddPropertyChange(PROPERTY_NUM_QUARTIER, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                repaint();
            }
        });
        this.privateAddPropertyChange(PROPERTY_WIDTH, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                repaint();
            }
        });

        this.addMouseListener(new MouseAdapter() {
            private Color color = getBackground();

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(color);
                System.out.println("Exited");
                //armed = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                armed = true;
                System.out.println("Entered");
                System.out.println("num_quartier " + getNum_quartier() + " angle : " + angle);
                setBackground(Color.BLUE.darker());
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                fireItemClicked(new PieMenuEvent(this));
                getParent().setVisible(false);
                System.out.println("CLICK " + (getNum_quartier() + 1));
            }

        });

    }

    private void fireItemClicked(PieMenuEvent itemEvent) {
        for (PieMenuListener listener : itemListeners) {
            listener.pieMenuMouseDown(itemEvent);
        }
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        //System.out.println(widthPie+" "+heigthPie+" "+angle+" "+num_quartier);
        Graphics2D g2 = (Graphics2D) graphics;
        Color oldColor = graphics.getColor();
        g2.setColor(background);
        g2.fillArc(0, 0, widthPie, heigthPie, angle * num_quartier, angle);

        //Tracé du cercle
        int d = widthPie / 2;
        int x = (int) ((widthPie * 1 / 3) * Math.cos(Math.toRadians((-1) * (angle * (2 * num_quartier + 1)) / 2)));
        int y = (int) ((d * 2 / 3) * Math.sin(Math.toRadians((-1) * (angle * (2 * num_quartier + 1)) / 2)));
        g2.setColor(foreground);
        g2.drawString(text, x + d, y + d);
        g2.setColor(oldColor);
    }

    public int getWidthPie() {
        return widthPie;
    }

    public void setWidthPie(int widthPie) {
        int oldWidth = this.widthPie;
        this.widthPie = widthPie;
        support.firePropertyChange(PROPERTY_WIDTH, oldWidth, widthPie);
    }

    public int getHeigthPie() {
        return heigthPie;
    }

    public void setHeigthPie(int heigthPie) {
        int oldHeigth = this.heigthPie;
        this.heigthPie = heigthPie;
        support.firePropertyChange(PROPERTY_HEIGTH, oldHeigth, heigthPie);
    }

    public int getNum_quartier() {
        return num_quartier;
    }

    /*
    public void draw(Graphics2D g2, int widthPie, int heigthPie, int depangle, int endangle) {
    g2.setColor(background);
    g2.fillArc(0, 0, widthPie, heigthPie, depangle, endangle);   
    }
     */
    public void setNum_quartier(int num_quartier) {
        int oldNum_quartier = this.num_quartier;
        this.num_quartier = num_quartier;
        support.firePropertyChange(PROPERTY_NUM_QUARTIER, oldNum_quartier, this.num_quartier);
    }

    public void setText(String t) {
        String oldText = this.text;
        this.text = t;
        support.firePropertyChange(PROPERTY_TEXT, oldText, t);
    }

    public String getText() {
        return this.text;
    }

    public void setBackground(Color b) {
        Color oldColor = this.background;
        this.background = b;
        support.firePropertyChange(PROPERTY_BACKGROUND, oldColor, b);
    }

    public Color getBackground() {
        return this.background;
    }

    public void setForeground(Color f) {
        Color oldColor = this.foreground;
        this.foreground = f;
        support.firePropertyChange(PROPERTY_FOREGROUND, oldColor, f);
    }

    public Color getForeground() {
        return this.foreground;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public void privateAddPropertyChange(String propertyName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void privateRemovePropertyChange(String propertyName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    @Override
    public boolean contains(int x, int y) {
        x -= widthPie / 2;
        y -= widthPie;
        y = Math.abs(y);
        y -= widthPie / 2;

        double myAngle = Math.atan2(y, x);
        if (myAngle < 0) {
            myAngle = Math.abs(myAngle);
            myAngle = Math.toDegrees(myAngle);
            myAngle -= 180;
            myAngle = Math.abs(myAngle);
            myAngle += 180;
        } else {
            myAngle = Math.toDegrees(myAngle);
        }
        if (myAngle > angle * getNum_quartier() && myAngle < angle + angle * getNum_quartier()) {
            return true;
        } else {
            return false;
        }
    }

}
