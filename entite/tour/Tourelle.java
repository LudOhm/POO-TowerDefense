package entite.tour;

import java.awt.Image;
import java.util.ArrayList;

import entite.Entite;

public abstract class Tourelle extends Entite {
    private String nom; 
    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}

    private Image imgAffiche;
    public Image getImgAffiche() {return imgAffiche;}
    public void setImgAffiche(Image imgAffiche) {this.imgAffiche = imgAffiche;}

    private Image[] img = new Image[10]; //stockera toutes les images de la tourelle
    public Image getImg(int i) {return img[i];}
    public void setImg(Image img, int i) {this.img[i] = img;}

    private Image[] imgProj = new Image[5]; //stockera toutes les images de son projectiles
    public Image[] getImgProj() {return imgProj;}
    public Image getImgProj(int i) {return imgProj[i];}
    public void setImgProj(Image img, int i) {this.imgProj[i] = img;}

    private double vitesseAtk;
    public double getVitesseAtk() {return vitesseAtk;}
    public void setVitesseAtk(double vitesse) {this.vitesseAtk = vitesse;}

    private static ArrayList<Projectile> tabProjectiles = new ArrayList<>();
    public static ArrayList<Projectile> getTabProj() {return tabProjectiles;}
    public void projAjoute(Projectile p) {tabProjectiles.add(p);}
    public static void projEnleve(Projectile p) {tabProjectiles.remove(p);}

    private boolean isAtking = false;
    public boolean isAtking() {return isAtking;}
    public void setAtking(boolean isAtking) {this.isAtking = isAtking;}

    private int price;
    public static int getPrice(Tourelle t) {return t.price;}
    public void setPrice(int price) {this.price = price;}

    private int pvLevel = 0;
    public int getPvLevel() {return pvLevel;}
    public void setPvLevel(int pvLevel) {this.pvLevel = pvLevel;}

    private int atkLevel = 0;
    public int getAtkLevel() {return atkLevel;}
    public void setAtkLevel(int atkLevel) {this.atkLevel = atkLevel;}

    private int cdAtkLevel = 0;
    public int getCdAtkLevel() {return cdAtkLevel;}
    public void setCdAtkLevel(int cdAtkLevel) {this.cdAtkLevel = cdAtkLevel;}

    private int priceRevente;
    public int getPriceRevente() {return priceRevente;}
    public void setPriceRevente(int priceRevente) {this.priceRevente = priceRevente;}


    public void attaque(){
    }

    public void changeAffichageAtk(){
        
    }


}
