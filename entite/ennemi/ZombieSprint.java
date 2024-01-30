package entite.ennemi;

import java.awt.Rectangle;
import java.awt.Toolkit;

public class ZombieSprint extends Ennemi {

    public ZombieSprint(double x, double y){
        this.setImg(Toolkit.getDefaultToolkit().getImage("ressource/enemy/zombieSprint/zombieSprint.png"),0);
        this.setX(x);
        this.setY(y);
        switch (getDifficulte()) {
            case 1:
                setValeurFacile();
                break;
            case 2:
                setValeurParDefaut();
                break;
            case 3:
                setValeurDifficile();
                break;
        }
        this.setDmgToAdd(5);
        this.setPvToAdd((int) (this.getPv()*0.1));
        this.setNom("Zombie sprint");
        this.setHitbox(new Rectangle((int)getX(),(int)getY(),128,128));
        this.setLastAtk(0);
    }

    private void setValeurParDefaut(){
        this.setDmg(15);
        this.setVitesse(1);
        this.setcooldownAtk(5000); //c'est en ms (5000 ms = 5 s)
        this.setPv(70);
        this.setPvMax(this.getPv());
        this.setGoldDrop(30);
    }

    private void setValeurFacile(){
        this.setDmg(10);
        this.setVitesse(0.9);
        this.setcooldownAtk(6000); //c'est en ms (6000 ms = 6 s)
        this.setPv(60);
        this.setPvMax(this.getPv());
        this.setGoldDrop(40);
    }

    private void setValeurDifficile(){
        this.setDmg(20);
        this.setVitesse(1.1);
        this.setcooldownAtk(4000); //c'est en ms (4000 ms = 4 s)
        this.setPv(80);
        this.setPvMax(this.getPv());
        this.setGoldDrop(20);
    }

}
