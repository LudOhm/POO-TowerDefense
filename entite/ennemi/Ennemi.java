package entite.ennemi;

import java.awt.Image;

import entite.Entite;
import entite.tour.Tourelle;
import java.awt.Rectangle;

public abstract class Ennemi extends Entite { //la class ennemi de base qui extends Entite, tous les ennemis utiliseront cette class 
    private String nom; 
    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}

    private Image[] img = new Image[10]; //stockera toutes les images de l'ennemi
    public Image getImg(int i) {return img[i];}
    public void setImg(Image img, int i) {this.img[i] = img;}
    
    private Rectangle hitbox; //la zone de dégât
    public Rectangle getHitbox() {return hitbox;}
    public void setHitbox(Rectangle hitbox) {this.hitbox = hitbox;}

    private double vitesse; //combien de pixel l'entité traverse
    public double getVitesse() {return vitesse;}
    public void setVitesse(double vitesse) {this.vitesse = vitesse;}

    private int goldDrop;
    public int getGoldDrop() {return goldDrop;}
    public void setGoldDrop(int goldDrop) {this.goldDrop = goldDrop;}

    private boolean crossedTheLine = false; //pour savoir si un ennemi a fini le chemin
    public boolean getCrossedTheLine() {return crossedTheLine;}

    public void move(){ //méthode pour faire déplacer les zombies
        this.setX(this.getX()-this.vitesse); 
        this.hitbox.x = (int) this.getX();
        if(this.getX() <= 20){
            crossedTheLine = true;
        }
    }

    public void attaque(Tourelle tour){
        if(this.getLastAtk() == 0){this.setLastAtk(System.currentTimeMillis());}
        if(System.currentTimeMillis() - this.getLastAtk() >= this.getCooldownAtk()){
            tour.setPv(tour.getPv()-this.getDmg());
            this.setLastAtk(System.currentTimeMillis());
        }
    }

    public void ameliorerStat(int multiplicateur){
        System.out.println(this.getPv());
        System.out.println(this.getPvMax());
        System.out.println(this.getDmg());
        System.out.println();
        this.setPv(this.getPv() + (this.getPvToAdd() * multiplicateur));
        this.setPvMax(this.getPvMax() + (this.getPvToAdd() * multiplicateur));
        this.setDmg(this.getDmg() + (this.getDmgToAdd() * multiplicateur));
        System.out.println(this.getPv());
        System.out.println(this.getPvMax());
        System.out.println(this.getDmg());
        System.out.println("------");
        System.out.println(multiplicateur);
        System.out.println("------");
    }

}
