package entite.tour;

import java.awt.Toolkit;

public class Chaeyoung extends Tourelle{

    private int lastAtk = 0;
    

    public Chaeyoung(double x, double y){
        this.setImg(Toolkit.getDefaultToolkit().getImage("ressource/tour/chaeyoung/tigre0.png"),0);
        this.setImg(Toolkit.getDefaultToolkit().getImage("ressource/tour/chaeyoung/tigre1.png"),1);
        this.setX(x);
        this.setY(y);
        this.setImgProj(Toolkit.getDefaultToolkit().getImage("ressource/tour/chaeyoung/tigre2.png"),0);
        this.setImgProj(Toolkit.getDefaultToolkit().getImage("ressource/tour/chaeyoung/tigre3.png"),1);
        this.setImgAffiche(this.getImg(0));
        this.setNom("chaeyoung");
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

    public Chaeyoung(){
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
        this.setDmg(5);
        this.setcooldownAtk(2000); // 2 secondes
        this.setPv(150);
        this.setPvMax(this.getPv());
        this.setVitesseAtk(0);
        this.setPrice(250);
        this.setPriceRevente((int) (super.getPrice(this)*0.8));
    }

    private void setValeurFacile(){
        this.setDmg(15);
        this.setcooldownAtk(1000); // 1 seconde
        this.setPv(170);
        this.setPvMax(this.getPv());
        this.setVitesseAtk(0);
        this.setPrice(230);
        this.setPriceRevente((int) (super.getPrice(this)*0.8));
    }

    private void setValeurDifficile(){
        this.setDmg(5);
        this.setcooldownAtk(3000); // 3 secondes
        this.setPv(130);
        this.setPvMax(this.getPv());
        this.setVitesseAtk(0);
        this.setPrice(270);
        this.setPriceRevente((int) (super.getPrice(this)*0.8));
    }

    public void attaque(){
        if(this.getLastAtk() == 0){this.setLastAtk(System.currentTimeMillis());}
        if(System.currentTimeMillis() - this.getLastAtk() >= this.getCooldownAtk()){
            if(lastAtk == 1){
                projAjoute(new Projectile(getX()+100, getY(), getImgProj(0), this.getDmg(), 1, getVitesseAtk()));
                lastAtk = 0;
            }
            else{
                projAjoute(new Projectile(getX()+100, getY(), getImgProj(1), this.getDmg(), 1, getVitesseAtk()));
                lastAtk = 1;
            }
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
