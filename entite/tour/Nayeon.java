package entite.tour;

import java.awt.Toolkit;

public class Nayeon extends Tourelle {
    
    public Nayeon(double x, double y){
        this.setImg(Toolkit.getDefaultToolkit().getImage("ressource/tour/nayeon/lapin0.png"),0);
        this.setX(x);
        this.setY(y);
        this.setImgProj(Toolkit.getDefaultToolkit().getImage("ressource/tour/nayeon/lapin4.png"),0);
        this.setImgAffiche(this.getImg(0));
        this.setNom("nayeon");
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

    public Nayeon(){
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
        this.setDmg(20);
        this.setcooldownAtk(5000); // 5 secondes
        this.setPv(30);
        this.setPvMax(this.getPv());
        this.setVitesseAtk(2);
        this.setPrice(100);
        this.setPriceRevente((int) (super.getPrice(this)*0.8));
    }

    private void setValeurFacile(){
        this.setDmg(30);
        this.setcooldownAtk(4000); // 4 secondes
        this.setPv(40);
        this.setPvMax(this.getPv());
        this.setVitesseAtk(3);
        this.setPrice(80);
        this.setPriceRevente((int) (super.getPrice(this)*0.8));
    }

    private void setValeurDifficile(){
        this.setDmg(10);
        this.setcooldownAtk(6000); // 6 secondes
        this.setPv(20);
        this.setPvMax(this.getPv());
        this.setVitesseAtk(1);
        this.setPrice(120);
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
