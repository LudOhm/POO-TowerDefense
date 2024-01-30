package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
    private Clip clip;
    private URL[] soundTab = new URL[10];
    private FloatControl fc;
    private float volume = -30f;
    private int volumeMulitplicateur = 1;
    public int getVolumeMulitplicateur() {return volumeMulitplicateur;}
    public void addVolumeMultiplicateur() {volumeMulitplicateur++; checkVolume();}
    public void removeVolumeMultiplicateur() {volumeMulitplicateur--; checkVolume();}

    public Sound(){
        soundTab[0] = getClass().getResource("../ressource/sound/ingamesound.wav");
        soundTab[1] = getClass().getResource("../ressource/sound/ingamesound2.wav");
        soundTab[2] = getClass().getResource("../ressource/sound/ingamesound3.wav");
        soundTab[3] = getClass().getResource("../ressource/sound/instorymodesound.wav");
        soundTab[4] = getClass().getResource("../ressource/sound/inmenusound.wav");
    }

    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundTab[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }

    public void checkVolume(){
        switch (volumeMulitplicateur) {
            case 0:
                volume = -80f;
                break;
            case 1:
                volume = -30f;
                break;
            case 2:
                volume = -10f;
                break;
            case 3:
                volume = -5f;
                break;
            case 4:
                volume = 1f;
                break;
            case 5 :
                volume = 6f;
                break;
        }
        fc.setValue(volume);
    }
}
