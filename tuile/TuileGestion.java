package tuile;

import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.Color;

import entite.tour.Chaeyoung;
import entite.tour.Dahyun;
import entite.tour.Jeongyeon;
import entite.tour.Jihyo;
import entite.tour.Mina;
import entite.tour.Momo;
import entite.tour.Nayeon;
import entite.tour.Sana;
import entite.tour.Tourelle;
import entite.tour.Tzuyu;
import main.GamePanel;

public class TuileGestion{
    private GamePanel gp;
    private Tuile[] tuile;
    private int[][] mapTuileNum;
    private ArrayList<Bouton> boutons;
    private String map;
    public String getMap() {return this.map;}
    public void setMap(String map) {this.map = map; this.chargementMap(map);}

    private String mapSansChangement;

    public String getMapSansChangement() {return mapSansChangement;}
    public void setMapSansChangement(String mapSansChangement) {this.mapSansChangement = mapSansChangement;}

    public TuileGestion(GamePanel gp){
        this.gp = gp;
        tuile = new Tuile[100]; //nombre total de sorte de tuile (pour l'instant j'ai mis 20)
        mapTuileNum = new int[gp.getNbCol()][gp.getNbLigne()]; //pour mettre le fichier .txt de la map dans ce tab
        boutons = new ArrayList<>(); //pour stocker tous les boutons
        Bouton.setGp(gp);
        getTuileImage();
    }

    public void getTuileImage(){
        tuile[0] = new Tuile();
        tuile[0].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/ciel1.png"));

        tuile[1] = new Tuile();
        tuile[1].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/ciel2.png"));

        tuile[2] = new Tuile();
        tuile[2].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/ciel3.png"));

        tuile[3] = new Tuile();
        tuile[3].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/ciel4.png"));

        tuile[4] = new Tuile();
        tuile[4].setZonePlantable(true); //les cases où on peut poser des tours
        tuile[4].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/herbe1.png"));

        tuile[5] = new Tuile();
        tuile[5].setZonePlantable(true); //les cases où on peut poser des tours
        tuile[5].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/herbe2.png"));

        tuile[6] = new Tuile();
        tuile[6].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/herbe3.png"));
        
        tuile[7] = new Tuile();
        tuile[7].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/herbe4.png"));

        tuile[8] = new Tuile();
        tuile[8].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/sol.png"));

        tuile[9] = new Tuile();
        tuile[9].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/sol2.png"));
         
        tuile[10] = new Tuile();
        tuile[10].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/sol3.png"));

        tuile[11] = new Tuile();
        tuile[11].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/sol4.png"));

        tuile[12] = new Tuile();
        tuile[12].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/barredaction.png"));

        tuile[13] = new Tuile();
        tuile[13].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/barredaction2.png"));

        tuile[14] = new Tuile();
        tuile[14].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/barredaction3.png"));

        tuile[15] = new Tuile();
        tuile[15].setZoneCliquable(true);
        tuile[15].setNote("nayeon");
        tuile[15].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/barredactionNayeon.png"));

        tuile[16] = new Tuile();
        tuile[16].setZoneCliquable(true);
        tuile[16].setNote("jeongyeon");
        tuile[16].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/barredactionJeongyeon.png"));

        tuile[17] = new Tuile();
        tuile[17].setZoneCliquable(true);
        tuile[17].setNote("momo");
        tuile[17].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/barredactionMomo.png"));

        tuile[18] = new Tuile();
        tuile[18].setZoneCliquable(true);
        tuile[18].setNote("sana");
        tuile[18].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/barredactionSana.png"));

        tuile[19] = new Tuile();
        tuile[19].setZoneCliquable(true);
        tuile[19].setNote("jihyo");
        tuile[19].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/barredactionJihyo.png"));

        tuile[20] = new Tuile();
        tuile[20].setZoneCliquable(true);
        tuile[20].setNote("mina");
        tuile[20].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/barredactionMina.png"));

        tuile[21] = new Tuile();
        tuile[21].setZoneCliquable(true);
        tuile[21].setNote("dahyun");
        tuile[21].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/barredactionDahyun.png"));

        tuile[22] = new Tuile();
        tuile[22].setZoneCliquable(true);
        tuile[22].setNote("chaeyoung");
        tuile[22].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/barredactionChaeyoung.png"));

        tuile[23] = new Tuile();
        tuile[23].setZoneCliquable(true);
        tuile[23].setNote("tzuyu");
        tuile[23].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/barredactionTzuyu.png"));

        tuile[24] = new Tuile();
        tuile[24].setZoneCliquable(true);
        tuile[24].setNote("pause");
        tuile[24].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/menuBoutonInGame.png"));

        tuile[25] = new Tuile();
        tuile[25].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/retourInGame1.png"));

        tuile[26] = new Tuile();
        tuile[26].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/retourInGame2.png"));

        tuile[27] = new Tuile();
        tuile[27].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/menuInGame1.png"));

        tuile[28] = new Tuile();
        tuile[28].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/menuInGame2.png"));

        tuile[29] = new Tuile();
        tuile[29].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/quitterInGame1.png"));

        tuile[34] = new Tuile();
        tuile[34].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/quitterInGame2.png"));

        tuile[30] = new Tuile();
        tuile[30].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/ciel6.png"));

        tuile[31] = new Tuile();
        tuile[31].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/ciel7.png"));

        tuile[32] = new Tuile();
        tuile[32].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/ciel8.png"));

        tuile[33] = new Tuile();
        tuile[33].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/ciel9.png"));

        tuile[35] = new Tuile();
        tuile[35].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/ciel10.png"));

        tuile[36] = new Tuile();
        tuile[36].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/ciel11.png"));

        tuile[37] = new Tuile();
        tuile[37].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/ciel12.png"));

        tuile[38] = new Tuile();
        tuile[38].setZonePlantable(true); //les cases où on peut poser des tours
        tuile[38].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/herbe5.png"));

        tuile[39] = new Tuile();
        tuile[39].setZonePlantable(true); //les cases où on peut poser des tours
        tuile[39].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/herbe6.png"));

        tuile[40] = new Tuile();
        tuile[40].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/herbe8.png"));

        tuile[41] = new Tuile();
        tuile[41].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/herbe9.png"));

        tuile[42] = new Tuile();
        tuile[42].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/sol5.png"));

        tuile[43] = new Tuile();
        tuile[43].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/sol6.png"));

        tuile[44] = new Tuile();
        tuile[44].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/sol7.png"));

        tuile[45] = new Tuile();
        tuile[45].setZonePlantable(true); //les cases où on peut poser des tours
        tuile[45].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/herbe10.png"));

        tuile[46] = new Tuile();
        tuile[46].setZonePlantable(true); //les cases où on peut poser des tours
        tuile[46].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/herbe12.png"));

        tuile[47] = new Tuile();
        tuile[47].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/herbe11.png"));

        tuile[48] = new Tuile();
        tuile[48].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/sol8.png"));

        tuile[49] = new Tuile();
        tuile[49].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/sol9.png"));

        tuile[50] = new Tuile();
        tuile[50].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/rejouerInGame1.png"));

        tuile[51] = new Tuile();
        tuile[51].setImg(Toolkit.getDefaultToolkit().getImage("ressource/tuile/rejouerInGame2.png"));
    }

    public void chargementMap(String map){ //méthode pour utiliser un fichier txt pour faire une carte
        try {
            InputStream is = getClass().getResourceAsStream(map);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int ligne = 0;
            while(ligne<gp.getNbLigne()){
                String ligneTXT = br.readLine();
                while(col<gp.getNbCol()){
                    String nombre[] = ligneTXT.split(" "); //mettre chaque chiffre de la ligne dans le tableau (sans compter les espaces)
                    int num = Integer.parseInt(nombre[col]); //pour transformer en int
                    mapTuileNum[col][ligne] = num; //on met le chiffre dans le tab de la map
                    col++;
                }
                col = 0;
                ligne++;
            }
            br.close();  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(){
        verifPrice();
    }

    public void draw(Graphics2D component2d){
        if(gp.getGameState() == gp.getMenuState()){drawMenuScreen(component2d, "Jouer", "Tutoriel", "Options", "Quitter");}
        if(gp.getGameState() == gp.getMenuJouerState()){drawMenuScreen(component2d, "Histoire", "Normal", "Marathon", "Retour");}
        if(gp.getGameState() == gp.getMenuMarathonState()){drawMenuScreen(component2d, "Facile", "Moyen", "Difficile", "Retour");}
        if(gp.getGameState() == gp.getMenuMapState()){drawMenuScreen(component2d, "Jardin", "Plage", "Aquatique", "Retour");}
        if(gp.getGameState() == gp.getMenuNormalState()){drawMenuScreen(component2d, "Stage 1", "Stage 2", "Stage 3", "Retour");}
        if(gp.getGameState() == gp.getMenuNormalDifficulteState()){drawMenuScreen(component2d, "Facile", "Moyen", "Difficile", "Retour");}
        if(gp.getGameState() == gp.getMenuOptionState()){drawMenuScreen(component2d, "+", String.valueOf(gp.getMusique().getVolumeMulitplicateur()), "-", "Retour");}
        if(gp.getGameState() == gp.getPlayState()){drawFond(component2d); drawBouton(component2d);}
        if(gp.getGameState() == gp.getTutorielState()){drawFond(component2d); drawBouton(component2d);}
        if(gp.getGameState() == gp.getPauseState()){drawFond(component2d);}
        if(gp.getGameState() == gp.getDefaiteState()){drawFond(component2d);}
        if(gp.getGameState() == gp.getVictoryState()){drawFond(component2d);}
    }

    private void drawFond(Graphics2D component2d){ //dans le cas où c'est une carte, on dessine tuile par tuile
        int y = 0;
        for(int ligne=0; ligne<gp.getNbLigne(); ligne++){
            int x = 0;
            for(int col = 0; col<gp.getNbCol(); col++){
                int tuileNum = mapTuileNum[col][ligne];
                component2d.drawImage(tuile[tuileNum].getImg(), x, y, gp.getTuileSize(), gp.getTuileSize(), null);
                if(tuileNum >= 15 && tuileNum <= 23){
                    if(!tuile[tuileNum].isZoneCliquable()){
                        component2d.setColor(new Color(0, 0, 0, 100));
                        component2d.fillRect(x, y, 64, 64);
                    }
                }
                x += gp.getTuileSize();
            }
                y += gp.getTuileSize();
            }
    }

    private void drawBouton(Graphics2D component2D){ //Pour dessiner les boutons, c'est-à-dire toutes les tuiles qui ont zoneCliquable en true
        int y = 0;
        int newY = 0;
        int ligne = 0;
        while(ligne<gp.getNbLigne()){
            int colonne = 0;
            int x = 0;
            while(colonne<gp.getNbCol()){
               int tuileNum = mapTuileNum[colonne][ligne];
               if(tuile[tuileNum].isZonePlantable()){
                boutons.add(new Bouton(x, y, gp.getTuileSize()*2, gp.getTuileSize()*2, true));
                boutons.get(boutons.size()-1).draw(component2D);

                x += gp.getTuileSize()*2;
                newY = y + gp.getTuileSize()*2;
                colonne++;
               }
               else if(tuile[tuileNum].isZoneCliquable()){
                boutons.add(new Bouton(x, y, gp.getTuileSize(), gp.getTuileSize(), tuile[tuileNum].getNote()));
                boutons.get(boutons.size()-1).draw(component2D);

                x += gp.getTuileSize();
                newY = y + gp.getTuileSize()*2;
               }
               else{
                   x += gp.getTuileSize();
               }
               colonne++;
            }
            if(newY!=y){y = newY; ligne++;}
            else{y+=gp.getTuileSize(); newY+=gp.getTuileSize();}
            ligne++;
        }
    }

    public void drawDefaiteScreen(Graphics2D component2d){
        for(int i = 8; i < 12; i++){
            for(int j = 5; j < 9; j++){
                component2d.drawImage(tuile[12].getImg(), gp.getTuileSize()*i, gp.getTuileSize()*j, gp.getTuileSize(), gp.getTuileSize(), null);
            }
        }
        component2d.setColor(Color.white);
        component2d.drawRect(gp.getTuileSize()*8, gp.getTuileSize()*5, gp.getTuileSize()*4, gp.getTuileSize()*4);

        //bouton rejouer
        component2d.drawImage(tuile[50].getImg(), gp.getTuileSize()*9, (int)(gp.getTuileSize()*5.5), gp.getTuileSize(), gp.getTuileSize(), null);
        component2d.drawImage(tuile[51].getImg(), gp.getTuileSize()*10,(int)(gp.getTuileSize()*5.5), gp.getTuileSize(), gp.getTuileSize(), null);
        boutons.add(new Bouton(gp.getTuileSize()*9, (int)(gp.getTuileSize()*5.5), gp.getTuileSize()*2, gp.getTuileSize(), "rejouer"));
        boutons.get(boutons.size()-1).draw(component2d);

        //bouton menu
        component2d.drawImage(tuile[27].getImg(), gp.getTuileSize()*9, (int)(gp.getTuileSize()*6.5), gp.getTuileSize(), gp.getTuileSize(), null);
        component2d.drawImage(tuile[28].getImg(), gp.getTuileSize()*10, (int)(gp.getTuileSize()*6.5), gp.getTuileSize(), gp.getTuileSize(), null);
        boutons.add(new Bouton(gp.getTuileSize()*9, (int)(gp.getTuileSize()*6.5), gp.getTuileSize()*2, gp.getTuileSize(), "menu"));
        boutons.get(boutons.size()-1).draw(component2d);

        //bouton quitter
        component2d.drawImage(tuile[29].getImg(), gp.getTuileSize()*9, (int)(gp.getTuileSize()*7.5), gp.getTuileSize(), gp.getTuileSize(), null);
        component2d.drawImage(tuile[34].getImg(), gp.getTuileSize()*10, (int)(gp.getTuileSize()*7.5), gp.getTuileSize(), gp.getTuileSize(), null);
        boutons.add(new Bouton(gp.getTuileSize()*9, (int)(gp.getTuileSize()*7.5), gp.getTuileSize()*2, gp.getTuileSize(), "quitter"));
        boutons.get(boutons.size()-1).draw(component2d);
        
    }

    public void drawVictoryScreen(Graphics2D component2d){
        for(int i = 8; i < 12; i++){
            for(int j = 5; j < 9; j++){
                component2d.drawImage(tuile[12].getImg(), gp.getTuileSize()*i, gp.getTuileSize()*j, gp.getTuileSize(), gp.getTuileSize(), null);
            }
        }
        component2d.setColor(Color.white);
        component2d.drawRect(gp.getTuileSize()*8, gp.getTuileSize()*5, gp.getTuileSize()*4, gp.getTuileSize()*4);

        //bouton rejouer
        component2d.drawImage(tuile[50].getImg(), gp.getTuileSize()*9, (int)(gp.getTuileSize()*5.5), gp.getTuileSize(), gp.getTuileSize(), null);
        component2d.drawImage(tuile[51].getImg(), gp.getTuileSize()*10,(int)(gp.getTuileSize()*5.5), gp.getTuileSize(), gp.getTuileSize(), null);
        boutons.add(new Bouton(gp.getTuileSize()*9, (int)(gp.getTuileSize()*5.5), gp.getTuileSize()*2, gp.getTuileSize(), "rejouer"));
        boutons.get(boutons.size()-1).draw(component2d);

        //bouton menu
        component2d.drawImage(tuile[27].getImg(), gp.getTuileSize()*9, (int)(gp.getTuileSize()*6.5), gp.getTuileSize(), gp.getTuileSize(), null);
        component2d.drawImage(tuile[28].getImg(), gp.getTuileSize()*10, (int)(gp.getTuileSize()*6.5), gp.getTuileSize(), gp.getTuileSize(), null);
        boutons.add(new Bouton(gp.getTuileSize()*9, (int)(gp.getTuileSize()*6.5), gp.getTuileSize()*2, gp.getTuileSize(), "menu"));
        boutons.get(boutons.size()-1).draw(component2d);

        //bouton quitter
        component2d.drawImage(tuile[29].getImg(), gp.getTuileSize()*9, (int)(gp.getTuileSize()*7.5), gp.getTuileSize(), gp.getTuileSize(), null);
        component2d.drawImage(tuile[34].getImg(), gp.getTuileSize()*10, (int)(gp.getTuileSize()*7.5), gp.getTuileSize(), gp.getTuileSize(), null);
        boutons.add(new Bouton(gp.getTuileSize()*9, (int)(gp.getTuileSize()*7.5), gp.getTuileSize()*2, gp.getTuileSize(), "quitter"));
        boutons.get(boutons.size()-1).draw(component2d);
        
    }

    public void drawPauseScreen(Graphics2D component2d){
        for(int i = 8; i < 12; i++){
            for(int j = 5; j < 9; j++){
                component2d.drawImage(tuile[12].getImg(), gp.getTuileSize()*i, gp.getTuileSize()*j, gp.getTuileSize(), gp.getTuileSize(), null);
            }
        }
        component2d.setColor(Color.white);
        component2d.drawRect(gp.getTuileSize()*8, gp.getTuileSize()*5, gp.getTuileSize()*4, gp.getTuileSize()*4);

        //bouton retour
        component2d.drawImage(tuile[25].getImg(), gp.getTuileSize()*9, (int)(gp.getTuileSize()*5.5), gp.getTuileSize(), gp.getTuileSize(), null);
        component2d.drawImage(tuile[26].getImg(), gp.getTuileSize()*10,(int)(gp.getTuileSize()*5.5), gp.getTuileSize(), gp.getTuileSize(), null);
        boutons.add(new Bouton(gp.getTuileSize()*9, (int)(gp.getTuileSize()*5.5), gp.getTuileSize()*2, gp.getTuileSize(), "retour"));
        boutons.get(boutons.size()-1).draw(component2d);

        //bouton rejouer
        component2d.drawImage(tuile[50].getImg(), gp.getTuileSize()*9, (int)(gp.getTuileSize()*6.5), gp.getTuileSize(), gp.getTuileSize(), null);
        component2d.drawImage(tuile[51].getImg(), gp.getTuileSize()*10,(int)(gp.getTuileSize()*6.5), gp.getTuileSize(), gp.getTuileSize(), null);
        boutons.add(new Bouton(gp.getTuileSize()*9, (int)(gp.getTuileSize()*6.5), gp.getTuileSize()*2, gp.getTuileSize(), "rejouer"));
        boutons.get(boutons.size()-1).draw(component2d);

        //bouton menu
        component2d.drawImage(tuile[27].getImg(), gp.getTuileSize()*9, (int)(gp.getTuileSize()*7.5), gp.getTuileSize(), gp.getTuileSize(), null);
        component2d.drawImage(tuile[28].getImg(), gp.getTuileSize()*10, (int)(gp.getTuileSize()*7.5), gp.getTuileSize(), gp.getTuileSize(), null);
        boutons.add(new Bouton(gp.getTuileSize()*9, (int)(gp.getTuileSize()*7.5), gp.getTuileSize()*2, gp.getTuileSize(), "menu"));
        boutons.get(boutons.size()-1).draw(component2d);
        
    }

    private void drawMenuScreen(Graphics2D component2d, String s1, String s2, String s3, String s4){
        //le fond
        Image temp = Toolkit.getDefaultToolkit().getImage("ressource/title/menu.png");
        component2d.drawImage(temp, 0, 0, gp.getFenetreLargeur(), gp.getFenetreHauteur(), null);

        //les boutons
        Image bouton = Toolkit.getDefaultToolkit().getImage("ressource/title/menubouton.png");
        component2d.drawImage(bouton, (int)(gp.getTuileSize()*8.5), (int)(gp.getTuileSize()*7.5), gp.getTuileSize()*4, gp.getTuileSize(), null);
        if(!s1.equals("5")){
            boutons.add(new Bouton((int)(gp.getTuileSize()*8.5), (int)(gp.getTuileSize()*7.5), gp.getTuileSize()*4, gp.getTuileSize(), s1));
            boutons.get(boutons.size()-1).draw(component2d);
        }
        component2d.drawImage(bouton, (int)(gp.getTuileSize()*8.5), gp.getTuileSize()*9, gp.getTuileSize()*4, gp.getTuileSize(), null);
        if(!s2.equals("0") && !s2.equals("1") && !s2.equals("2") && !s2.equals("3") && !s2.equals("4") && !s2.equals("5")){
            boutons.add(new Bouton((int)(gp.getTuileSize()*8.5),  gp.getTuileSize()*9, gp.getTuileSize()*4, gp.getTuileSize(), s2));
            boutons.get(boutons.size()-1).draw(component2d);
        }
        component2d.drawImage(bouton, (int)(gp.getTuileSize()*8.5), (int)(gp.getTuileSize()*10.5), gp.getTuileSize()*4, gp.getTuileSize(), null);
        if(!s2.equals("0")){
            boutons.add(new Bouton((int)(gp.getTuileSize()*8.5), (int)(gp.getTuileSize()*10.5), gp.getTuileSize()*4, gp.getTuileSize(), s3));
            boutons.get(boutons.size()-1).draw(component2d);
        }
        component2d.drawImage(bouton, (int)(gp.getTuileSize()*8.5), gp.getTuileSize()*12, gp.getTuileSize()*4, gp.getTuileSize(), null);
        boutons.add(new Bouton((int)(gp.getTuileSize()*8.5), gp.getTuileSize()*12, gp.getTuileSize()*4, gp.getTuileSize(), s4));
        boutons.get(boutons.size()-1).draw(component2d);

        //le texte dans les boutons
        component2d.setColor(Color.white);
        component2d.setFont(component2d.getFont().deriveFont(Font.PLAIN,40));
        component2d.drawString(s1, coXTexteCentre(component2d, s1, (int)(gp.getTuileSize()*8.5), 256), coYTexteCentre(component2d, s1, (int)(gp.getTuileSize()*7.5),64));
        component2d.drawString(s2, coXTexteCentre(component2d, s2, (int)(gp.getTuileSize()*8.5), 256), coYTexteCentre(component2d, s2, gp.getTuileSize()*9,64));
        component2d.drawString(s3, coXTexteCentre(component2d, s3, (int)(gp.getTuileSize()*8.5), 256), coYTexteCentre(component2d, s3, (int)(gp.getTuileSize()*10.5),64));
        component2d.drawString(s4, coXTexteCentre(component2d, s4, (int)(gp.getTuileSize()*8.5), 256), coYTexteCentre(component2d, s4, gp.getTuileSize()*12,64));
    }

    public int coXTexteCentre(Graphics2D component2d, String texte, int x, int largeur){
        FontMetrics metrics = component2d.getFontMetrics();
        int texteLongueur = metrics.stringWidth(texte);
        return (x + ((largeur - texteLongueur)/2));
    }

    public int coYTexteCentre(Graphics2D component2d, String texte, int y, int longueur){
        FontMetrics metrics = component2d.getFontMetrics();
        int texteHauteur = metrics.getHeight();
        return (y - 10 + ((longueur + texteHauteur)/2));
    }

    private void verifPrice(){
        for(int i = 15; i<24; i++){
            switch (i) {
                case 15:
                    if(gp.getTourelleG().getGold() < Tourelle.getPrice(new Nayeon())){
                        tuile[i].setZoneCliquable(false);
                    }
                    else{
                        tuile[i].setZoneCliquable(true);
                    }
                    break;
                case 16:
                    if(gp.getTourelleG().getGold() < Tourelle.getPrice(new Jeongyeon())){
                        tuile[i].setZoneCliquable(false);
                    }
                    else{
                        tuile[i].setZoneCliquable(true);
                    }
                    break;
                case 17:
                    if(gp.getTourelleG().getGold() < Tourelle.getPrice(new Momo())){
                        tuile[i].setZoneCliquable(false);
                    }
                    else{
                        tuile[i].setZoneCliquable(true);
                    }
                    break;
                case 18:
                    if(gp.getTourelleG().getGold() < Tourelle.getPrice(new Sana())){
                        tuile[i].setZoneCliquable(false);
                    }
                    else{
                        tuile[i].setZoneCliquable(true);
                    }
                    break;
                case 19:
                    if(gp.getTourelleG().getGold() < Tourelle.getPrice(new Jihyo())){
                        tuile[i].setZoneCliquable(false);
                    }
                    else{
                        tuile[i].setZoneCliquable(true);
                    }
                    break;
                case 20:
                    if(gp.getTourelleG().getGold() < Tourelle.getPrice(new Mina())){
                        tuile[i].setZoneCliquable(false);
                    }
                    else{
                        tuile[i].setZoneCliquable(true);
                    }
                    break;
                case 21:
                    if(gp.getTourelleG().getGold() < Tourelle.getPrice(new Dahyun())){
                        tuile[i].setZoneCliquable(false);
                    }
                    else{
                        tuile[i].setZoneCliquable(true);
                    }
                    break;
                case 22:
                    if(gp.getTourelleG().getGold() < Tourelle.getPrice(new Chaeyoung())){
                        tuile[i].setZoneCliquable(false);
                    }
                    else{
                        tuile[i].setZoneCliquable(true);
                    }
                    break;
                case 23:
                    if(gp.getTourelleG().getGold() < Tourelle.getPrice(new Tzuyu())){
                        tuile[i].setZoneCliquable(false);
                    }
                    else{
                        tuile[i].setZoneCliquable(true);
                    }
                    break;
                
            }
        }
    }
    
    
}
