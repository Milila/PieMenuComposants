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

/**
 * Item d'un pie menu
 * Possibilité de changer la couleur de texte, de fond, et de surbrillance
 * Possibilité de changer le texte
 * Angle variable en fonction du nombre d'items dans le pie menu
 * @author cannacan
 */
public class ItemPieMenu extends JComponent {
    /**
     * Propriétés de l'item du PieMenu : le texte affiché, la couleur de fond,
     * la couleur du texte, la valeur de l'angle, le numéro du quartier dans 
     * le piemenu et la couleur en surbrillance de l'item/quartier.
     */
    private static final String PROPERTY_TEXT = "text";
    private static final String PROPERTY_BACKGROUND = "background";
    private static final String PROPERTY_FOREGROUND = "foreground";
    private static final String PROPERTY_ANGLE = "angle";
    private static final String PROPERTY_NUM_QUARTIER = "numquartier";
    private static final String PROPERTY_BACKGROUND_HIGHLIGHT = "backgroundhighlight";
    private PropertyChangeSupport support;
    private List<PieMenuListener> itemListeners;

    /**
     * Taille du PieMenu associé à l'item.
     */
    private int widthPie;
    private int heigthPie;
    
    /**
     * Variables associées aux propriétés de l'item.
     */
    private int num_quartier = 0;
    private String text;
    private Color background;
    private Color foreground;
    private Color highlightBackground;
    private int angle;

    /**
     * Constructeur sans paramètres.
     */
    public ItemPieMenu() {
        this("", Color.BLACK, Color.BLUE);
    }

    /**
     * Constructeur de l'item.
     * @param t
     * Texte à afficher dans l'item.
     * @param b
     * Couleur de l'item.
     * @param f 
     * Couleur du texte.
     */
    public ItemPieMenu(String t, Color b, Color f) {
        this.text = t;
        this.background = b;
        this.highlightBackground = new Color(background.getRed(), background.getGreen(), background.getBlue(), 50);
        this.foreground = f;
        this.itemListeners = new ArrayList();
        this.support = new PropertyChangeSupport(this);

        //Repeindre le composant lorsqu'une propriété est modifiée.
        this.privateAddPropertyChangeListener(PROPERTY_TEXT, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                repaint();
            }
        });
        this.privateAddPropertyChangeListener(PROPERTY_BACKGROUND, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                repaint();
            }
        });
        this.privateAddPropertyChangeListener(PROPERTY_BACKGROUND_HIGHLIGHT, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                repaint();
            }
        });
        this.privateAddPropertyChangeListener(PROPERTY_FOREGROUND, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                repaint();
            }
        });
        this.privateAddPropertyChangeListener(PROPERTY_ANGLE, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                repaint();
            }
        });
        this.privateAddPropertyChangeListener(PROPERTY_NUM_QUARTIER, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                repaint();
            }
        });

        /**
         * Comportement de l'item : highlight quand on entre la souris, couleur 
         * de base quand la souris quitte l'item et appel aux listeners associés
         * à mouseEntered, mouseExited et mouseClicked.
        */
        this.addMouseListener(new MouseAdapter() {
            private Color color = getBackground();

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(color);
                fireItemExited(new PieMenuEvent(this));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(highlightBackground);
                fireItemEntered(new PieMenuEvent(this));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                fireItemClicked(new PieMenuEvent(this));
                getParent().setVisible(false);
            }

        });

    }

    /**
     * Déclenchement des actions associées à mouseEntered.
     * @param itemEvent 
     * Evenement PieMenu.
     */
    private synchronized void fireItemEntered(PieMenuEvent itemEvent) {
        for (PieMenuListener listener : itemListeners) {
            listener.pieMenuEntered(itemEvent);
        }
    }
    
    /**
     * Déclenchement des actions associées à mouseExited.
     * @param itemEvent 
     * Evenement PieMenu.
     */
    private synchronized void fireItemExited(PieMenuEvent itemEvent) {
        for (PieMenuListener listener : itemListeners) {
            listener.pieMenuExited(itemEvent);
        }
    }
    
    /**
     * Déclenchement des actions associées à mouseClicked.
     * @param itemEvent 
     * Evenement PieMenu.
     */
    private synchronized void fireItemClicked(PieMenuEvent itemEvent) {
        for (PieMenuListener listener : itemListeners) {
            listener.pieMenuClick(itemEvent);
        }
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        Graphics2D g2 = (Graphics2D) graphics;
        Color oldColor = graphics.getColor();
        g2.setColor(background);
        g2.fillArc(0, 0, widthPie, heigthPie, angle * num_quartier, angle);

        //Tracé de l'arc de cercle
        int d = widthPie / 2;
        int x = (int) ((widthPie * 1 / 3) * Math.cos(Math.toRadians((-1) * (angle * (2 * num_quartier + 1)) / 2)));
        int y = (int) ((d * 2 / 3) * Math.sin(Math.toRadians((-1) * (angle * (2 * num_quartier + 1)) / 2)));
        //Tracé du label
        g2.setColor(foreground);
        g2.drawString(text, x + d, y + d);
        g2.setColor(oldColor);
    }

    /**
     * Getter et Setter des variables.
     */
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

    public int getNum_quartier() {
        return num_quartier;
    }

    public void setNum_quartier(int num_quartier) {
        int oldNum_quartier = this.num_quartier;
        this.num_quartier = num_quartier;
        support.firePropertyChange(PROPERTY_NUM_QUARTIER, oldNum_quartier, this.num_quartier);
    }

    public Color getHighlightBackground() {
        return highlightBackground;
    }

    public void setHighlightBackground(Color highlightBackground) {
        this.highlightBackground = highlightBackground;
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
        int oldAngle = this.angle;
        this.angle = angle;
        support.firePropertyChange(PROPERTY_ANGLE, oldAngle, this.angle);
    }
    
    public void privateAddPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void privateRemovePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
    
    @Override
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        privateAddPropertyChangeListener(propertyName, listener);
    }

    @Override
    public boolean contains(int x, int y) {
        //On se place au centre du cercle
        int rayon = widthPie/2;
        x = x - rayon;
        y -= widthPie;
        y = Math.abs(y);
        y = y - rayon;

        //On calcule l'angle dans forme entre ces deux coordonnees
        double nouvelAngle = Math.atan2(y, x);
        if (nouvelAngle < 0) {
            nouvelAngle = Math.abs(nouvelAngle);
            nouvelAngle = Math.toDegrees(nouvelAngle);
            nouvelAngle -= 180;
            nouvelAngle = Math.abs(nouvelAngle);
            nouvelAngle += 180;
        } else {
            nouvelAngle = Math.toDegrees(nouvelAngle);
        }
        //Si notre angle obtenu est compris entre l'angle de départ, et l'angle de départ + un angle complet, on est dedans
        if (nouvelAngle > angle * getNum_quartier() && nouvelAngle < angle + angle * getNum_quartier()) {
            return true;
        } else {
            return false;
        }
    }

     public void addItemListener(PieMenuListener listener) {
        itemListeners.add(listener);
    }

    public void removeItemListener(PieMenuListener listener) {
        itemListeners.remove(listener);
    }

    
}
