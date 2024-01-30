package main;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Image;

import javax.swing.*;


public class Main {
    private static JFrame fenetre;
    public static JFrame getFenetre() {return fenetre;}
    
    public static void main(String[] args) {
        fenetre = new JFrame();
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Pour arrêter le programme quand on ferme la fenêtre
        fenetre.setResizable(false); //Pour que l'utilisateur ne puisse pas modifier les dimensions de la fenêtre
        fenetre.setTitle("Time To Tower"); //Le nom de la fenêtre
        //fenetre.setUndecorated(true); //Pour pas avoir la barre en haut de la fenêtre

        GamePanel gp = new GamePanel();
        fenetre.add(gp);
        fenetre.pack(); //la fenêtre prend la taille de GamePanel

        //Pour mettre un curseur personnalisé
        Image curseurImg = Toolkit.getDefaultToolkit().getImage("ressource/curseur/curseur.png");
        Cursor Curseur = Toolkit.getDefaultToolkit().createCustomCursor(curseurImg, new Point(), "Curseur");
        fenetre.getContentPane().setCursor(Curseur);

        fenetre.setLocationRelativeTo(null); //null donc la fenêtre sera au centre de l'écran
        fenetre.setVisible(true);

        gp.startGameThread();
    }
    
}