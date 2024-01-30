package entite.ennemi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import main.GamePanel;

public class EnnemiGestion{ //Cette class nous permettra de nous occuper de comment un ennemi marche
    private GamePanel gp;
    private ArrayList<Ennemi> ennemis;
    public Ennemi getEnnemi(int i) {return ennemis.get(i);}
    public ArrayList<Ennemi> getEnnemis() {return ennemis;}
    private boolean isWaveFinished;
    private ArrayList<Ennemi> nextWave;
    private long timeForNewWave;
    private long timeForNewEnnemy;
    private int nbrWave;
    private int[] probaTab;
    private int timeForNewEnnemyLimit;
    private int timeForNewWaveLimit;
    private int multiplicateurStat;
    private long diffTimeNewWave;
    private long diffTimeNewEnnemy;
    private boolean spawnOnce = false;


    public EnnemiGestion(GamePanel gp){
        this.gp = gp;
        ennemis = new ArrayList<>();
        nextWave = new ArrayList<>();
        isWaveFinished = false;
        timeForNewWaveLimit = 30000;
        multiplicateurStat = 1;
        nbrWave = 0;
        probaTab = new int[] {75,80,85,90,0,0,0,0};
        //[0] = proba zombieBasique [1] = proba zombieBasiqueVariant1 (c'est pas 80 mais 80-75 donc 5%) [2] = proba zombieBasiqueVariant 2 (85-80)
        //[3] = proba zombieVariant3 [4] = proba zombieTank [5] = proba zombieSprint [6] = proba zombieGeant [7] = proba zombieGeantVariant
    }

    public void resetGame(){
        ennemis = new ArrayList<>();
        nextWave = new ArrayList<>();
        isWaveFinished = false;
        timeForNewWaveLimit = 30000;
        timeForNewEnnemyLimit = 5000;
        multiplicateurStat = 1;
        nbrWave = 0;
        probaTab = new int[] {75,80,85,90,0,0,0,0};
    }

    public void update(){
        if(gp.getGameState() == gp.getTutorielState()){
            if(gp.getTourelleG().getTutoState() == 4 && !spawnOnce){
                ennemis.add(new ZombieGeant(gp.getFenetreLargeur()-128, (64*6.5)));
                spawnOnce = true;
            }
        }
        if(gp.getGameState() == gp.getPauseState() || gp.getGameState() == gp.getVictoryState() || gp.getGameState() == gp.getDefaiteState()){
            timeForNewWave = System.currentTimeMillis() - diffTimeNewWave;
            timeForNewEnnemy = System.currentTimeMillis() - diffTimeNewEnnemy;
            }
            if(gp.getGameState() == gp.getPlayState()){
                diffTimeNewWave = System.currentTimeMillis() - timeForNewWave;
                diffTimeNewEnnemy = System.currentTimeMillis() - timeForNewEnnemy;
                if(ennemis.size() <= 0 && nextWave.size() <= 0){
                    if(!isWaveFinished){ //Si on rentre dans ce if c'est que la wave actuelle vient de se finir
                        isWaveFinished = true;
                        timeForNewWave = System.currentTimeMillis();
                        timeForNewEnnemy = System.currentTimeMillis();
                        if(gp.getModeDeJeu() == 2){if(nbrWave >= 2) {gp.setGameState(gp.getVictoryState());}}
                        if(gp.getModeDeJeu() == 3){if(nbrWave >= 2) {gp.setGameState(gp.getVictoryState());}}
                        if(gp.getModeDeJeu() == 4){if(nbrWave >= 3) {gp.setGameState(gp.getVictoryState());}}
                    }

                    if(isWaveFinished && System.currentTimeMillis() - timeForNewWave >= timeForNewWaveLimit){ //le délai entre deux waves est écoulée, on crée une nouvelle wave (15000 ms = 15 s)
                        newWave();
                        if(nbrWave%5 == 0 && timeForNewWaveLimit > 10000){timeForNewWave -= 5000;}
                        if(nbrWave < 20){if(nbrWave == 8 || nbrWave == 12 || nbrWave == 15){multiplicateurStat++;}}
                        else{if(nbrWave%5 == 0){multiplicateurStat++;}}
                        isWaveFinished = false;
                    }
                }
            }
            if(gp.getGameState() != gp.getPauseState()){
                //Dans le cas où ennemis n'est pas vide (la wave actuelle n'est pas fini donc)
                if(nextWave.size() > 0) {insertNextWave();}
                
                for(int i = 0; i < ennemis.size(); i++){
                    if(ennemis.get(i).getCrossedTheLine()){
                        if(gp.getGameState() == gp.getTutorielState()){ennemis.get(i).setX(gp.getFenetreLargeur()-128);}
                        gp.setGameState(gp.getDefaiteState());
                    }
                    if(!ennemis.get(i).estEnVie()){ //vérifie qu'il est en vie ou non
                        gp.getTourelleG().addGold(ennemis.get(i).getGoldDrop());
                        if(gp.getGameState() == gp.getTutorielState()){gp.getTourelleG().nextTutoState();}
                        ennemis.remove(i);
                    }
                    else{
                        if(gp.getTourelleG().getZoneLibreCase((int) ennemis.get(i).getX()+40, (int) ennemis.get(i).getY()-32)){ //si c'est true c'est qu'il ne croise pas d'ennemi
                            ennemis.get(i).move(); 
                        }    
                        else{
                            ennemis.get(i).attaque(gp.getTourelleG().getToursCase((int) ennemis.get(i).getX()+40, (int) ennemis.get(i).getY()-32));
                        }  
                    }
                }
            }
            
        }
        

    public void draw(Graphics2D component2d){
        if(gp.getGameState() != gp.getVictoryState() && gp.getGameState() != gp.getDefaiteState() && gp.getGameState() != gp.getTutorielState()){
            if(isWaveFinished){drawTimeLeft(component2d);}
            else{drawNbrWave(component2d);}
        }
        if(gp.getGameState() == gp.getVictoryState()){drawVictory(component2d);}
        if(gp.getGameState() == gp.getDefaiteState()){drawDefaite(component2d);}
        for(int i = 0; i < ennemis.size(); i++){
            drawEnemy(ennemis.get(i), component2d);    
            drawBarreVie(ennemis.get(i), component2d); 
        }
    }

    private void drawEnemy(Ennemi e, Graphics2D component2d){
        component2d.drawImage(e.getImg(0), (int) e.getX(), (int) e.getY(), gp.getTuileSize()*2, gp.getTuileSize()*2, null);
    }

    private void drawBarreVie(Ennemi e, Graphics2D component2d){
        component2d.setColor(Color.red);
        component2d.fillRect((int) e.getX() + 32, (int) e.getY(), 50, 3);
        component2d.setColor(Color.green);
        component2d.fillRect((int) e.getX() + 32, (int) e.getY(), getBarreVieLongueur(e), 3);
    }

    private int getBarreVieLongueur(Ennemi e){
        return (int) (50 * e.getBarreVie());
    }

    private void newWave(){
        if(gp.getModeDeJeu() == 1){
            updateProbaTab();
            Random r = new Random();
            int nbrEnnemi = r.nextInt(11)+10; //renvoie un nombre entre 10 et 20 inclus 
            for(int i = 0; i<nbrEnnemi; i++){ //on rajoute les ennemis dans le next wave
                int probaEnnemi = r.nextInt(100)+1; //renvoie un nombre entre 1 et 100 inclus
                if(probaEnnemi > 90 && probaEnnemi <= 95){ //il s'agit de la probabilité de zombieBasiqueArgent
                    nextWave.add(new ZombieBasiqueArgent(gp.getFenetreLargeur()-128, quelleLigne()));
                }
                else if(probaEnnemi > 95 && probaEnnemi <= 100){    //probabilité zombieTankArgent
                    nextWave.add(new ZombieTankArgent(gp.getFenetreLargeur()-128, quelleLigne()));
                }
                else{
                    int min = 0;
                    int caseZombieFound = 0;
                    for(int j = 0; j<probaTab.length; j++){
                        if(probaEnnemi > min && probaEnnemi <= probaTab[j]){
                            caseZombieFound = j;
                            break;
                        }
                        else{
                            min = probaTab[j];
                        }
                    }
                    switch (caseZombieFound) {
                        case 0:
                            nextWave.add(new ZombieBasique(gp.getFenetreLargeur()-128, quelleLigne()));
                            break;
                        case 1:
                            nextWave.add(new ZombieBasiqueVariant1(gp.getFenetreLargeur()-128, quelleLigne()));
                            break;
                        case 2:
                            nextWave.add(new ZombieBasiqueVariant2(gp.getFenetreLargeur()-128, quelleLigne()));
                            break;
                        case 3:
                            nextWave.add(new ZombieBasiqueVariant3(gp.getFenetreLargeur()-128, quelleLigne()));
                            break;
                        case 4:
                            nextWave.add(new ZombieTank(gp.getFenetreLargeur()-128, quelleLigne()));
                            break;
                        case 5:
                            nextWave.add(new ZombieSprint(gp.getFenetreLargeur()-128, quelleLigne()));
                            break;
                        case 6:
                            nextWave.add(new ZombieGeant(gp.getFenetreLargeur()-128, quelleLigne()));
                            break;
                        case 7:
                            nextWave.add(new ZombieGeantVariant(gp.getFenetreLargeur()-128, quelleLigne()));
                            break;
                    } 
                } 
            }
            nbrWave++;
        }
        else{ //On est en mode Normal, les waves sont prédéfini
            nbrWave++;
            switch (gp.getModeDeJeu()) {
                case 2: //stage 1
                    switch (nbrWave) {
                        case 1:
                            nextWave.add(new ZombieBasique(gp.getFenetreLargeur()-128, (64*2.5)));
                            nextWave.add(new ZombieBasique(gp.getFenetreLargeur()-128, (64*4.5)));
                            nextWave.add(new ZombieBasique(gp.getFenetreLargeur()-128, (64*6.5)));
                            nextWave.add(new ZombieBasique(gp.getFenetreLargeur()-128, (64*8.5)));
                            nextWave.add(new ZombieBasique(gp.getFenetreLargeur()-128, (64*10.5)));
                            nextWave.add(new ZombieBasiqueVariant1(gp.getFenetreLargeur()-128, (64*2.5)));
                            nextWave.add(new ZombieBasiqueVariant1(gp.getFenetreLargeur()-128, (64*4.5)));
                            nextWave.add(new ZombieBasiqueVariant1(gp.getFenetreLargeur()-128, (64*6.5)));
                            nextWave.add(new ZombieBasiqueVariant1(gp.getFenetreLargeur()-128, (64*8.5)));
                            nextWave.add(new ZombieBasiqueVariant1(gp.getFenetreLargeur()-128, (64*10.5)));
                            nextWave.add(new ZombieBasiqueVariant2(gp.getFenetreLargeur()-128, (64*2.5)));
                            nextWave.add(new ZombieBasiqueVariant2(gp.getFenetreLargeur()-128, (64*4.5)));
                            nextWave.add(new ZombieBasiqueVariant2(gp.getFenetreLargeur()-128, (64*6.5)));
                            nextWave.add(new ZombieBasiqueVariant2(gp.getFenetreLargeur()-128, (64*8.5)));
                            nextWave.add(new ZombieBasiqueVariant2(gp.getFenetreLargeur()-128, (64*10.5)));
                            nextWave.add(new ZombieBasiqueVariant3(gp.getFenetreLargeur()-128, (64*2.5)));
                            nextWave.add(new ZombieBasiqueVariant3(gp.getFenetreLargeur()-128, (64*4.5)));
                            nextWave.add(new ZombieBasiqueVariant3(gp.getFenetreLargeur()-128, (64*6.5)));
                            nextWave.add(new ZombieBasiqueVariant3(gp.getFenetreLargeur()-128, (64*8.5)));
                            nextWave.add(new ZombieBasiqueVariant3(gp.getFenetreLargeur()-128, (64*10.5)));
                            break;
                    
                        case 2:
                            nextWave.add(new ZombieBasiqueVariant3(gp.getFenetreLargeur()-128, (64*10.5)));
                            nextWave.add(new ZombieBasiqueVariant3(gp.getFenetreLargeur()-128, (64*8.5)));
                            nextWave.add(new ZombieBasiqueVariant3(gp.getFenetreLargeur()-128, (64*6.5)));
                            nextWave.add(new ZombieBasiqueVariant3(gp.getFenetreLargeur()-128, (64*4.5)));
                            nextWave.add(new ZombieBasiqueVariant3(gp.getFenetreLargeur()-128, (64*2.5)));
                            nextWave.add(new ZombieBasiqueVariant2(gp.getFenetreLargeur()-128, (64*10.5)));
                            nextWave.add(new ZombieBasiqueVariant2(gp.getFenetreLargeur()-128, (64*8.5)));
                            nextWave.add(new ZombieBasiqueVariant2(gp.getFenetreLargeur()-128, (64*6.5)));
                            nextWave.add(new ZombieBasiqueVariant2(gp.getFenetreLargeur()-128, (64*4.5)));
                            nextWave.add(new ZombieBasiqueVariant2(gp.getFenetreLargeur()-128, (64*2.5)));
                            nextWave.add(new ZombieBasiqueVariant1(gp.getFenetreLargeur()-128, (64*10.5)));
                            nextWave.add(new ZombieBasiqueVariant1(gp.getFenetreLargeur()-128, (64*8.5)));
                            nextWave.add(new ZombieBasiqueVariant1(gp.getFenetreLargeur()-128, (64*6.5)));
                            nextWave.add(new ZombieBasiqueVariant1(gp.getFenetreLargeur()-128, (64*4.5)));
                            nextWave.add(new ZombieBasiqueVariant1(gp.getFenetreLargeur()-128, (64*2.5)));
                            nextWave.add(new ZombieBasique(gp.getFenetreLargeur()-128, (64*10.5)));
                            nextWave.add(new ZombieBasique(gp.getFenetreLargeur()-128, (64*8.5)));
                            nextWave.add(new ZombieBasique(gp.getFenetreLargeur()-128, (64*6.5)));
                            nextWave.add(new ZombieBasique(gp.getFenetreLargeur()-128, (64*4.5)));
                            nextWave.add(new ZombieBasique(gp.getFenetreLargeur()-128, (64*2.5)));
                            break;
                    }
                    break;
                case 3: //stage 2
                    switch (nbrWave) {
                        case 1:
                            nextWave.add(new ZombieTank(gp.getFenetreLargeur()-128, (64*2.5)));
                            nextWave.add(new ZombieTank(gp.getFenetreLargeur()-128, (64*2.5)));
                            nextWave.add(new ZombieTank(gp.getFenetreLargeur()-128, (64*4.5)));
                            nextWave.add(new ZombieTank(gp.getFenetreLargeur()-128, (64*4.5)));
                            nextWave.add(new ZombieTank(gp.getFenetreLargeur()-128, (64*6.5)));
                            nextWave.add(new ZombieTank(gp.getFenetreLargeur()-128, (64*6.5)));
                            nextWave.add(new ZombieTank(gp.getFenetreLargeur()-128, (64*8.5)));
                            nextWave.add(new ZombieTank(gp.getFenetreLargeur()-128, (64*8.5)));
                            nextWave.add(new ZombieTank(gp.getFenetreLargeur()-128, (64*10.5)));
                            nextWave.add(new ZombieTank(gp.getFenetreLargeur()-128, (64*10.5)));
                            nextWave.add(new ZombieTank(gp.getFenetreLargeur()-128, (64*10.5)));
                            nextWave.add(new ZombieTank(gp.getFenetreLargeur()-128, (64*10.5)));
                            nextWave.add(new ZombieTank(gp.getFenetreLargeur()-128, (64*8.5)));
                            nextWave.add(new ZombieTank(gp.getFenetreLargeur()-128, (64*8.5)));
                            nextWave.add(new ZombieTank(gp.getFenetreLargeur()-128, (64*6.5)));
                            nextWave.add(new ZombieTank(gp.getFenetreLargeur()-128, (64*6.5)));
                            nextWave.add(new ZombieTank(gp.getFenetreLargeur()-128, (64*4.5)));
                            nextWave.add(new ZombieTank(gp.getFenetreLargeur()-128, (64*4.5)));
                            nextWave.add(new ZombieTank(gp.getFenetreLargeur()-128, (64*2.5)));
                            nextWave.add(new ZombieTank(gp.getFenetreLargeur()-128, (64*2.5)));
                            break;
                    
                        case 2:
                            nextWave.add(new ZombieSprint(gp.getFenetreLargeur()-128, (64*2.5)));
                            nextWave.add(new ZombieSprint(gp.getFenetreLargeur()-128, (64*2.5)));
                            nextWave.add(new ZombieSprint(gp.getFenetreLargeur()-128, (64*4.5)));
                            nextWave.add(new ZombieSprint(gp.getFenetreLargeur()-128, (64*4.5)));
                            nextWave.add(new ZombieSprint(gp.getFenetreLargeur()-128, (64*6.5)));
                            nextWave.add(new ZombieSprint(gp.getFenetreLargeur()-128, (64*6.5)));
                            nextWave.add(new ZombieSprint(gp.getFenetreLargeur()-128, (64*8.5)));
                            nextWave.add(new ZombieSprint(gp.getFenetreLargeur()-128, (64*8.5)));
                            nextWave.add(new ZombieSprint(gp.getFenetreLargeur()-128, (64*10.5)));
                            nextWave.add(new ZombieSprint(gp.getFenetreLargeur()-128, (64*10.5)));
                            nextWave.add(new ZombieSprint(gp.getFenetreLargeur()-128, (64*10.5)));
                            nextWave.add(new ZombieSprint(gp.getFenetreLargeur()-128, (64*10.5)));
                            nextWave.add(new ZombieSprint(gp.getFenetreLargeur()-128, (64*8.5)));
                            nextWave.add(new ZombieSprint(gp.getFenetreLargeur()-128, (64*8.5)));
                            nextWave.add(new ZombieSprint(gp.getFenetreLargeur()-128, (64*6.5)));
                            nextWave.add(new ZombieSprint(gp.getFenetreLargeur()-128, (64*6.5)));
                            nextWave.add(new ZombieSprint(gp.getFenetreLargeur()-128, (64*4.5)));
                            nextWave.add(new ZombieSprint(gp.getFenetreLargeur()-128, (64*4.5)));
                            nextWave.add(new ZombieSprint(gp.getFenetreLargeur()-128, (64*2.5)));
                            nextWave.add(new ZombieSprint(gp.getFenetreLargeur()-128, (64*2.5)));
                            break;
                    }
                    break;
                case 4: //stage 3
                    switch (nbrWave) {
                        case 1:
                            nextWave.add(new ZombieBasiqueArgent(gp.getFenetreLargeur()-128, (64*2.5)));
                            nextWave.add(new ZombieBasiqueArgent(gp.getFenetreLargeur()-128, (64*4.5)));
                            nextWave.add(new ZombieBasiqueArgent(gp.getFenetreLargeur()-128, (64*6.5)));
                            nextWave.add(new ZombieBasiqueArgent(gp.getFenetreLargeur()-128, (64*8.5)));
                            nextWave.add(new ZombieBasiqueArgent(gp.getFenetreLargeur()-128, (64*10.5)));
                            nextWave.add(new ZombieBasiqueArgent(gp.getFenetreLargeur()-128, (64*2.5)));
                            nextWave.add(new ZombieBasiqueArgent(gp.getFenetreLargeur()-128, (64*4.5)));
                            nextWave.add(new ZombieBasiqueArgent(gp.getFenetreLargeur()-128, (64*6.5)));
                            nextWave.add(new ZombieBasiqueArgent(gp.getFenetreLargeur()-128, (64*8.5)));
                            nextWave.add(new ZombieBasiqueArgent(gp.getFenetreLargeur()-128, (64*10.5)));
                            nextWave.add(new ZombieBasiqueArgent(gp.getFenetreLargeur()-128, (64*2.5)));
                            nextWave.add(new ZombieBasiqueArgent(gp.getFenetreLargeur()-128, (64*4.5)));
                            nextWave.add(new ZombieBasiqueArgent(gp.getFenetreLargeur()-128, (64*6.5)));
                            nextWave.add(new ZombieBasiqueArgent(gp.getFenetreLargeur()-128, (64*8.5)));
                            nextWave.add(new ZombieBasiqueArgent(gp.getFenetreLargeur()-128, (64*10.5)));
                            nextWave.add(new ZombieBasiqueArgent(gp.getFenetreLargeur()-128, (64*2.5)));
                            nextWave.add(new ZombieBasiqueArgent(gp.getFenetreLargeur()-128, (64*4.5)));
                            nextWave.add(new ZombieBasiqueArgent(gp.getFenetreLargeur()-128, (64*6.5)));
                            nextWave.add(new ZombieBasiqueArgent(gp.getFenetreLargeur()-128, (64*8.5)));
                            nextWave.add(new ZombieBasiqueArgent(gp.getFenetreLargeur()-128, (64*10.5)));
                            break;
                        case 2:
                            nextWave.add(new ZombieGeant(gp.getFenetreLargeur()-128, (64*2.5)));
                            nextWave.add(new ZombieGeant(gp.getFenetreLargeur()-128, (64*4.5)));
                            nextWave.add(new ZombieGeant(gp.getFenetreLargeur()-128, (64*6.5)));
                            nextWave.add(new ZombieGeant(gp.getFenetreLargeur()-128, (64*8.5)));
                            nextWave.add(new ZombieGeant(gp.getFenetreLargeur()-128, (64*10.5)));
                            nextWave.add(new ZombieGeant(gp.getFenetreLargeur()-128, (64*2.5)));
                            nextWave.add(new ZombieGeant(gp.getFenetreLargeur()-128, (64*4.5)));
                            nextWave.add(new ZombieGeant(gp.getFenetreLargeur()-128, (64*6.5)));
                            nextWave.add(new ZombieGeant(gp.getFenetreLargeur()-128, (64*8.5)));
                            nextWave.add(new ZombieGeant(gp.getFenetreLargeur()-128, (64*10.5)));
                            nextWave.add(new ZombieGeant(gp.getFenetreLargeur()-128, (64*2.5)));
                            nextWave.add(new ZombieGeant(gp.getFenetreLargeur()-128, (64*4.5)));
                            nextWave.add(new ZombieGeant(gp.getFenetreLargeur()-128, (64*6.5)));
                            nextWave.add(new ZombieGeant(gp.getFenetreLargeur()-128, (64*8.5)));
                            nextWave.add(new ZombieGeant(gp.getFenetreLargeur()-128, (64*10.5)));
                            nextWave.add(new ZombieGeant(gp.getFenetreLargeur()-128, (64*2.5)));
                            nextWave.add(new ZombieGeant(gp.getFenetreLargeur()-128, (64*4.5)));
                            nextWave.add(new ZombieGeant(gp.getFenetreLargeur()-128, (64*6.5)));
                            nextWave.add(new ZombieGeant(gp.getFenetreLargeur()-128, (64*8.5)));
                            nextWave.add(new ZombieGeant(gp.getFenetreLargeur()-128, (64*10.5)));
                            break;
                        case 3:
                            nextWave.add(new ZombieGeantVariant(gp.getFenetreLargeur()-128, (64*2.5)));
                            nextWave.add(new ZombieGeantVariant(gp.getFenetreLargeur()-128, (64*4.5)));
                            nextWave.add(new ZombieGeantVariant(gp.getFenetreLargeur()-128, (64*6.5)));
                            nextWave.add(new ZombieGeantVariant(gp.getFenetreLargeur()-128, (64*8.5)));
                            nextWave.add(new ZombieGeantVariant(gp.getFenetreLargeur()-128, (64*10.5)));
                            nextWave.add(new ZombieGeantVariant(gp.getFenetreLargeur()-128, (64*2.5)));
                            nextWave.add(new ZombieGeantVariant(gp.getFenetreLargeur()-128, (64*4.5)));
                            nextWave.add(new ZombieGeantVariant(gp.getFenetreLargeur()-128, (64*6.5)));
                            nextWave.add(new ZombieGeantVariant(gp.getFenetreLargeur()-128, (64*8.5)));
                            nextWave.add(new ZombieGeantVariant(gp.getFenetreLargeur()-128, (64*10.5)));
                            nextWave.add(new ZombieGeantVariant(gp.getFenetreLargeur()-128, (64*2.5)));
                            nextWave.add(new ZombieGeantVariant(gp.getFenetreLargeur()-128, (64*4.5)));
                            nextWave.add(new ZombieGeantVariant(gp.getFenetreLargeur()-128, (64*6.5)));
                            nextWave.add(new ZombieGeantVariant(gp.getFenetreLargeur()-128, (64*8.5)));
                            nextWave.add(new ZombieGeantVariant(gp.getFenetreLargeur()-128, (64*10.5)));
                            nextWave.add(new ZombieGeantVariant(gp.getFenetreLargeur()-128, (64*2.5)));
                            nextWave.add(new ZombieGeantVariant(gp.getFenetreLargeur()-128, (64*4.5)));
                            nextWave.add(new ZombieGeantVariant(gp.getFenetreLargeur()-128, (64*6.5)));
                            nextWave.add(new ZombieGeantVariant(gp.getFenetreLargeur()-128, (64*8.5)));
                            nextWave.add(new ZombieGeantVariant(gp.getFenetreLargeur()-128, (64*10.5)));
                            break;
                    }
                    break;
            }
        }
        
    }

    private void updateProbaTab(){
        if(nbrWave<=20){ //à partir de la wave 20 les probas ne changent plus
            switch (nbrWave) {
                case 2:
                    probaTab[0] = 60;
                    probaTab[1] = 70;
                    probaTab[2] = 80;
                    probaTab[3] = 90;
                    break;
                
                case 4: 
                    probaTab[0] = 50; //50%
                    probaTab[1] = 60; //10%
                    probaTab[2] = 70;
                    probaTab[3] = 80;
                    probaTab[4] = 90; //à partir de la wave 4 il est possible de croiser des zombieTank
                    break;
                case 6:
                    probaTab[0] = 40;
                    probaTab[1] = 50;
                    probaTab[2] = 60;
                    probaTab[3] = 70;
                    probaTab[4] = 80;
                    probaTab[5] = 90; //à partir de la wave 6 il est possible de croiser des zombieSprint
                    break;
                case 8:
                    probaTab[0] = 20;
                    probaTab[1] = 30;
                    probaTab[2] = 40;
                    probaTab[3] = 50;
                    probaTab[4] = 70; //20% de chance d'avoir zombieTank
                    probaTab[5] = 90;   //20% zombieSprint
                    break;
                case 10:
                    probaTab[0] = 5;  //5%
                    probaTab[1] = 15; //10%
                    probaTab[2] = 25; //10%
                    probaTab[3] = 35; //10%
                    probaTab[4] = 60; //25%
                    probaTab[5] = 85; //25%
                    probaTab[6] = 90; //5% d'avoir zombie Geant  
                    break;
                case 15:
                    probaTab[0] = 0;  //0%
                    probaTab[1] = 10; //10%
                    probaTab[2] = 20; //10%
                    probaTab[3] = 30; //10%
                    probaTab[4] = 55; //25%
                    probaTab[5] = 80; //25%
                    probaTab[6] = 85; //5%
                    probaTab[7] = 90; //5% zombieGeantVariant
                    break;
                case 20:
                    probaTab[1] = 10; //10%
                    probaTab[2] = 20; //10%
                    probaTab[3] = 30; //10%
                    probaTab[4] = 40; //10%
                    probaTab[5] = 50; //10%
                    probaTab[6] = 70; //20%
                    probaTab[7] = 90; //20% 
                    break;
                default:
                    break;
            }
        }
    }

    private double quelleLigne(){
        Random r = new Random();
        int ligne = r.nextInt(5);
        switch (ligne) {
            case 0:
                return (64*2.5);

            case 1:
                return (64*4.5);

            case 2:
                return (64*6.5);

            case 3:
                return (64*8.5);
            
            case 4:
                return (64*10.5);
                
            default:
                return (64*2.5);
        }
    }

    private void insertNextWave(){
        if(nbrWave%5 == 0 && timeForNewEnnemyLimit > 1000) {timeForNewEnnemyLimit -= 1000;}
        if(System.currentTimeMillis() - timeForNewEnnemy >= timeForNewEnnemyLimit){
            addStat(nextWave.get(0));
            ennemis.add(nextWave.get(0));
            nextWave.remove(0);
            timeForNewEnnemy = System.currentTimeMillis();
        }
    }

    private void addStat(Ennemi e){
        if(nbrWave < 20){
            if(nbrWave >= 4 && nbrWave < 8){//dans la wave 4, seul les zombie basique (et variant) ont leur stats améliorées
                if(!e.getNom().equals("Zombie tank") && !e.getNom().equals("Zombie tank argent") && !e.getNom().equals("Zombie sprint") && !e.getNom().equals("Zombie geant") && !e.getNom().equals("Zombie geant variant")){
                    e.ameliorerStat(multiplicateurStat);
                }
            }
            if(nbrWave >= 8 && nbrWave < 12){//les tanks sont améliorés aussi
                if(!e.getNom().equals("Zombie sprint") && !e.getNom().equals("Zombie geant") && !e.getNom().equals("Zombie geant variant")){
                    if(e.getNom().equals("Zombie tank") || e.getNom().equals("Zombie tank argent")){e.ameliorerStat(multiplicateurStat-1);}
                    else{e.ameliorerStat(multiplicateurStat);}
                }
            }
            if(nbrWave >= 12 && nbrWave < 15){//les sprints aussi
                if(!e.getNom().equals("Zombie geant") && !e.getNom().equals("Zombie geant variant")){
                    if(e.getNom().equals("Zombie sprint")){e.ameliorerStat(multiplicateurStat-2);}
                    if(e.getNom().equals("Zombie tank") || e.getNom().equals("Zombie tank argent")){e.ameliorerStat(multiplicateurStat-1);}
                    else{e.ameliorerStat(multiplicateurStat);}
                }
            }
            if(nbrWave >= 15){ //les geants aussi
                if(!e.getNom().equals("Zombie geant variant")){
                    if(e.getNom().equals("Zombie geant")){e.ameliorerStat(multiplicateurStat-3);}
                    if(e.getNom().equals("Zombie sprint")){e.ameliorerStat(multiplicateurStat-2);}
                    if(e.getNom().equals("Zombie tank") || e.getNom().equals("Zombie tank argent")){e.ameliorerStat(multiplicateurStat-1);}
                    else{e.ameliorerStat(multiplicateurStat);}
                }
            }
        }
        else{
            if(e.getNom().equals("Zombie geant variant")){e.ameliorerStat(multiplicateurStat-4);}
            if(e.getNom().equals("Zombie geant")){e.ameliorerStat(multiplicateurStat-3);}
            if(e.getNom().equals("Zombie sprint")){e.ameliorerStat(multiplicateurStat-2);}
            if(e.getNom().equals("Zombie tank") || e.getNom().equals("Zombie tank argent")){e.ameliorerStat(multiplicateurStat-1);}
            else{e.ameliorerStat(multiplicateurStat);}
        }
    }

    private void drawTimeLeft(Graphics2D component2d){
        component2d.setColor(Color.white);
        component2d.setFont(component2d.getFont().deriveFont(Font.PLAIN,40));
        String s = String.valueOf((timeForNewWaveLimit - (System.currentTimeMillis() - timeForNewWave))/1000) + "s";
        component2d.drawString(s, gp.getTuileG().coXTexteCentre(component2d, s, gp.getTuileSize()*9, 256), gp.getTuileG().coYTexteCentre(component2d, s, 0,64));
    }

    private void drawNbrWave(Graphics2D component2d){
        component2d.setColor(Color.white);
        component2d.setFont(component2d.getFont().deriveFont(Font.PLAIN,30));
        String s = "Wave " + String.valueOf(nbrWave);
        component2d.drawString(s, gp.getTuileG().coXTexteCentre(component2d, s, gp.getTuileSize()*9,256), gp.getTuileG().coYTexteCentre(component2d, s, 0,64));
    }

    private void drawVictory(Graphics2D component2d){
        component2d.setColor(Color.white);
        component2d.setFont(component2d.getFont().deriveFont(Font.PLAIN,30));
        String s = "Victoire !";
        component2d.drawString(s, gp.getTuileG().coXTexteCentre(component2d, s, gp.getTuileSize()*9,256), gp.getTuileG().coYTexteCentre(component2d, s, 0,64));
    }

    private void drawDefaite(Graphics2D component2d){
        component2d.setColor(Color.white);
        component2d.setFont(component2d.getFont().deriveFont(Font.PLAIN,30));
        String s = "Défaite !";
        component2d.drawString(s, gp.getTuileG().coXTexteCentre(component2d, s, gp.getTuileSize()*9,256), gp.getTuileG().coYTexteCentre(component2d, s, 0,64));
    }
    

}
