package entite.tour;

import java.awt.Toolkit;

public class Jihyo extends Tourelle {
    public Jihyo(double x, double y){
        this.setImg(Toolkit.getDefaultToolkit().getImage("ressource/tour/jihyo/hibou0.png"),0);
        this.setX(x);
        this.setY(y);
        this.setImgProj(Toolkit.getDefaultToolkit().getImage("ressource/tour/jihyo/hibou1.png"),0);
        this.setImgAffiche(this.getImg(0));
        this.setNom("jihyo");
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

    public Jihyo(){
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
        this.setDmg(25);
        this.setcooldownAtk(10000); // 10 secondes
        this.setPv(15);
        this.setPvMax(this.getPv());
        this.setVitesseAtk(0);
        this.setPrice(50);
        this.setPriceRevente((int) (super.getPrice(this)*0.8));
    }

    private void setValeurFacile(){
        this.setDmg(35);
        this.setcooldownAtk(9000); // 9 secondes
        this.setPv(25);
        this.setPvMax(this.getPv());
        this.setVitesseAtk(0);
        this.setPrice(30);
        this.setPriceRevente((int) (super.getPrice(this)*0.8));
    }

    private void setValeurDifficile(){
        this.setDmg(50);
        this.setcooldownAtk(11000); // 11 secondes
        this.setPv(10);
        this.setPvMax(this.getPv());
        this.setVitesseAtk(0);
        this.setPrice(70);
        this.setPriceRevente((int) (super.getPrice(this)*0.8));
    }

    public void attaque(){
        if(this.getLastAtk() == 0){this.setLastAtk(System.currentTimeMillis());}
        if(System.currentTimeMillis() - this.getLastAtk() >= this.getCooldownAtk()){
            projAjoute(new Projectile(getX()+20, getY()-20, getImgProj(0), this.getDmg(), 3, getVitesseAtk()));  
            this.setLastAtk(System.currentTimeMillis());
        }
    }
}
