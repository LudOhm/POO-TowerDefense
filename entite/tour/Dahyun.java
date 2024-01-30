package entite.tour;

import java.awt.Toolkit;

public class Dahyun extends Tourelle {
    public Dahyun(double x, double y){
        this.setImg(Toolkit.getDefaultToolkit().getImage("ressource/tour/dahyun/aigle0.png"),0);
        this.setX(x);
        this.setY(y);
        this.setImgProj(Toolkit.getDefaultToolkit().getImage("ressource/tour/dahyun/aigle2.png"),0);
        this.setImgAffiche(this.getImg(0));
        this.setNom("dahyun");
        this.setLastAtk(0);
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
        this.setCdToAdd((int) (getCooldownAtk()*0.1));
        this.setPvToAdd((int) (getPvMax()*0.2));
        this.setDmgToAdd((int) (getDmg()*0.2));
    }

    public Dahyun(){
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
    }

    private void setValeurParDefaut(){
        this.setDmg(15);
        this.setcooldownAtk(2000); // 2 secondes
        this.setPv(50);
        this.setPvMax(this.getPv());
        this.setVitesseAtk(8);
        this.setPrice(120);
        this.setPriceRevente((int) (super.getPrice(this)*0.8));
    }

    private void setValeurFacile(){
        this.setDmg(25);
        this.setcooldownAtk(1000); // 1 seconde
        this.setPv(60);
        this.setPvMax(this.getPv());
        this.setVitesseAtk(9);
        this.setPrice(100);
        this.setPriceRevente((int) (super.getPrice(this)*0.8));
    }

    private void setValeurDifficile(){
        this.setDmg(10);
        this.setcooldownAtk(3000); // 3 secondes
        this.setPv(40);
        this.setPvMax(this.getPv());
        this.setVitesseAtk(7);
        this.setPrice(140);
        this.setPriceRevente((int) (super.getPrice(this)*0.8));
    }

    public void attaque(){
        if(this.getLastAtk() == 0){this.setLastAtk(System.currentTimeMillis());}
        if(System.currentTimeMillis() - this.getLastAtk() >= this.getCooldownAtk()){
            projAjoute(new Projectile(getX()+80, getY()-20, getImgProj(0), this.getDmg(), 0, getVitesseAtk()));
            this.setLastAtk(System.currentTimeMillis());
        }
    }

}
