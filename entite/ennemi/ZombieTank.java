package entite.ennemi;

import java.awt.Rectangle;
import java.awt.Toolkit;

public class ZombieTank extends Ennemi {

    public ZombieTank(double x, double y){
        this.setImg(Toolkit.getDefaultToolkit().getImage("ressource/enemy/zombieTank/zombieTank.png"),0);
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
        this.setNom("Zombie tank");
        this.setHitbox(new Rectangle((int)getX(),(int)getY(),128,128));
        this.setLastAtk(0);
    }

    private void setValeurParDefaut(){
        this.setDmg(10);
        this.setVitesse(0.2);
        this.setcooldownAtk(7000); //c'est en ms (7000 ms = 7 s)
        this.setPv(120);
        this.setPvMax(this.getPv());
        this.setGoldDrop(40);
    }

    private void setValeurFacile(){
        this.setDmg(5);
        this.setVitesse(0.1);
        this.setcooldownAtk(8000); //c'est en ms (8000 ms = 8 s)
        this.setPv(110);
        this.setPvMax(this.getPv());
        this.setGoldDrop(50);
    }

    private void setValeurDifficile(){
        this.setDmg(15);
        this.setVitesse(0.3);
        this.setcooldownAtk(6000); //c'est en ms (6000 ms = 6 s)
        this.setPv(130);
        this.setPvMax(this.getPv());
        this.setGoldDrop(30);
    }

}
