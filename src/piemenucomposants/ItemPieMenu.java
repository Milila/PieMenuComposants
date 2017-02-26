/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piemenucomposants;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author cannacan
 */
public class ItemPieMenu {
    
    
    //IL MANQUE LES ACTIONS ASSOCIEES A CHAQUE ITEM!!!
    
    private static final String PROPERTY_TEXT = "text";
    private static final String PROPERTY_BACKGROUND = "background";
    private static final String PROPERTY_FOREGROUND = "foreground";
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    
    private String text;
    private Color background;
    private Color foreground;
    private int angle;
    
    public ItemPieMenu (String t, Color b, Color f){
        this.text = t;
        this.background = b;
        this.foreground = f;
        
        this.privateAddPropertyChange(PROPERTY_TEXT, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                //DO SOMETHING
            }
        });
        this.privateAddPropertyChange(PROPERTY_BACKGROUND, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                //DO SOMETHING
            }
        });
        this.privateAddPropertyChange(PROPERTY_FOREGROUND, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                //DO SOMETHING
            }
        });
    }
    
    
    public void setText (String t){
        String oldText = this.text;
        this.text = t;
        support.firePropertyChange(PROPERTY_TEXT, oldText, t);
    }
    public String getText(){
        return this.text;
    }
    
    public void setBackground(Color b){
        Color oldColor = this.background;
        this.background = b;
        support.firePropertyChange(PROPERTY_BACKGROUND, oldColor, b);
    }
    public Color getBackground(){
        return this.background;
    }
    
    
    public void setForeground(Color f){
        Color oldColor = this.foreground;
        this.foreground = f;
        support.firePropertyChange(PROPERTY_FOREGROUND, oldColor, f);
    }
    public Color getForeground(){
        return this.foreground;
    }
    
    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
    
    public void privateAddPropertyChange (String propertyName, PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }
    
    public void privateRemovePropertyChange (String propertyName, PropertyChangeListener listener){
        support.removePropertyChangeListener(listener);
    }
    
}
