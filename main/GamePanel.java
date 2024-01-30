package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import entite.ennemi.EnnemiGestion;
import entite.tour.TourelleGestion;
import tuile.TuileGestion;

public class GamePanel extends JPanel implements Runnable{ //GamePanel est une subclass de JPanel d'où l'utilisation de super
    
    //Paramètre de l'écran de jeu
    private final int tuileSize = 64; //on veut qu'une tuile de l'écran soit du 64px*64px
    public int getTuileSize() {return tuileSize;}

    //la largeur de la fenêtre
    private final int nbCol = 20;
    private final int fenetreLargeur = nbCol * tuileSize; //1280 px
    public int getNbCol() {return nbCol;}
    public int getFenetreLargeur() {return fenetreLargeur;}

    //la hauteur de la fenêtre
    private final int nbLigne = 14; 
    private final int fenetreHauteur = nbLigne * tuileSize; //896 px
    public int getNbLigne() {return nbLigne;}
    public int getFenetreHauteur() {return fenetreHauteur;}


    private int gameState = 0;
    public int getGameState() {return gameState;}
    public void setGameState(int i) {gameState = i;}
    private final int titleState = 0;
    public int getTitleState() {return titleState;}
    private final int playState = 1;
    public int getPlayState() {return playState;}
    private final int pauseState = 2;
    public int getPauseState() {return pauseState;}
    private final int menuState = 3;
    public int getMenuState() {return menuState;}
    private final int menuJouerState = 4;
    public int getMenuJouerState() {return menuJouerState;}
    private final int menuMarathonState = 5;
    public int getMenuMarathonState() {return menuMarathonState;}
    private final int menuMapState = 6;
    public int getMenuMapState() {return menuMapState;}
    private final int defaiteState = 7;
    public int getDefaiteState() {return defaiteState;}
    private final int victoryState = 8;
    public int getVictoryState() {return victoryState;}
    private final int menuNormalState = 9;
    public int getMenuNormalState() {return menuNormalState;}
    private final int menuNormalDifficulteState = 10;
    public int getMenuNormalDifficulteState() {return menuNormalDifficulteState;}
    private final int menuOptionState = 11;
    public int getMenuOptionState() {return menuOptionState;}
    private final int tutorielState = 12;
    public int getTutorielState() {return tutorielState;}
    private final int storyState = 13;
    public int getStoryState() {return storyState;}

    private int storyInt;
    public void setStoryInt(int storyInt) {this.storyInt = storyInt;}

    private int lastState = 0;
    public int getLastState() {return lastState;}
    public void setLastState(int lastState) {this.lastState = lastState;}

    private int modeDeJeu; //1 --> Marathon 2 --> Normale (stage 1) 3 --> Normale (stage 2) 4 --> Normale (stage 3)
    public int getModeDeJeu() {return modeDeJeu;}
    public void setModeDeJeu(int modeDeJeu) {this.modeDeJeu = modeDeJeu;}

    private Thread gameThread;

    private ControleSouris souris = new ControleSouris();
    private TuileGestion tuileG = new TuileGestion(this);
    private EnnemiGestion ennemiG = new EnnemiGestion(this);
    private TourelleGestion tourelleG = new TourelleGestion(this);

    private Sound musique = new Sound();    
    public Sound getMusique() {return musique;}

    //Paramètre des fps
    int FPS = 60; //on limite les fps à 60

    public GamePanel(){
        this.setPreferredSize(new Dimension(fenetreLargeur, fenetreHauteur));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //permet d'améliorer les performances
        this.addMouseMotionListener(souris); //permet de reconnaitre les mouvements de la souris
        this.addMouseListener(souris); //permet de reconnaitre les clics de la souris
        this.setFocusable(true); //permet au GamePanel de "focus" sur l'input
        playMusique(4);
    }


    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        //Création de la boucle de jeu par rapport au fps max (60)
        double intervale = 1000000000/FPS; //1 000 000 = 1 nanoseconde
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/intervale;
            lastTime = currentTime;
            if(delta >= 1){
                //Etape 1 : Update --> Mettre à jour les infos (comme la position des éléments)
                update();

                //Etape 2 : Draw --> Redessinner l'écran avec les nouvelles infos
                repaint();  //pour appeler paintComponent qui est une fonction built in java 

                delta--;
            }
        }
    }

    private void update(){ //On update l'écran
        switch (gameState) {
            case playState:
                tourelleG.update();
                ennemiG.update();
                tuileG.update();
                break;
            case tutorielState:
                tourelleG.update();
                ennemiG.update();
                tuileG.update();
                break;
            case titleState:
                updateTitle();
                break;
            case storyState:
                updateCutscene();
                break;
            default:
                tuileG.update();
                ennemiG.update();
                break;
        }
        
    }

    public void paintComponent(Graphics component){
        super.paintComponent(component);
        //On change le component en Graphics2d
        Graphics2D component2d = (Graphics2D)component; //class extends de la class Graphics (elle permet un meilleur contrôle sur la géométrie)

        switch (gameState) {
            case playState:
                tuileG.draw(component2d);
                tourelleG.draw(component2d);
                ennemiG.draw(component2d);
                break;
            
            case tutorielState:
                tuileG.draw(component2d);
                tourelleG.draw(component2d);
                ennemiG.draw(component2d);
                break;
            
            case pauseState:
                tuileG.draw(component2d);
                tourelleG.draw(component2d);
                ennemiG.draw(component2d);
                tuileG.drawPauseScreen(component2d);
                break;

            case defaiteState:
                tuileG.draw(component2d);
                tourelleG.draw(component2d);
                ennemiG.draw(component2d);
                tuileG.drawDefaiteScreen(component2d);
                break;

            case victoryState:
                tuileG.draw(component2d);
                tourelleG.draw(component2d);
                ennemiG.draw(component2d);
                tuileG.drawVictoryScreen(component2d);
                break;
            
            case titleState:
                drawTitle(component2d);
                break;

            case storyState:
                drawStory(component2d);
                break;

            default:
                tuileG.draw(component2d);
                break;
        }

        component2d.dispose(); //ça peut marcher sans cette ligne mais ça permet d'économiser de la mémoire
    }


    public int getSourisX(){return souris.getX();}
    public int getSourisY(){return souris.getY();}

    public boolean getSourisClique(){
        if(souris.getSourisClique()){
            souris.setSourisClique(false);
            return true;
        }
        return false;
    }

    private void drawTitle(Graphics2D component2d){
        Image temp = Toolkit.getDefaultToolkit().getImage("ressource/title/title.png");
        component2d.drawImage(temp, 0, 0, fenetreLargeur, fenetreHauteur, null);
        component2d.setColor(Color.white);
        component2d.setFont(component2d.getFont().deriveFont(Font.PLAIN,40));
        component2d.drawString("Appuyez sur votre souris pour continuer !", 330, 800);
    }

    private void drawStory(Graphics2D component2d){
        switch (storyInt) {
            case 1:
                Image cutscene1 = Toolkit.getDefaultToolkit().getImage("ressource/cutscene/cutscene1.png");
                component2d.drawImage(cutscene1, 0, 0, fenetreLargeur, fenetreHauteur, null);
                component2d.setColor(Color.white);
                String s1 = "L'an 37XX.. Découverte d'un phénomène qui changera à tout jamais le monde...";
                component2d.setFont(component2d.getFont().deriveFont(Font.PLAIN,20));
                component2d.drawString(s1, 330, 600);

                component2d.setFont(component2d.getFont().deriveFont(Font.PLAIN,10));
                component2d.drawString("(Appuyez sur votre souris pour continuer !)", 500, 800);
                break;
            case 2:
                Image cutscene2 = Toolkit.getDefaultToolkit().getImage("ressource/cutscene/cutscene2.png");
                component2d.drawImage(cutscene2, 0, 0, fenetreLargeur, fenetreHauteur, null);
                component2d.setColor(Color.white);
                String s2 = "Alors que le monde commence à être envahi par les zombies...";
                component2d.setFont(component2d.getFont().deriveFont(Font.PLAIN,20));
                component2d.drawString(s2, 330, 600);

                component2d.setFont(component2d.getFont().deriveFont(Font.PLAIN,10));
                component2d.drawString("(Appuyez sur votre souris pour continuer !)", 500, 800);
                break;
            case 3:
                Image cutscene3 = Toolkit.getDefaultToolkit().getImage("ressource/cutscene/cutscene3.png");
                component2d.drawImage(cutscene3, 0, 0, fenetreLargeur, fenetreHauteur, null);
                component2d.setColor(Color.white);
                String s3 = "Petit à petit, l'espoir disparait...";
                component2d.setFont(component2d.getFont().deriveFont(Font.PLAIN,20));
                component2d.drawString(s3, 500, 600);

                component2d.setFont(component2d.getFont().deriveFont(Font.PLAIN,10));
                component2d.drawString("(Appuyez sur votre souris pour continuer !)", 500, 800);
                break;
            case 4:
                Image cutscene4 = Toolkit.getDefaultToolkit().getImage("ressource/cutscene/cutscene4.png");
                component2d.drawImage(cutscene4, 0, 0, fenetreLargeur, fenetreHauteur, null);
                component2d.setColor(Color.white);
                String s4 = "Mais vous êtes notre dernier espoir, je vous prête mes meilleurs soldats, menez à bien cette mission et lancez vite une partie.";
                component2d.setFont(component2d.getFont().deriveFont(Font.PLAIN,20));
                component2d.drawString(s4, 100, 600);

                component2d.setFont(component2d.getFont().deriveFont(Font.PLAIN,10));
                component2d.drawString("(Appuyez sur votre souris pour continuer !)", 500, 800);
                break;
            default:
                stopMusique();
                playMusique(4);
                setGameState(getMenuState());
                break;
        }
    }

    private void updateTitle(){
        if(getSourisClique()){
            gameState = menuState;
        }
    }

    private void updateCutscene(){
        if(getSourisClique()){
            storyInt++;
        }
    }

    public void resetGame(){
        tourelleG.resetGame();
        ennemiG.resetGame();
    }

    public void playMusique(int i){
        musique.setFile(i);
        musique.play();
        musique.loop();
    }

    public void stopMusique(){
        musique.stop();
    }

    public TourelleGestion getTourelleG(){return tourelleG;}
    public EnnemiGestion getEnnemiG(){return ennemiG;}
    public TuileGestion getTuileG(){return tuileG;}

}
