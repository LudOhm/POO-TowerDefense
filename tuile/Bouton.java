package tuile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import entite.Entite;
import main.GamePanel;

public class Bouton{
    private int x, y, largeur, hauteur;
    private static int lastX, lastY;
    private Rectangle bordure; //sert à déterminer les limites d'où est-ce que le bouton peut être appuyé
    private boolean zonePlante;
    private String note;
    private static GamePanel gp;
    private boolean isPaused = false;

    public static void setGp(GamePanel gp) {Bouton.gp = gp;}
    public boolean getZonePlante() {return this.zonePlante;}
    public String getNote() {return this.note;}
    public static int getLastX() {return lastX;}
    public static int getLastY() {return lastY;}

    public Bouton(int x, int y, int largeur, int hauteur, boolean zp){
        this.x = x;
        this.y = y;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.zonePlante = zp;
        this.bordure = new Rectangle((int) (x),(int) (y),largeur,hauteur);
    }                                

    public Bouton(int x, int y, int largeur, int hauteur, String note){
        this.x = x;
        this.y = y;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.zonePlante = false;
        this.note = note;
        this.bordure = new Rectangle((int) (x),(int) y,largeur,hauteur);
        if(note.equals("menu") || note.equals("retour") || note.equals("quitter") || note.equals("rejouer")){isPaused = true;}
    } 
    

    public void draw(Graphics2D graph2d){
        if(gp.getGameState() != gp.getPauseState()){
            //Dessiner l'intérieur
            if(bordure.contains(gp.getSourisX(), gp.getSourisY())){
                graph2d.setColor(new Color(255, 255, 255, 100)); //R:255 G:255 B:255 A:100 (A = Alpha --> "l'opacité" avec 0 le minimum et 255 max)
                if(gp.getSourisClique()){cliqueSurUneCase(this.x, this.y);}
            }
            else{
                graph2d.setColor(new Color(0, 0, 0, 0));
            }
            graph2d.fillRect(x, y, largeur, hauteur);

            //Dessiner les contours
            //graph2d.setColor(Color.black);
            //graph2d.drawRect(x, y, largeur, hauteur);
        }
        else{
            if(this.isPaused){
                if(bordure.contains(gp.getSourisX(), gp.getSourisY())){
                graph2d.setColor(new Color(255, 255, 255, 100)); //R:255 G:255 B:255 A:100 (A = Alpha --> "l'opacité" avec 0 le minimum et 255 max)
                if(gp.getSourisClique()){cliqueSurUneCase(this.x, this.y);}
                }
                else{
                    graph2d.setColor(new Color(0, 0, 0, 0));
                }
                graph2d.fillRect(x, y, largeur, hauteur);
            } 
        }
    }

    private void cliqueSurUneCase(int x, int y){
        String mapOriginal = gp.getTuileG().getMapSansChangement();
        if(this.zonePlante){
            if(gp.getTuileG().getMap().equals(mapOriginal)){ //il y a pas la barre, on l'affiche alors
                if(gp.getTourelleG().getZoneLibreCase(x,y)){
                    if(gp.getGameState() == gp.getTutorielState()){if(gp.getTourelleG().getTutoState() == 0){gp.getTourelleG().nextTutoState();}}
                    String newMap = mapOriginal.substring(0,mapOriginal.length()-4);
                    newMap += "withbarre.txt";
                    gp.getTuileG().setMap(newMap);
                    gp.getTourelleG().setDrawPrice(true);
                    lastX = x; lastY = y;
                }
                else{
                    String newMap = mapOriginal.substring(0,mapOriginal.length()-4);
                    newMap += "withbarreamelioration.txt";
                    gp.getTuileG().setMap(newMap);
                    gp.getTourelleG().setDrawStat(true);
                    lastX = x; lastY = y;
                }
            }
            else{
                gp.getTuileG().setMap(mapOriginal);
                gp.getTourelleG().setDrawPrice(false);
                gp.getTourelleG().setDrawStat(false);
            }
        }
        else{
            if(gp.getGameState() == gp.getPauseState()){
               switch (note) {
                    case "retour":
                        gp.setGameState(gp.getLastState());
                        break;

                    case "menu":
                        gp.stopMusique();
                        gp.playMusique(4);
                        gp.setGameState(gp.getMenuState());
                        break;

                    case "rejouer":
                        gp.resetGame();
                        gp.getTuileG().setMap(gp.getTuileG().getMapSansChangement());
                        gp.setGameState(gp.getPlayState());
                        break;
                }
            }
            if(gp.getGameState() == gp.getPlayState()){
                if(note.equals("pause")){gp.setLastState(gp.getGameState()); gp.setGameState(gp.getPauseState());}
                if(note.equals("vendre")){
                    gp.getTourelleG().vendreTour(gp.getTourelleG().getToursCase(lastX, lastY));
                    gp.getTuileG().setMap(mapOriginal);
                    gp.getTourelleG().setDrawStat(false);
                }
                if(note.equals("ameliorerPV")){
                    gp.getTourelleG().ameliorerPvTour(gp.getTourelleG().getToursCase(lastX, lastY));
                    gp.getTuileG().setMap(mapOriginal);
                    gp.getTourelleG().setDrawStat(false);
                }
                if(note.equals("ameliorerATK")){
                    gp.getTourelleG().ameliorerAtkTour(gp.getTourelleG().getToursCase(lastX, lastY));
                    gp.getTuileG().setMap(mapOriginal);
                    gp.getTourelleG().setDrawStat(false);
                }
                if(note.equals("ameliorerCD")){
                    gp.getTourelleG().ameliorerCdTour(gp.getTourelleG().getToursCase(lastX, lastY));
                    gp.getTuileG().setMap(mapOriginal);
                    gp.getTourelleG().setDrawStat(false);
                }
                else{gp.getTourelleG().cliqueSurUneCase(lastX, lastY, note);}
            }
            if(gp.getGameState() == gp.getTutorielState()){
                if(note.equals("pause")) {gp.getTourelleG().setIsTuto(false);gp.stopMusique();gp.playMusique(4);gp.setGameState(gp.getMenuState());}
                if(note.equals("vendre")){
                    gp.getTourelleG().vendreTour(gp.getTourelleG().getToursCase(lastX, lastY));
                    gp.getTuileG().setMap(mapOriginal);
                    gp.getTourelleG().setDrawStat(false);
                }
                if(note.equals("ameliorerPV")){
                    gp.getTourelleG().ameliorerPvTour(gp.getTourelleG().getToursCase(lastX, lastY));
                    gp.getTuileG().setMap(mapOriginal);
                    gp.getTourelleG().setDrawStat(false);
                    if(gp.getTourelleG().getTutoState()==3){gp.getTourelleG().nextTutoState();}
                }
                if(note.equals("ameliorerATK")){
                    gp.getTourelleG().ameliorerAtkTour(gp.getTourelleG().getToursCase(lastX, lastY));
                    gp.getTuileG().setMap(mapOriginal);
                    gp.getTourelleG().setDrawStat(false);
                    if(gp.getTourelleG().getTutoState()==3){gp.getTourelleG().nextTutoState();}
                }
                if(note.equals("ameliorerCD")){
                    gp.getTourelleG().ameliorerCdTour(gp.getTourelleG().getToursCase(lastX, lastY));
                    gp.getTuileG().setMap(mapOriginal);
                    gp.getTourelleG().setDrawStat(false);
                    if(gp.getTourelleG().getTutoState()==3){gp.getTourelleG().nextTutoState();}
                }
                else{gp.getTourelleG().cliqueSurUneCase(lastX, lastY, note);}
            }
            if(gp.getGameState() == gp.getDefaiteState()){
               switch (note) {
                    case "rejouer":
                        gp.resetGame();
                        gp.getTuileG().setMap(gp.getTuileG().getMapSansChangement());
                        gp.setGameState(gp.getPlayState());
                        break;

                    case "menu":
                        gp.stopMusique();
                        gp.playMusique(4);
                        gp.setGameState(gp.getMenuState());
                        break;

                    case "quitter":
                        System.exit(0);
                        break;
            
                }
            }
            if(gp.getGameState() == gp.getVictoryState()){
               switch (note) {
                    case "rejouer":
                        gp.resetGame();
                        gp.getTuileG().setMap(gp.getTuileG().getMapSansChangement());
                        gp.setGameState(gp.getPlayState());
                        break;

                    case "menu":
                        gp.stopMusique();
                        gp.playMusique(4);
                        gp.setGameState(gp.getMenuState());
                        break;

                    case "quitter":
                        System.exit(0);
                        break;
            
                }
            }
            if(gp.getGameState() == gp.getMenuState()){
                switch (note) {
                    case "Jouer":
                        gp.setGameState(gp.getMenuJouerState());
                        break;
                    case "Tutoriel":
                        gp.getTuileG().setMap("../ressource/map/map1Facile.txt");
                        gp.getTuileG().setMapSansChangement("../ressource/map/map1Facile.txt");
                        gp.stopMusique();
                        gp.playMusique(0);
                        Entite.setDifficulte(2);
                        gp.setGameState(gp.getTutorielState());
                        gp.resetGame();
                        break;
                    case "Options":
                        gp.setGameState(gp.getMenuOptionState());
                        break;
                    case "Quitter":
                        System.exit(0);
                        break;
                }
            }
            if(gp.getGameState() == gp.getMenuJouerState()){
                switch (note) {
                    case "Histoire":
                        gp.stopMusique();
                        gp.playMusique(3);
                        gp.setStoryInt(1);
                        gp.setGameState(gp.getStoryState());
                        break;
                    case "Normal":
                        gp.setGameState(gp.getMenuNormalState());
                        break;
                    case "Marathon":
                        gp.setGameState(gp.getMenuMarathonState());
                        break;
                    case "Retour":
                        gp.setGameState(gp.getMenuState());
                        break;
                }
            }
            if(gp.getGameState() == gp.getMenuOptionState()){
                switch (note) {
                    case "+":
                        gp.getMusique().addVolumeMultiplicateur();
                        break;
                    case "-":
                        gp.getMusique().removeVolumeMultiplicateur();
                        break;
                    case "Retour":
                        gp.setGameState(gp.getMenuState());
                        break;
                }
            }

            if(gp.getGameState() == gp.getMenuMarathonState()){
                switch (note) {
                    case "Facile":
                        Entite.setDifficulte(1);
                        gp.setModeDeJeu(1);
                        gp.setGameState(gp.getMenuMapState());
                        break;
                    case "Moyen":
                        Entite.setDifficulte(2);
                        gp.setModeDeJeu(1);
                        gp.setGameState(gp.getMenuMapState());
                        break;
                    case "Difficile":
                        Entite.setDifficulte(3);
                        gp.setModeDeJeu(1);
                        gp.setGameState(gp.getMenuMapState());
                        break;
                    case "Retour":
                        gp.setGameState(gp.getMenuJouerState());
                        break;
                }
            }

            if(gp.getGameState() == gp.getMenuNormalState()){
                switch (note) {
                    case "Stage 1":
                        gp.setModeDeJeu(2);
                        gp.setGameState(gp.getMenuNormalDifficulteState());
                        break;
                    case "Stage 2":
                        gp.setModeDeJeu(3);
                        gp.setGameState(gp.getMenuNormalDifficulteState());
                        break;
                    case "Stage 3":
                        gp.setModeDeJeu(4);
                        gp.setGameState(gp.getMenuNormalDifficulteState());
                        break;
                    case "Retour":
                        gp.setGameState(gp.getMenuJouerState());
                        break;
                }
            }

            if(gp.getGameState() == gp.getMenuNormalDifficulteState()){
                String mapTemp = "";
                int whichSong = 0;
                switch (note) {
                    case "Facile":
                        Entite.setDifficulte(1);
                        switch (gp.getModeDeJeu()) {
                            case 2:
                                mapTemp = "../ressource/map/map1Facile.txt";
                                whichSong = 0;
                                break;
                            case 3:
                                mapTemp = "../ressource/map/map3Facile.txt";
                                whichSong = 2;
                                break;
                            case 4:
                                mapTemp = "../ressource/map/map2Facile.txt";
                                whichSong = 1;
                                break;
                        }
                        gp.resetGame();
                        gp.getTuileG().setMap(mapTemp);
                        gp.getTuileG().setMapSansChangement(mapTemp);
                        gp.stopMusique();
                        gp.playMusique(whichSong);
                        gp.setGameState(gp.getPlayState());
                        break;
                    case "Moyen":
                        Entite.setDifficulte(2);
                        switch (gp.getModeDeJeu()) {
                            case 2:
                                mapTemp = "../ressource/map/map1Moyen.txt";
                                whichSong = 0;
                                break;
                            case 3:
                                mapTemp = "../ressource/map/map3Moyen.txt";
                                whichSong = 2;
                                break;
                            case 4:
                                mapTemp = "../ressource/map/map2Moyen.txt";
                                whichSong = 1;
                                break;
                        }
                        gp.resetGame();
                        gp.getTuileG().setMap(mapTemp);
                        gp.getTuileG().setMapSansChangement(mapTemp);
                        gp.stopMusique();
                        gp.playMusique(whichSong);
                        gp.setGameState(gp.getPlayState());
                        break;
                    case "Difficile":
                        Entite.setDifficulte(3);
                        switch (gp.getModeDeJeu()) {
                            case 2:
                                mapTemp = "../ressource/map/map1Difficile.txt";
                                whichSong = 0;
                                break;
                            case 3:
                                mapTemp = "../ressource/map/map3Difficile.txt";
                                whichSong = 2;
                                break;
                            case 4:
                                mapTemp = "../ressource/map/map2Difficile.txt";
                                whichSong = 1;
                                break;
                        }
                        gp.resetGame();
                        gp.getTuileG().setMap(mapTemp);
                        gp.getTuileG().setMapSansChangement(mapTemp);
                        gp.stopMusique();
                        gp.playMusique(whichSong);
                        gp.setGameState(gp.getPlayState());
                        break;
                    case "Retour":
                        gp.setGameState(gp.getMenuNormalState());
                        break;
                }
            }

            if(gp.getGameState() == gp.getMenuMapState()){
                String mapTemp = "";
                switch (note) {
                    case "Jardin":
                        switch (Entite.getDifficulte()) {
                            case 1:
                                mapTemp = "../ressource/map/map1Facile.txt";
                                break;
                            case 2:
                                mapTemp = "../ressource/map/map1Moyen.txt";
                                break;
                            case 3:
                                mapTemp = "../ressource/map/map1Difficile.txt";
                                break;
                        }
                        gp.resetGame();
                        gp.getTuileG().setMap(mapTemp);
                        gp.getTuileG().setMapSansChangement(mapTemp);
                        gp.stopMusique();
                        gp.playMusique(0);
                        gp.setGameState(gp.getPlayState());
                        break;
                    case "Plage":
                        switch (Entite.getDifficulte()) {
                            case 1:
                                mapTemp = "../ressource/map/map2Facile.txt";
                                break;
                            case 2:
                                mapTemp = "../ressource/map/map2Moyen.txt";
                                break;
                            case 3:
                                mapTemp = "../ressource/map/map2Difficile.txt";
                                break;
                        }
                        gp.resetGame();
                        gp.getTuileG().setMap(mapTemp);
                        gp.getTuileG().setMapSansChangement(mapTemp);
                        gp.stopMusique();
                        gp.playMusique(1);
                        gp.setGameState(gp.getPlayState());
                        break;
                    case "Aquatique":
                        switch (Entite.getDifficulte()) {
                            case 1:
                                mapTemp = "../ressource/map/map3Facile.txt";
                                break;
                            case 2:
                                mapTemp = "../ressource/map/map3Moyen.txt";
                                break;
                            case 3:
                                mapTemp = "../ressource/map/map3Difficile.txt";
                                break;
                        }
                        gp.resetGame();
                        gp.getTuileG().setMap(mapTemp);
                        gp.getTuileG().setMapSansChangement(mapTemp);
                        gp.stopMusique();
                        gp.playMusique(2);
                        gp.setGameState(gp.getPlayState());
                        break;
                    case "Retour":
                        gp.setGameState(gp.getMenuMarathonState());
                        break;
                }
            }
    
        }
    }


}
