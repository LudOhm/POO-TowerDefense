package entite.tour;

import java.awt.Toolkit;

public class Jeongyeon extends Tourelle {
    public Jeongyeon(double x, double y){
        this.setImg(Toolkit.getDefaultToolkit().getImage("ressource/tour/jeongyeon/autruche0.png"),0);
        this.setImg(Toolkit.getDefaultToolkit().getImage("ressource/tour/jeongyeon/autruche1.png"),1);
        this.setX(x);
        this.setY(y);
        this.setImgProj(Toolkit.getDefaultToolkit().getImage("ressource/tour/jeongyeon/autruche2.png"),0);
        this.setImgAffiche(this.getImg(0));
        this.setNom("jeongyeon");
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

    public Jeongyeon(){
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
        this.setDmg(40);
        this.setcooldownAtk(6000); // 6 secondes
        this.setPv(15);
        this.setPvMax(this.getPv());
        this.setVitesseAtk(0);
        this.setPrice(200);
        this.setPriceRevente((int) (super.getPrice(this)*0.8));
    }

    private void setValeurFacile(){
        this.setDmg(50);
        this.setcooldownAtk(5000); // 5 secondes
        this.setPv(25);
        this.setPvMax(this.getPv());
        this.setVitesseAtk(0);
        this.setPrice(180);
        this.setPriceRevente((int) (super.getPrice(this)*0.8));
    }

    private void setValeurDifficile(){
        this.setDmg(30);
        this.setcooldownAtk(7000); // 7 secondes
        this.setPv(10);
        this.setPvMax(this.getPv());
        this.setVitesseAtk(0);
        this.setPrice(220);
        this.setPriceRevente((int) (super.getPrice(this)*0.8));
    }

    public void attaque(){
        if(this.getLastAtk() == 0){this.setLastAtk(System.currentTimeMillis());}
        if(System.currentTimeMillis() - this.getLastAtk() >= this.getCooldownAtk()){
            projAjoute(new Projectile(getY(), getImgProj(0), this.getDmg(), 2, getVitesseAtk()));
            this.setLastAtk(System.currentTimeMillis());
        }
    }

    public void changeAffichageAtk(){
        if(this.isAtking()){
            this.setImgAffiche(this.getImg(1));
        }
        else{
            this.setImgAffiche(this.getImg(0));
        }
    }
}
