package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ControleSouris implements MouseListener, MouseMotionListener{ //MouseListener --> pour les clics / MouseMotionListener --> pour les mouvements de souris
    private int x,y;    
    private boolean sourisClique;

    public int getX() {return x;}
    public int getY() {return y;}
    public boolean getSourisClique() {return sourisClique;}
    public void setSourisClique(boolean b) {this.sourisClique = b;}

    public ControleSouris(){
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.sourisClique = true;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = (int)e.getX();
        y = (int)e.getY();
    }
    
}
