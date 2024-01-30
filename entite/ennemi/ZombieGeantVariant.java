package entite.ennemi;

import java.awt.Rectangle;
import java.awt.Toolkit;

public class ZombieGeantVariant extends Ennemi {

    public ZombieGeantVariant(double x, double y){
        this.setImg(Toolkit.getDefaultToolkit().getImage("ressource/enemy/zombieGeantVariant/zombieGeantVariant.png"),0);
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
        this.setNom("Zombie geant variant");
        this.setHitbox(new Rectangle((int)getX(),(int)getY(),128,128));
        this.setLastAtk(0);
    }

    private void setValeurParDefaut(){
        this.setDmg(50);
        this.setVitesse(0.1);
        this.setcooldownAtk(8000); //c'est en ms (8000 ms = 8 s)
        this.setPv(180);
        this.setPvMax(this.getPv());
        this.setGoldDrop(100);
    }

    private void setValeurFacile(){
        this.setDmg(45);
        this.setVitesse(0.1);
        this.setcooldownAtk(9000); //c'est en ms (9000 ms = 9 s)
        this.setPv(170);
        this.setPvMax(this.getPv());
        this.setGoldDrop(110);
    }

    private void setValeurDifficile(){
        this.setDmg(55);
        this.setVitesse(0.2);
        this.setcooldownAtk(7000); //c'est en ms (7000 ms = 7 s)
        this.setPv(190);
        this.setPvMax(this.getPv());
        this.setGoldDrop(90);
    }

}
