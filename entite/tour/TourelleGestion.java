package entite.tour;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import entite.ennemi.Ennemi;
import main.GamePanel;
import tuile.Bouton;

public class TourelleGestion{
    private GamePanel gp;
    private Tourelle[][] tours; //la pos des tours
    private boolean[][] zoneLibre; //ça nous aidera à savoir si une case est libre ou pas
    private int gold;
    private boolean drawPrice;
    private boolean drawStat;
    private long timeGainGold;
    private ArrayList<Bouton> boutons;
    private int tutoState;
    private boolean isTuto;

    public void setIsTuto(boolean b) {isTuto = b;}
    public int getTutoState() {return tutoState;}
    public void nextTutoState() {tutoState++;}
    public void setDrawPrice(boolean b) {drawPrice = b;}
    public void setDrawStat(boolean b) {drawStat = b;}

    public TourelleGestion(GamePanel gp){
        this.gp = gp;
        this.tours = new Tourelle[5][8]; //Il y a au maximum 40 tourelles posées simultanéiment sur le terrain
        this.zoneLibre = new boolean[5][8];
        gold = 100;
        drawPrice = false;
        drawStat = false;
        boutons = new ArrayList<>();
        boutons.add(new Bouton(5, gp.getTuileSize()*6, (gp.getTuileSize()*2)-10, (gp.getTuileSize()*2)-10, "ameliorerPV"));
        boutons.add(new Bouton(5, gp.getTuileSize()*8, (gp.getTuileSize()*2)-10, (gp.getTuileSize()*2)-10, "ameliorerATK"));
        boutons.add(new Bouton(5, gp.getTuileSize()*10, (gp.getTuileSize()*2)-10, (gp.getTuileSize()*2)-10, "ameliorerCD"));
        boutons.add(new Bouton(5, gp.getTuileSize()*13, (gp.getTuileSize()*2)-10, gp.getTuileSize()-10, "vendre"));
        setZoneAllTrue();
        Projectile.setGp(gp);
        timeGainGold = System.currentTimeMillis();
    }

    public void resetGame(){
        if(gp.getGameState() == gp.getTutorielState()){tutoState = 0; isTuto = true;}
        this.tours = new Tourelle[5][8]; //Il y a au maximum 40 tourelles posées simultanéiment sur le terrain
        this.zoneLibre = new boolean[5][8];
        gold = 100;
        drawPrice = false;
        drawStat = false;
        boutons = new ArrayList<>();
        boutons.add(new Bouton(5, gp.getTuileSize()*6, (gp.getTuileSize()*2)-10, (gp.getTuileSize()*2)-10, "ameliorerPV"));
        boutons.add(new Bouton(5, gp.getTuileSize()*8, (gp.getTuileSize()*2)-10, (gp.getTuileSize()*2)-10, "ameliorerATK"));
        boutons.add(new Bouton(5, gp.getTuileSize()*10, (gp.getTuileSize()*2)-10, (gp.getTuileSize()*2)-10, "ameliorerCD"));
        boutons.add(new Bouton(5, gp.getTuileSize()*13, (gp.getTuileSize()*2)-10, gp.getTuileSize()-10, "vendre"));
        setZoneAllTrue();
        timeGainGold = System.currentTimeMillis();
    }

    private void setZoneAllTrue(){ //on met tout true car au début on a aucune tourelles <=> toutes les cases sont libres
        for(int i = 0; i < this.zoneLibre.length; i++){
            for(int j = 0; j < this.zoneLibre[i].length; j++){
                this.zoneLibre[i][j] = true;
            }
        }
    }

    public void cliqueSurUneCase(int x, int y, String note){
        switch (note) {
            case "nayeon":
                if(zoneLibre[(y/128)-1][(x/128)-1]){
                    Nayeon temp = new Nayeon(x,y);
                    poserTourelle(temp, x,y);
                    gold -= Tourelle.getPrice(temp);
                    gp.getTuileG().setMap(gp.getTuileG().getMapSansChangement());
                    drawPrice = false;
                }
                break;

            case "chaeyoung":
                if(zoneLibre[(y/128)-1][(x/128)-1]){
                    Chaeyoung temp = new Chaeyoung(x,y);
                    poserTourelle(temp, x,y);
                    gold -= Tourelle.getPrice(temp);
                    gp.getTuileG().setMap(gp.getTuileG().getMapSansChangement());
                    drawPrice = false;
                }
                break;

            case "jeongyeon":
                if(zoneLibre[(y/128)-1][(x/128)-1]){
                    Jeongyeon temp = new Jeongyeon(x,y);
                    poserTourelle(temp, x,y);
                    gold -= Tourelle.getPrice(temp);
                    gp.getTuileG().setMap(gp.getTuileG().getMapSansChangement());
                    drawPrice = false;
                }
                break;

            case "mina":
                if(zoneLibre[(y/128)-1][(x/128)-1]){
                    Mina temp = new Mina(x,y);
                    poserTourelle(temp, x,y);
                    gold -= Tourelle.getPrice(temp);
                    gp.getTuileG().setMap(gp.getTuileG().getMapSansChangement());
                    drawPrice = false;
                }
                break;
            
            case "momo":
                if(zoneLibre[(y/128)-1][(x/128)-1]){
                    Momo temp = new Momo(x,y);
                    poserTourelle(temp, x,y);
                    gold -= Tourelle.getPrice(temp);
                    gp.getTuileG().setMap(gp.getTuileG().getMapSansChangement());
                    drawPrice = false;
                }
                break;
            
            case "sana":
                if(zoneLibre[(y/128)-1][(x/128)-1]){
                    Sana temp = new Sana(x,y);
                    poserTourelle(temp, x,y);
                    gold -= Tourelle.getPrice(temp);
                    gp.getTuileG().setMap(gp.getTuileG().getMapSansChangement());
                    drawPrice = false;
                }
                break;

            case "tzuyu":
                if(zoneLibre[(y/128)-1][(x/128)-1]){
                    Tzuyu temp = new Tzuyu(x,y);
                    poserTourelle(temp, x,y);
                    gold -= Tourelle.getPrice(temp);
                    gp.getTuileG().setMap(gp.getTuileG().getMapSansChangement());
                    drawPrice = false;
                }
                break;
            
            case "dahyun":
                if(zoneLibre[(y/128)-1][(x/128)-1]){
                    Dahyun temp = new Dahyun(x,y);
                    poserTourelle(temp, x,y);
                    gold -= Tourelle.getPrice(temp);
                    gp.getTuileG().setMap(gp.getTuileG().getMapSansChangement());
                    drawPrice = false;
                }
                break;

            case "jihyo":
                if(zoneLibre[(y/128)-1][(x/128)-1]){
                    Jihyo temp = new Jihyo(x,y);
                    poserTourelle(temp, x,y);
                    gold -= Tourelle.getPrice(temp);
                    gp.getTuileG().setMap(gp.getTuileG().getMapSansChangement());
                    drawPrice = false;
                    if(gp.getGameState() == gp.getTutorielState()){if(tutoState == 1){tutoState++;}}
                }
                break;
            default:
                break;
        }
    }

    public void poserTourelle(Tourelle tour, int x, int y){
        this.tours[(y/128)-1][(x/128)-1] = tour;
        this.zoneLibre[(y/128)-1][(x/128)-1] = false;
    }

    public void enleverTourelle(int x, int y){
        this.zoneLibre[(y/128)-1][(x/128)-1] = true;
    }

    public void update(){
        if(System.currentTimeMillis() - timeGainGold >= 15000){gold += 10; timeGainGold = System.currentTimeMillis();}
            for(int i = 0; i < this.zoneLibre.length; i++){
                for(int j = 0; j < this.zoneLibre[i].length; j++){
                    if(!this.zoneLibre[i][j]){
                        if(!this.tours[i][j].estEnVie()){this.zoneLibre[i][j] = true;} //si on rentre dans ce if ==> la tourelle n'a plus d'hp donc morte
                        else{
                            switch (this.tours[i][j].getNom()) {
                                case "nayeon":
                                    this.tours[i][j].attaque();break;
                                case "chaeyoung":
                                    this.tours[i][j].changeAffichageAtk();
                                    if(ennemiAhead(this.tours[i][j])){
                                        this.tours[i][j].setAtking(true);
                                        this.tours[i][j].attaque();
                                    }
                                    else{
                                        this.tours[i][j].setAtking(false);}break;
                                case "jeongyeon":
                                    this.tours[i][j].changeAffichageAtk();
                                    if(ennemiFarAhead(this.tours[i][j])){
                                        this.tours[i][j].setAtking(true);
                                        this.tours[i][j].attaque();
                                    }
                                    else{this.tours[i][j].setAtking(false);}break;
                                case "mina":
                                    this.tours[i][j].attaque();break;
                                case "momo":
                                    this.tours[i][j].attaque();
                                    break;
                                case "sana":
                                    this.tours[i][j].attaque();break;
                                case "tzuyu":
                                    this.tours[i][j].attaque();break;
                                case "dahyun":
                                    this.tours[i][j].attaque();break;
                                case "jihyo":
                                    this.tours[i][j].attaque();break;
                                default:break;
                            }
                            
                        }
                    }
                }
        }
        
        for(Projectile p : Tourelle.getTabProj()){
            p.update();
            boolean isBreak = false;
            for(int i = 0; i < gp.getEnnemiG().getEnnemis().size(); i++){
                if(p.toucheEnnemi(gp.getEnnemiG().getEnnemi(i))){
                    p.hurtEnnemi(gp.getEnnemiG().getEnnemi(i));
                    Tourelle.projEnleve(p); isBreak = true; break;
                }
            }
            if(isBreak) {break;}
            if(p.isHaveToRemove()){
                Tourelle.projEnleve(p); 
                if(gp.getGameState() == gp.getTutorielState()){if(tutoState == 2 && gold >= 100){tutoState++;}}
                break;}
            if(!p.estSurEcran(gp.getFenetreLargeur(), gp.getFenetreHauteur())){Tourelle.projEnleve(p); break;}
        }
    }

    public void draw(Graphics2D component2d){
        for(int i = 0; i < this.zoneLibre.length; i++){
            for(int j = 0; j < this.zoneLibre[i].length; j++){
                if(!this.zoneLibre[i][j]){
                    drawTour(tours[i][j], component2d);
                    drawBarreVie(tours[i][j], component2d);
                }
            }
        }
        for(Projectile p : Tourelle.getTabProj()){
            p.draw(component2d);
        }
        this.drawGold(component2d);
        this.drawTourPrice(component2d);
        if(drawStat){this.drawTourStat(component2d,getToursCase(Bouton.getLastX(), Bouton.getLastY()));}
        if(isTuto){drawDialogue(component2d);}
    }

    private void drawTour(Tourelle t, Graphics2D component2d){
        component2d.drawImage(t.getImgAffiche(), (int) t.getX(), (int) t.getY(), gp.getTuileSize()*2, gp.getTuileSize()*2, null);
    }

    private void drawGold(Graphics2D component2d){
        component2d.setColor(Color.black);
        component2d.setFont(component2d.getFont().deriveFont(Font.PLAIN,20));
        component2d.drawString(String.valueOf(gold), 60, 105);
    }

    private void drawTourPrice(Graphics2D component2d){
        if(drawPrice){
            component2d.setColor(Color.white);
            component2d.setFont(component2d.getFont().deriveFont(Font.PLAIN,20));
            component2d.drawString(String.valueOf(Tourelle.getPrice(new Nayeon())), 20, 280);
            component2d.drawString(String.valueOf(Tourelle.getPrice(new Dahyun())), 20, 670);
            component2d.drawString(String.valueOf(Tourelle.getPrice(new Chaeyoung())), 80, 670);
            component2d.drawString(String.valueOf(Tourelle.getPrice(new Jeongyeon())), 80, 280);
            component2d.drawString(String.valueOf(Tourelle.getPrice(new Mina())), 80, 540);
            component2d.drawString(String.valueOf(Tourelle.getPrice(new Jihyo())), 20, 540);
            component2d.drawString(String.valueOf(Tourelle.getPrice(new Momo())), 20, 410);
            component2d.drawString(String.valueOf(Tourelle.getPrice(new Sana())), 80, 410);
            component2d.drawString(String.valueOf(Tourelle.getPrice(new Tzuyu())), 20, 800);
        }
    }

    private void drawTourStat(Graphics2D component2d, Tourelle t){
        component2d.setColor(Color.lightGray);
        component2d.fillRect(5, gp.getTuileSize()*3, (gp.getTuileSize()*2)-10, (gp.getTuileSize()*2)-10);
        component2d.drawImage(t.getImgAffiche(), 0, gp.getTuileSize()*3, gp.getTuileSize()*2, gp.getTuileSize()*2, null);
        Image temp = Toolkit.getDefaultToolkit().getImage("ressource/title/menubouton.png");
        //Bouton améliorer pv
        component2d.drawImage(temp, 5, gp.getTuileSize()*6, (gp.getTuileSize()*2)-10, (gp.getTuileSize()*2)-10, null);
        String s1 = "Améliorer PV ("+String.valueOf(t.getPvLevel())+"/5)";
        if(t.getAtkLevel() > 3 || t.getCdAtkLevel() > 3) {s1 ="Améliorer PV ("+String.valueOf(t.getPvLevel())+"/3)"; }
        String s1bis = "Prix : "+String.valueOf((t.getPvLevel()+1)*100);
        if(((t.getAtkLevel() > 3 || t.getCdAtkLevel() > 3) && t.getPvLevel() == 3) || t.getPvLevel() == 5){s1bis = "Niveau Max !";}
        component2d.setFont(component2d.getFont().deriveFont(Font.PLAIN,10));
        component2d.drawString(s1, gp.getTuileG().coXTexteCentre(component2d, s1, 5,(gp.getTuileSize()*2)-10), gp.getTuileG().coYTexteCentre(component2d, s1, gp.getTuileSize()*6,gp.getTuileSize()));
        component2d.setFont(component2d.getFont().deriveFont(Font.PLAIN,15));
        component2d.drawString(s1bis, gp.getTuileG().coXTexteCentre(component2d, s1bis, 5,(gp.getTuileSize()*2)-10), gp.getTuileG().coYTexteCentre(component2d, s1bis, gp.getTuileSize()*7,gp.getTuileSize()));
    
        //Bouton améliorer Atk
        component2d.drawImage(temp, 5, gp.getTuileSize()*8, (gp.getTuileSize()*2)-10, (gp.getTuileSize()*2)-10, null);
        String s2  ="Améliorer ATK ("+String.valueOf(t.getAtkLevel())+"/5)";
        if(t.getPvLevel() > 3 || t.getCdAtkLevel() > 3) {s2 ="Améliorer ATK ("+String.valueOf(t.getAtkLevel())+"/3)"; }
        String s2bis = "Prix : "+String.valueOf((t.getAtkLevel()+1)*100);
        if(((t.getPvLevel() > 3 || t.getCdAtkLevel() > 3) && t.getAtkLevel() == 3) || t.getAtkLevel() == 5){s2bis = "Niveau Max !";}
        component2d.setFont(component2d.getFont().deriveFont(Font.PLAIN,10));
        component2d.drawString(s2, gp.getTuileG().coXTexteCentre(component2d, s2, 5,(gp.getTuileSize()*2)-10), gp.getTuileG().coYTexteCentre(component2d, s2, gp.getTuileSize()*8,gp.getTuileSize()));
        component2d.setFont(component2d.getFont().deriveFont(Font.PLAIN,15));
        component2d.drawString(s2bis, gp.getTuileG().coXTexteCentre(component2d, s2bis, 5,(gp.getTuileSize()*2)-10), gp.getTuileG().coYTexteCentre(component2d, s2bis, gp.getTuileSize()*9,gp.getTuileSize()));

        //Bouton améliorer cdAtk
        component2d.drawImage(temp, 5, gp.getTuileSize()*10, (gp.getTuileSize()*2)-10, (gp.getTuileSize()*2)-10, null);
        String s3  ="Améliorer CD ("+String.valueOf(t.getCdAtkLevel())+"/5)";
        if(t.getPvLevel() > 3 || t.getAtkLevel() > 3) {s3 ="Améliorer CD ("+String.valueOf(t.getCdAtkLevel())+"/3)"; }
        String s3bis = "Prix : "+String.valueOf((t.getCdAtkLevel()+1)*100);
        if(((t.getPvLevel() > 3 || t.getAtkLevel() > 3) && t.getCdAtkLevel() == 3) || t.getCdAtkLevel() == 5){s3bis = "Niveau Max !";}
        component2d.setFont(component2d.getFont().deriveFont(Font.PLAIN,10));
        component2d.drawString(s3, gp.getTuileG().coXTexteCentre(component2d, s3, 5,(gp.getTuileSize()*2)-10), gp.getTuileG().coYTexteCentre(component2d, s3, gp.getTuileSize()*10,gp.getTuileSize()));
        component2d.setFont(component2d.getFont().deriveFont(Font.PLAIN,15));
        component2d.drawString(s3bis, gp.getTuileG().coXTexteCentre(component2d, s3bis, 5,(gp.getTuileSize()*2)-10), gp.getTuileG().coYTexteCentre(component2d, s3bis, gp.getTuileSize()*11,gp.getTuileSize()));

        //Bouton vendre
        component2d.drawImage(temp, 5, gp.getTuileSize()*13, (gp.getTuileSize()*2)-10, gp.getTuileSize()-10, null);
        String s4 = "Vendre (+"+String.valueOf(t.getPriceRevente())+")";
        component2d.setFont(component2d.getFont().deriveFont(Font.PLAIN,10));
        component2d.drawString(s4, gp.getTuileG().coXTexteCentre(component2d, s4, 5,(gp.getTuileSize()*2)-10), gp.getTuileG().coYTexteCentre(component2d, s4, gp.getTuileSize()*13,gp.getTuileSize()));

        for(Bouton b : boutons){
            if(verifPriceAmelioration(b, t) && verifLevelMax(b, t)){
                b.draw(component2d);
            }
        }
    }

    
    public boolean getZoneLibreCase(int x, int y){
        if(x < 128 || x >= 1152){return true;}
        int arrondiX = (int) Math.ceil((x/128));
        return this.zoneLibre[(y/128)-1][arrondiX-1];
    }

    public Tourelle getToursCase(int x, int y){
        int arrondiX = (int) Math.ceil((x/128));
        return this.tours[(y/128)-1][arrondiX-1];
    }

    public int getGold() {return gold;}
    public void addGold(int g) {this.gold += g;}

    public boolean ennemiAhead(Tourelle t){
        for(Ennemi e : gp.getEnnemiG().getEnnemis()){
            if(e.getX() >= t.getX() && e.getX() <= t.getX()+160){
                if(e.getY()+32 == t.getY()){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean ennemiFarAhead(Tourelle t){
        for(Ennemi e : gp.getEnnemiG().getEnnemis()){
            if(e.getY()+32 == t.getY()){
                    return true;
            }
        }
        return false;
    }

    private void drawBarreVie(Tourelle t, Graphics2D component2d){
        component2d.setColor(Color.red);
        component2d.fillRect((int) t.getX() + 32, (int) t.getY(), 50, 3);
        component2d.setColor(Color.green);
        component2d.fillRect((int) t.getX() + 32, (int) t.getY(), getBarreVieLongueur(t), 3);
    }

    private int getBarreVieLongueur(Tourelle t){
        return (int) (50 * t.getBarreVie());
    }

    public void vendreTour(Tourelle t){
        gold += t.getPriceRevente();
        int arrondiX = (int) Math.ceil((t.getX()/128));
        this.zoneLibre[((int)t.getY()/128)-1][arrondiX-1] = true;
    }

    public void ameliorerPvTour(Tourelle t){
        t.setPv(t.getPv() + t.getPvToAdd());
        t.setPvMax(t.getPvMax() + t.getPvToAdd());
        t.setPvLevel(t.getPvLevel()+1);
        gold -= t.getPvLevel()*100;
        t.setPriceRevente(t.getPriceRevente() + (int) ((t.getPvLevel()*100)*0.8));
    }

    public void ameliorerAtkTour(Tourelle t){
        t.setDmg(t.getDmg() + t.getDmgToAdd());
        t.setAtkLevel(t.getAtkLevel()+1);
        gold -= t.getAtkLevel()*100;
        t.setPriceRevente(t.getPriceRevente() + (int) ((t.getAtkLevel()*100)*0.8));
    }

    public void ameliorerCdTour(Tourelle t){
        t.setcooldownAtk(t.getCooldownAtk() - t.getCdToAdd());
        t.setCdAtkLevel(t.getCdAtkLevel()+1);
        gold -= t.getCdAtkLevel()*100;
        t.setPriceRevente(t.getPriceRevente() + (int) ((t.getCdAtkLevel()*100)*0.8));
    }

    private boolean verifLevelMax(Bouton b, Tourelle t){
        switch (b.getNote()) {
            case "ameliorerPV":
                if(t.getAtkLevel() > 3 || t.getCdAtkLevel() > 3){ //On vérifie que les autres améliorations ne sont pas au dessus de 3
                    if(t.getPvLevel() == 3){    //Seule *une* amélioration peut-être au dessus de niveau 3
                        return false;}
                    else{return true;}
                }
                else{
                    if(t.getPvLevel() == 5){return false;}
                    else{return true;}
                }
                
            case "ameliorerATK":
                if(t.getPvLevel() > 3 || t.getCdAtkLevel() > 3){
                    if(t.getAtkLevel() == 3){return false;}
                    else{return true;}
                }
                else{
                    if(t.getAtkLevel() == 5){return false;}
                    else{return true;}
                }
            case "ameliorerCD":
                if(t.getPvLevel() > 3 || t.getAtkLevel() > 3){
                    if(t.getCdAtkLevel() == 3){return false;}
                    else{return true;}
                }
                else{
                    if(t.getCdAtkLevel() == 5){return false;}
                    else{return true;}
                }
            default:
                return true;
        }
    }

    private boolean verifPriceAmelioration(Bouton b, Tourelle t){
        switch (b.getNote()) {
            case "ameliorerPV":
                if(gold < (t.getPvLevel()+1)*100){
                    return false;
                }
                else{return true;}
            case "ameliorerATK":
                if(gold < (t.getAtkLevel()+1)*100){
                    return false;
                }
                else{return true;}
            case "ameliorerCD":
                if(gold < (t.getCdAtkLevel()+1)*100){
                    return false;
                }
                else{return true;}
            default:
                return true;
        }
    }

    private void drawDialogue(Graphics2D component2d){
        component2d.setColor(Color.black);
        component2d.fillRect((gp.getTuileSize()*2)+5, 0, (gp.getTuileSize()*18)-10, gp.getTuileSize()*2);
        component2d.setColor(Color.white);
        component2d.drawRect((gp.getTuileSize()*2)+5, 0, (gp.getTuileSize()*18)-10, gp.getTuileSize()*2);
        switch (tutoState) {
            case 0:
                String s = "Bienvenu(e) à toi dans ce tutoriel de Time To Tower ! Le but de ce jeu est très simple, il suffit de ne pas laisser les ennemis passer !";
                String s2 = "Et pour t'aider à cela tu as le droit à de l'aide de précieux camarades ! Appuie sur n'importe quel case et tu pourras tous les voir !";
                component2d.setFont(component2d.getFont().deriveFont(Font.PLAIN,18));
                component2d.drawString(s, gp.getTuileG().coXTexteCentre(component2d, s, (gp.getTuileSize()*2)+5,(gp.getTuileSize()*18)-10), gp.getTuileG().coYTexteCentre(component2d, s, 0,gp.getTuileSize()));
                component2d.drawString(s2, gp.getTuileG().coXTexteCentre(component2d, s2, (gp.getTuileSize()*2)+5,(gp.getTuileSize()*18)-10), gp.getTuileG().coYTexteCentre(component2d, s2, 10,gp.getTuileSize()*2));
                break;
            case 1:
                String s3 = "Et oui, tu es très bien accompagné(e) dans cette histoire, mais attention, tu vois que certaines cases sont noircis ! Regarde ton argent !";
                String s4 = "Rien n'est gratuit dans la vie malheureusement.. Pour gagner de l'argent tu as trois choix : le premier est de contacter Jihyo l'hibou ! Essaie de cliquer sur l'hibou !";
                component2d.setFont(component2d.getFont().deriveFont(Font.PLAIN,16));
                component2d.drawString(s3, gp.getTuileG().coXTexteCentre(component2d, s3, (gp.getTuileSize()*2)+5,(gp.getTuileSize()*18)-10), gp.getTuileG().coYTexteCentre(component2d, s3, 0,gp.getTuileSize()));
                component2d.drawString(s4, gp.getTuileG().coXTexteCentre(component2d, s4, (gp.getTuileSize()*2)+5,(gp.getTuileSize()*18)-10), gp.getTuileG().coYTexteCentre(component2d, s4, 10,gp.getTuileSize()*2));
                break;
            case 2:
                String s5 = "Félicitions tu as fait apparaître une Jihyo ! Maintenant plus qu'à attendre quelques secondes et tu verras des étoiles apparaitre !";
                String s6 = "Attention, selon le niveau de difficulté de la partie, cela prendra plus ou moins de temps...";
                component2d.setFont(component2d.getFont().deriveFont(Font.PLAIN,18));
                component2d.drawString(s5, gp.getTuileG().coXTexteCentre(component2d, s5, (gp.getTuileSize()*2)+5,(gp.getTuileSize()*18)-10), gp.getTuileG().coYTexteCentre(component2d, s5, 0,gp.getTuileSize()));
                component2d.drawString(s6, gp.getTuileG().coXTexteCentre(component2d, s6, (gp.getTuileSize()*2)+5,(gp.getTuileSize()*18)-10), gp.getTuileG().coYTexteCentre(component2d, s6, 10,gp.getTuileSize()*2));
                break;
            case 3:
                String s7 = "Mais tu peux améliorer Jihyo pour qu'elle puisse produire de l'argent plus vite ! Il te suffit de cliquer sur la case d'herbe où elle est ! Tu vois alors ";
                String s8 = "apparaître 4 boutons, pour améliorer sa vitesse de production il faut cliquer sur le troisième bouton ! Attention, qu'une seule statistique des trois peut être améliorée jusqu'à 5 !";
                component2d.setFont(component2d.getFont().deriveFont(Font.PLAIN,15));
                component2d.drawString(s7, gp.getTuileG().coXTexteCentre(component2d, s7, (gp.getTuileSize()*2)+5,(gp.getTuileSize()*18)-10), gp.getTuileG().coYTexteCentre(component2d, s7, 0,gp.getTuileSize()));
                component2d.drawString(s8, gp.getTuileG().coXTexteCentre(component2d, s8, (gp.getTuileSize()*2)+5,(gp.getTuileSize()*18)-10), gp.getTuileG().coYTexteCentre(component2d, s8, 10,gp.getTuileSize()*2));
                break;
            case 4:
                String s9 = "Oh non ! Attention ! Un zombie se dirige sur la ligne du milieu ! Mets vite des troupes pour l'empêcher de passer";
                String s10 = "A part Jihyo, toutes les troupes tapent mais certaines sont plus fortes derrière que devant, réfléchis bien à ta stratégie !";
                component2d.setFont(component2d.getFont().deriveFont(Font.PLAIN,15));
                component2d.drawString(s9, gp.getTuileG().coXTexteCentre(component2d, s9, (gp.getTuileSize()*2)+5,(gp.getTuileSize()*18)-10), gp.getTuileG().coYTexteCentre(component2d, s9, 0,gp.getTuileSize()));
                component2d.drawString(s10, gp.getTuileG().coXTexteCentre(component2d, s10, (gp.getTuileSize()*2)+5,(gp.getTuileSize()*18)-10), gp.getTuileG().coYTexteCentre(component2d, s10, 10,gp.getTuileSize()*2));
                break;
            case 5:
                String s11 = "Toutes mes félicitations ! Tu as récupéré de l'argent en tuant ce monstre par ailleurs ! Enfin bref,";
                String s12 = "Tu as acquis les bases de ce jeu, je te laisse appuie sur le bouton en haut à gauche et partir de ce tutoriel ! Bon jeu !";
                component2d.setFont(component2d.getFont().deriveFont(Font.PLAIN,15));
                component2d.drawString(s11, gp.getTuileG().coXTexteCentre(component2d, s11, (gp.getTuileSize()*2)+5,(gp.getTuileSize()*18)-10), gp.getTuileG().coYTexteCentre(component2d, s11, 0,gp.getTuileSize()));
                component2d.drawString(s12, gp.getTuileG().coXTexteCentre(component2d, s12, (gp.getTuileSize()*2)+5,(gp.getTuileSize()*18)-10), gp.getTuileG().coYTexteCentre(component2d, s12, 10,gp.getTuileSize()*2));
                break;
            
            default:
                break;
        }
    }

}
