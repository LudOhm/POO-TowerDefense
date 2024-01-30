package entite;

public abstract class Entite{ //La class entité de base (tous les personnages utiliseront cette class)
    private double x,y; //les coordonnées
    public double getX() {return x;}
    public void setX(double x) {this.x = x;}
    public double getY() {return y;}
    public void setY(double y) {this.y = y;}
    
    private double cooldownAtk; //délai entre deux attaques
    public double getCooldownAtk() {return cooldownAtk;}
    public void setcooldownAtk(double cd) {this.cooldownAtk = cd;}

    private int cdToAdd;
    public int getCdToAdd() {return cdToAdd;}
    public void setCdToAdd(int cdToAdd) {this.cdToAdd = cdToAdd;}

    private int pv; //ses points de vie
    public int getPv() {return pv;}
    public void setPv(int pv) {this.pv = pv;}

    private int pvToAdd; //pour savoir combien de pv on rajoute au zombie au fil du temps (mode infini)
    public int getPvToAdd() {return pvToAdd;}
    public void setPvToAdd(int pvToAdd) {this.pvToAdd = pvToAdd;}

    private int pvMax;
    public int getPvMax() {return pvMax;}
    public void setPvMax(int pvMax) {this.pvMax = pvMax;}

    private int dmg; //ses dégâts
    public int getDmg() {return dmg;}
    public void setDmg(int dmg) {this.dmg = dmg;}

    private int dmgToAdd; //pour savoir combien de dmg on rajoute (tourelle --> amélioration) (zombie --> fil du temps)
    public int getDmgToAdd() {return dmgToAdd;}
    public void setDmgToAdd(int dmgToAdd) {this.dmgToAdd = dmgToAdd;}

    private long lastAtk;
    public long getLastAtk() {return lastAtk;}
    public void setLastAtk(long lastAtk) {this.lastAtk = lastAtk;}

    private static int difficulte; //1 --> facile 2 --> moyen 3 --> dur
    public static int getDifficulte() {return difficulte;}
    public static void setDifficulte(int difficulte) {Entite.difficulte = difficulte;}


    public boolean estEnVie(){
        return this.pv>0;
    }

    public float getBarreVie(){
        return this.pv /(float) this.pvMax;
    }


}
