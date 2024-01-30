package entite.tour;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import entite.ennemi.Ennemi;
import main.GamePanel;

public class Projectile {
    private double x,y; //les coordonnées
    public double getX() {return x;}
    public void setX(double x) {this.x = x;}
    public double getY() {return y;}
    public void setY(double y) {this.y = y;}

    private Image img; //stockera toutes les images du projectile
    public Image getImg() {return img;}
    public void setImg(Image img) {this.img = img;}
    
    private Rectangle hitbox; //la zone de dégât
    public Rectangle getHitbox() {return hitbox;}
    public void setHitbox(Rectangle hitbox) {this.hitbox = hitbox;}

    private int degat;

    private int type; // 0 = projectile qui bouge // 1 = projectile immobile // 2 = projectile Jeongyeon// 3 = projectile Jihyo

    private long tempsApparition;

    private boolean haveToRemove = false;
    public boolean isHaveToRemove() {return haveToRemove;}

    private double vitesse; //combien de pixel l'entité traverse
    public double getVitesse() {return vitesse;}
    public void setVitesse(double vitesse) {this.vitesse = vitesse;}

    private static GamePanel gp;
    public static void setGp(GamePanel gp) {Projectile.gp = gp;}

    public Projectile(double x, double y, Image img, int degat, int type, double vitesse){
        this.x = x;
        this.y = y;
        this.img = img;
        this.hitbox = new Rectangle((int)x,(int)y,64,64);
        this.degat = degat;
        this.type = type;
        this.vitesse = vitesse;
        if(type == 3){
            tempsApparition = System.currentTimeMillis();
            gp.getTourelleG().addGold(degat);
        }
    }

    public Projectile(double y, Image img, int degat, int type, double vitesse){
        findFirstEnnemyAhead(y);
        this.y = y;
        this.img = img;
        this.hitbox = new Rectangle((int)x,(int)y,64,64);
        this.degat = degat;
        this.type = type;
        this.vitesse = vitesse;
    }

    public void draw(Graphics2D component2d){
        component2d.drawImage(img, (int) x, (int) y, 128, 128, null);
    }

    public void update(){
        switch (type) {
            case 0:
                this.move();
                break;
            
            case 3:
                if(System.currentTimeMillis() - tempsApparition >= 2000){this.haveToRemove = true;}
                else{this.haveToRemove = false;}
                break;
        
            default:
                break;
        }
    }

    public void move(){
        this.x += this.vitesse;
    }

    public boolean estSurEcran(int x, int y){
        return this.x >= 0 && this.x <= x && this.y >= 0 && this.y <= y;
    }

    public boolean toucheEnnemi(Ennemi e){
        if(type == 3) {return false;} //le projectile de type ne peut pas faire de dégâts à l'ennemi
        return (e.getHitbox().contains(x,y));
    }

    public void hurtEnnemi(Ennemi e){
        e.setPv(e.getPv() - degat);
    }

    public void findFirstEnnemyAhead(double y){
        Ennemi first = gp.getEnnemiG().getEnnemi(0);
        boolean found = false;
        for(Ennemi e : gp.getEnnemiG().getEnnemis()){
            if(e.getY()+32 == y){
                if(!found){first = e; found = true;}
                else{
                    if(e.getX() < first.getX()){
                        first = e;
                    }
                }
            }
        }
        if(found){this.x = first.getX();}
    }
}
